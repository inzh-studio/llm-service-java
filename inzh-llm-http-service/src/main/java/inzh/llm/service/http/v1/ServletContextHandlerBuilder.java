package inzh.llm.service.http.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import inzh.llm.service.http.ApplicationService;
import inzh.llm.service.http.v1.servlet.Embeddings;
import inzh.llm.service.http.v1.servlet.generic.EmptyServlet;
import inzh.llm.service.http.v1.servlet.Models;
import inzh.llm.service.http.v1.servlet.Completions;
import inzh.llm.service.http.v1.servlet.Resolves;
import inzh.llm.service.http.v1.servlet.Tokenizes;
import jakarta.servlet.Servlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class ServletContextHandlerBuilder {
    
    private final static  String PREFIX = "v1";
    
    private ApplicationService application;
    
    public String resolve(String path){
        return "/" + PREFIX + "/" + path;
    }
    
    public String resolveRoot(String path){
        return "/" + path;
    }
    
    public ServletContextHandlerBuilder application(ApplicationService application){
        this.application = application;
        return this;
    }
    
    private void addServlet(ServletContextHandler handler, Servlet servlet, String pathSpec){
        ServletHolder holder = new ServletHolder(servlet);
        handler.addServlet(holder, pathSpec);
        if(!pathSpec.endsWith("/*")){
            handler.addServlet(holder, pathSpec + "/*");
        }
    }
    
    public ServletContextHandler build(){
        ServletContextHandler ha = new ServletContextHandler();
   
        ObjectMapper mapper = application.mapper();

        ha.addServlet(new ServletHolder(new EmptyServlet(mapper)), "/*");
        addServlet(ha, new Models(application), resolve("models"));
        
        addServlet(ha, new Embeddings(application), resolve("embeddings"));
        addServlet(ha, new Tokenizes(application), resolve("tokenizes"));
        addServlet(ha, new Resolves(application), resolve("resolves"));
    
        addServlet(ha, new Completions(application), resolve("chat/completions"));

        return ha;
    }
}
