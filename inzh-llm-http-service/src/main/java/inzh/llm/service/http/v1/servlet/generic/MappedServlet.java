package inzh.llm.service.http.v1.servlet.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class MappedServlet extends HttpServlet {

    protected final ObjectMapper mapper;

    public MappedServlet(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    protected void doTrace(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        writeMethodNotSupported(r, re);
    }

    @Override
    protected void doOptions(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        writeMethodNotSupported(r, re);
    }

    @Override
    protected void doDelete(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        writeMethodNotSupported(r, re);
    }

    @Override
    protected void doPut(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        writeMethodNotSupported(r, re);
    }

    @Override
    protected void doPost(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        writeMethodNotSupported(r, re);
    }

    @Override
    protected void doHead(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        writeMethodNotSupported(r, re);
    }

    @Override
    protected void doGet(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        writeMethodNotSupported(r, re);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            super.service(req, resp);
        } catch(Throwable ex){
            ex.printStackTrace();
            writeFormatedError(resp, 500, ex.getLocalizedMessage());
        }
    }
    
    protected String[] path(HttpServletRequest r) {
        String pathInfo = r.getPathInfo();
        if (pathInfo != null) {
            pathInfo = pathInfo.substring(1);
            if (pathInfo.isEmpty()) {
                pathInfo = null;
            }
        }

        if (pathInfo != null) {
            return pathInfo.split("/");
        }
        return null;
    }
    
    
    
    private int getMethodNotSupportedCode(HttpServletRequest r) {
        switch (r.getProtocol()) {
            default:
                return HttpServletResponse.SC_METHOD_NOT_ALLOWED;
            case "HTTP/0.9":
            case "HTTP/1.0":
                return HttpServletResponse.SC_BAD_REQUEST;
        }
    }
    
    protected void writeOkOrFound(HttpServletResponse re, Boolean ok) throws IOException{
        write(null, re, ok ? HttpServletResponse.SC_OK : HttpServletResponse.SC_FOUND);
    }
    
    protected void writeNotFound(HttpServletResponse re) throws IOException{
        writeFormatedError(re, HttpServletResponse.SC_NOT_FOUND, "Not Found");
    }
    
    protected void writeMethodNotSupported(HttpServletRequest r, HttpServletResponse re) throws IOException{
        int status = getMethodNotSupportedCode(r);
        writeFormatedError(re, status, "Method not allowed");
    }
    
    protected void writeFormatedError(HttpServletResponse re, int status, String message) throws IOException{
        ObjectNode o = mapper.createObjectNode();
        o.put("message", message);
        o.put("code", status);
        
        write(o, re, status);
    }

    protected void write(HttpServletResponse re, int status) throws IOException {
        write(null, re, status);
    }
    
    protected void write(Object o, HttpServletResponse re, int status) throws IOException {
        re.setContentType("application/json;charset=UTF-8");
        re.setStatus(status);
        if(o != null){
            mapper.writeValue(re.getOutputStream(), o);
        }
    }
}
