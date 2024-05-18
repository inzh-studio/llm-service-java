package inzh.llm.service.http.v1.servlet;

import inzh.llm.service.LlmModelService;
import inzh.llm.service.gguf.GGUFFile;
import inzh.llm.service.gguf.GGUFFileService;
import inzh.llm.service.http.ApplicationService;
import inzh.llm.service.http.v1.request.InputRequest;
import inzh.llm.service.http.v1.response.TokenizesReponse;
import java.io.IOException;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Tokenizes extends Input {
    
    public Tokenizes(ApplicationService application) {
        super(application);
    }
    
    @Override
    protected TokenizesReponse create(InputRequest request) throws IOException{
        GGUFFileService service = application().getFileService();
        GGUFFile file = service.get(request.getModel());
        LlmModelService mService = service.getService(file);
        
        long start = System.currentTimeMillis();
        int[] tokens = mService.tokenize(request.getInput());
        
        TokenizesReponse response = TokenizesReponse.create(tokens, request.getKey(), System.currentTimeMillis() - start);
        return response;
    }
}