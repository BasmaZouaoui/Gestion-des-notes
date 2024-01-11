import com.example.gestiondesnotes.controller.LoginServlet;
import com.example.gestiondesnotes.dao.dao_impl.ProfesseurDAOImpl;
import com.example.gestiondesnotes.model.Professeur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ProfesseurDAOImpl professeurDAO;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher requestDispatcher;

    private LoginServlet loginServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginServlet = new LoginServlet();
    }

    @Test
    void testSuccessfulLogin() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("username")).thenReturn("john_doe");
        when(request.getParameter("password")).thenReturn("mot_de_passe1");
        Professeur professeur = new Professeur(1, "Doe", "John", "Mathématiques", "john_doe", "mot_de_passe1");
        when(professeurDAO.getProfesseurByUsernameAndPassword("john_doe", "mot_de_passe1")).thenReturn(professeur);
        when(request.getSession(true)).thenReturn(session);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(session).setAttribute("codeProf", 1);  // Utilisez la valeur correcte
        verify(response).sendRedirect(request.getContextPath() + "/listeElements");
    }

    @Test()
    void testFailedLogin() throws ServletException, IOException {
        // Arrange
        when(request.getParameter("username")).thenReturn("invalidUsername");
        when(request.getParameter("password")).thenReturn("invalidPassword");
        when(professeurDAO.getProfesseurByUsernameAndPassword("invalidUsername", "invalidPassword")).thenReturn(null);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(response).sendRedirect("login.jsp?error=1");
    }
}
