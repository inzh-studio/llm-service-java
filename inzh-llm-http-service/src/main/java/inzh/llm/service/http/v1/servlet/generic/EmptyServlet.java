package inzh.llm.service.http.v1.servlet.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class EmptyServlet extends MappedServlet {
    
    public EmptyServlet(ObjectMapper mapper) {
        super(mapper);
    }

    @Override
    protected void service(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        writeNotFound(re);
    }
}
