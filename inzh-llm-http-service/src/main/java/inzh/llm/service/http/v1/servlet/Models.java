package inzh.llm.service.http.v1.servlet;

import inzh.llm.service.ModelConfiguration;
import inzh.llm.service.http.v1.servlet.generic.ApplicationServlet;
import inzh.llm.service.http.ApplicationService;
import inzh.llm.service.gguf.GGUFFile;
import inzh.llm.service.gguf.GGUFFileService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Models extends ApplicationServlet {

    private final GGUFFileService service;

    public Models(ApplicationService application) {
        super(application);
        service = application.getFileService();
    }
    
    @Override
    protected void doGet(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        String[] path = path(r);
        int type = path == null ? 0 : path.length;

        switch (type) {
            default:
                write(null, re, HttpServletResponse.SC_BAD_REQUEST);
                break;
            case 0: // All
                Boolean open = Boolean.valueOf(r.getParameter("open"));
                write(open ? service.openned() : service.get(), re, HttpServletResponse.SC_OK);
                break;

            case 1: // Get
            {
                GGUFFile f = service.get(path[0]);
                if (f == null) {
                    writeNotFound(re);
                } else {
                    write(f, re, HttpServletResponse.SC_OK);
                }
            }
            break;

            case 2: // Action
                GGUFFile file = service.get(path[0]);
                if (file == null) {
                    writeNotFound(re);
                } else {
                    switch (path[1]) {
                        default:
                            write(null, re, HttpServletResponse.SC_BAD_REQUEST);
                            break;
                        case "open":
                            ModelConfiguration configuration = mapper.readValue(r.getInputStream(), ModelConfiguration.class);
                            System.out.println(configuration);
                            writeOkOrFound(re, service.open(file, configuration));
                            break;
                        case "close":
                            writeOkOrFound(re, service.close(file));
                            break;
                    }
                }

                service.get(path[0]);
                break;
        }

    }

}
