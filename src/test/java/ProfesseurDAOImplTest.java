import com.example.gestiondesnotes.dao.dao_impl.ProfesseurDAOImpl;
import com.example.gestiondesnotes.model.Professeur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.gestiondesnotes.dao.connexioJDBC.Connexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProfesseurDAOImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Connexion connexion;

    @InjectMocks
    private ProfesseurDAOImpl professeurDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(connexion.getConnection()).thenReturn(connection);
    }

    @Test
    void testGetProfesseurByUsernameAndPassword() throws SQLException {
        // Arrange
        String username = "john_doe";
        String password = "mot_de_passe1";
        Professeur expectedProfesseur = new Professeur(1, "Doe", "John", "Mathématiques", username, password);

        // Mocking the PreparedStatement and ResultSet
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("code_prof")).thenReturn(expectedProfesseur.getCode_prof());
        when(resultSet.getString("nom_prof")).thenReturn(expectedProfesseur.getNom_prof());
        when(resultSet.getString("prenom_prof")).thenReturn(expectedProfesseur.getPrenom_prof());
        when(resultSet.getString("specialite")).thenReturn(expectedProfesseur.getSpecialite());
        when(resultSet.getString("username")).thenReturn(expectedProfesseur.getUsername());
        when(resultSet.getString("password")).thenReturn(expectedProfesseur.getPassword());

        // Act
        Professeur actualProfesseur = professeurDAO.getProfesseurByUsernameAndPassword(username, password);

        // Assert
        assertEquals(expectedProfesseur, actualProfesseur);
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).setString(1, username);
        verify(preparedStatement).setString(2, password);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getInt("code_prof");
        verify(resultSet).getString("nom_prof");
        verify(resultSet).getString("prenom_prof");
        verify(resultSet).getString("specialite");
        verify(resultSet).getString("username");
        verify(resultSet).getString("password");
    }

}
