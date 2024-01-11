import com.example.gestiondesnotes.controller.ListeNotesServlet;

import static org.mockito.Mockito.*;

import com.example.gestiondesnotes.dao.ElementDAO;
import com.example.gestiondesnotes.dao.EtudiantDAO;
import com.example.gestiondesnotes.dao.NoteDAO;
import com.example.gestiondesnotes.model.Element;
import com.example.gestiondesnotes.model.Etudiant;
import com.example.gestiondesnotes.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ListeNotesServletTest {

    @Mock private HttpServletRequest request;
    @Mock private HttpServletResponse response;
    @Mock private ElementDAO elementDAO;
    @Mock private NoteDAO noteDAO;
    @Mock private EtudiantDAO etudiantDAO;
    @Mock private RequestDispatcher dispatcher;

    private ListeNotesServlet servlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new ListeNotesServlet();

        // Remplacement des instances DAO dans le servlet par des mocks
        servlet.elementDAO = elementDAO;
        servlet.noteDAO = noteDAO;
        servlet.etudiantDAO = etudiantDAO;
    }

    @Test
    public void testDoGet() throws Exception {
        int testCodeElement = 1;
        when(request.getParameter("code_element")).thenReturn(String.valueOf(testCodeElement));
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        Element mockElement = new Element(); // Créez une instance mockée d'Element
        List<Etudiant> mockEtudiants = new ArrayList<>(); // Liste mockée d'Etudiants
        List<Note> mockNotes = new ArrayList<>(); // Liste mockée de Notes

        // Configurer les DAOs pour retourner les données mockées
        when(elementDAO.getById(testCodeElement)).thenReturn(mockElement);
        when(etudiantDAO.getEtudiantsByElement(testCodeElement)).thenReturn(mockEtudiants);
        when(noteDAO.getNotesByElementModule(testCodeElement)).thenReturn(mockNotes);

        servlet.doGet(request, response);

        // Vérifiez que les attributs appropriés sont définis sur la requête
        verify(request).setAttribute("element", mockElement);
        verify(request).setAttribute("etudiants", mockEtudiants);
        verify(request).setAttribute("notes", mockNotes);
        verify(request).setAttribute(eq("jsonData"), anyString());

        // Vérifiez que le dispatcher est appelé pour transmettre la requête et la réponse
        verify(dispatcher).forward(request, response);
    }
}
