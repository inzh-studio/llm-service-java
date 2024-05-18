package inzh.llm.service.gguf;

import de.kherud.llama.LlamaModel;
import inzh.llm.service.Configuration;
import inzh.llm.service.LlmModelService;
import inzh.llm.service.ModelConfiguration;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class GGUFFileService {

    private static final Logger log = LoggerFactory.getLogger(GGUFFileService.class);

    private final Configuration configuration;

    private List<GGUFFile> cache = null;
    private final Map<GGUFFile, LlamaModel> openned = new ConcurrentHashMap<>();

    public GGUFFileService(Configuration configuration) {
        this.configuration = configuration;
    }

    private List<GGUFFile> cache() throws IOException {
        if (cache != null) {
            return cache;
        }
        
        if(!Files.isDirectory(configuration.getModelPath())){
            Files.createDirectories(configuration.getModelPath());
        }
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(configuration.getModelPath(), "*.gguf")) {
            log.info("GGUF cache on load");
            List<GGUFFile> ls = new ArrayList<>();
            for (Path p : stream) {
                GGUFFile f = new GGUFFile.Parser().parse(p, false);
                ls.add(f);
            }
            cache = ls;
            log.info("GGUF cache loaded");
        }

        return cache;
    }

    public List<GGUFFile> get() throws IOException {
        return cache();
    }
    
    public GGUFFile get(String name) throws IOException {
        return cache().stream().filter(f -> f.getName().equals(name)).findFirst().orElse(null);
    }

    
    // Openned GGUFFile
    
    synchronized public List<GGUFFile> openned() throws IOException {
        return openned.entrySet().stream().map(e -> e.getKey()).collect(Collectors.toList());
    }
    
    synchronized public boolean isOpen(GGUFFile file){
        return openned.entrySet().stream().filter(e -> e.getKey().equals(file)).findFirst().orElse(null) != null;
    }
    
    synchronized public boolean open(GGUFFile file, ModelConfiguration configuration) {
        
        if(openned.containsKey(file)){
            return false;
        }

        Path path = this.configuration.getModelPath().resolve(file.getFilename());
        log.info("Open model file: " + path.toString() + ".");

        if(configuration == null){
            configuration = ModelConfiguration.createDefault();
        }
        configuration.setModelFilePath(path.toString());

        LlamaModel m = new LlamaModel(configuration.toParameters(this.configuration));
        openned.put(file, m);
        
        log.info("Model file: " + path.toString() + " openned.");
        
        return true;
    }
    
    synchronized public boolean close(GGUFFile file) {
        
        if(!openned.containsKey(file)){
            return false;
        }
        
        Path path = configuration.getModelPath().resolve(file.getFilename());
        log.info("Close model file: " + path.toString() + ".");
        
        openned.get(file).close();
        openned.remove(file);
        
        log.info("Model file: " + path.toString() + " closed.");
        
        return true;
    }
    
    synchronized public LlmModelService getService(GGUFFile file){
        Map.Entry<GGUFFile, LlamaModel> me = openned.entrySet().stream().filter(e -> e.getKey().equals(file)).findFirst().orElse(null);
        if(me == null){
            return null;
        }
        return new LlmModelService(me.getValue());
    }
}
