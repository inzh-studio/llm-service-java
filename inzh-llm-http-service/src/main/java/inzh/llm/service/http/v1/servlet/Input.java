package inzh.llm.service.http.v1.servlet;

import com.fasterxml.jackson.databind.node.ObjectNode;
import inzh.llm.service.gguf.GGUFFile;
import inzh.llm.service.gguf.GGUFFileService;
import inzh.llm.service.http.ApplicationService;
import inzh.llm.service.http.v1.request.InputRequest;
import inzh.llm.service.http.v1.servlet.generic.ApplicationServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public abstract class Input extends ApplicationServlet {

    private final GGUFFileService fileService;
    
    public Input(ApplicationService application) {
        super(application);
        this.fileService = application.getFileService();
    }
    
    protected abstract <T> T create(InputRequest request) throws IOException;
    
    @Override
    protected void doPost(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        
        List<InputRequest> es = null;
        if(r.getInputStream().isReady()){
            es = mapper.readerForListOf(InputRequest.class).readValue(r.getInputStream());
        }
        
        if(es == null){
            write(null, re, HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Checks models
        List<String> models = es.stream().map(e -> {
            return e.getModel();
        }).distinct().collect(Collectors.toList());

        for(String modelName : models){
            GGUFFile f = fileService.get(modelName);
            if(f == null){
                throw new IOException("Model not exist: " + modelName);
            }
            if(!fileService.isOpen(f)){
                throw new IOException("Model not open: " + modelName);
            }
        }

        // Create
        List responses = new ArrayList<>();
        for(InputRequest e : es){
            try {
                responses.add(create(e));
            } catch (IOException ex) {
                ObjectNode o = mapper.createObjectNode();
                o.put("message", ex.getMessage());
                o.put("code", 500);
                responses.add(o);
            }
        }

        write(responses, re, HttpServletResponse.SC_OK);
    }
}
