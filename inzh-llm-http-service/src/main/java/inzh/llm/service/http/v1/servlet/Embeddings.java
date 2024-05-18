package inzh.llm.service.http.v1.servlet;

import inzh.llm.service.LlmModelService;
import inzh.llm.service.gguf.GGUFFile;
import inzh.llm.service.gguf.GGUFFileService;
import inzh.llm.service.http.ApplicationService;
import inzh.llm.service.http.v1.response.EmbeddingsResponse;
import inzh.llm.service.http.v1.request.InputRequest;
import java.io.IOException;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Embeddings extends Input {

    public Embeddings(ApplicationService application) {
        super(application);
    }
    
    @Override
    protected EmbeddingsResponse create(InputRequest request) throws IOException{
        
        GGUFFileService service = application().getFileService();
        GGUFFile file = service.get(request.getModel());
        LlmModelService mService = service.getService(file);
        
        long start = System.currentTimeMillis();
        float[] embedding = mService.embedding(request.getInput());
        
        EmbeddingsResponse response = EmbeddingsResponse.create(embedding, request.getKey(), System.currentTimeMillis() - start);
        return response;
    }
}
