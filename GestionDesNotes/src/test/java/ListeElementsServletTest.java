import com.example.gestiondesnotes.controller.ListeElementsServlet;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ListeElementsServletTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher dispatcher;

    private ListeElementsServlet servlet;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        servlet = new ListeElementsServlet();
    }

    @Test
    public void testDoGetWithNoSession() throws Exception {
        // Mock the request and response
        when(request.getSession(false)).thenReturn(null);
        when(request.getContextPath()).thenReturn("testContextPath");

        // Call the method to test
        servlet.doGet(request, response);

        // Verify the response is redirected to login page
        verify(response).sendRedirect("testContextPath/login.jsp");
    }

    @Test
    public void testDoGetWithSessionButNoCodeProf() throws Exception {
        // Mock the session and request
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("codeProf")).thenReturn(null);
        when(request.getContextPath()).thenReturn("testContextPath");

        // Call the method to test
        servlet.doGet(request, response);

        // Verify the response is redirected to login page
        verify(response).sendRedirect("testContextPath/login.jsp");
    }

}