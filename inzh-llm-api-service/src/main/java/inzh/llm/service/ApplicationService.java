package inzh.llm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import inzh.llm.service.gguf.GGUFFileService;
import inzh.llm.service.jackson.ObjectMapperFactory;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class ApplicationService {
    
    protected final Configuration configuration;
    
    protected ObjectMapper mapper;
    private final GGUFFileService fileService;

    public ApplicationService(Configuration configuration) {
        this.configuration = configuration;
        this.mapper = ObjectMapperFactory.create();
        this.fileService = new GGUFFileService(configuration);
    }
    
    public ObjectMapper mapper(){
        return mapper;
    }
    
    public GGUFFileService getFileService() {
        return fileService;
    }  
    
    public static void init(ApplicationService service) throws IOException{
        Configuration configuration = service.configuration;
        if(configuration.getModelPath() == null){
            configuration.setModelPath(configuration.getHome().resolve("model"));
            Files.createDirectories(configuration.getModelPath());
        }
    }
}
