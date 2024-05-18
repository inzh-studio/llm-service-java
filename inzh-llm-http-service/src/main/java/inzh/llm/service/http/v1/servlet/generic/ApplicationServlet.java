package inzh.llm.service.http.v1.servlet.generic;

import inzh.llm.service.http.ApplicationService;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class ApplicationServlet extends MappedServlet {
    
    protected ApplicationService application;
    
    public ApplicationServlet(ApplicationService application) {
        super(application.mapper());
        this.application = application;
    }

    public ApplicationService application() {
        return application;
    }  
}
