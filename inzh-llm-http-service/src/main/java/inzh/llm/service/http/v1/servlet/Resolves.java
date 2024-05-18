package inzh.llm.service.http.v1.servlet;

import inzh.llm.service.LlmModelService;
import inzh.llm.service.gguf.GGUFFile;
import inzh.llm.service.gguf.GGUFFileService;
import inzh.llm.service.http.ApplicationService;
import inzh.llm.service.http.v1.request.InputRequest;
import inzh.llm.service.http.v1.response.ResolvesReponse;
import java.io.IOException;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Resolves extends Input {
    
    public Resolves(ApplicationService application) {
        super(application);
    }
    
    @Override
    protected ResolvesReponse create(InputRequest request) throws IOException{
        GGUFFileService service = application().getFileService();
        GGUFFile file = service.get(request.getModel());
        LlmModelService mService = service.getService(file);

        long start = System.currentTimeMillis();
        String text = mService.resolve(request.getTokens());
        ResolvesReponse response = ResolvesReponse.create(text, request.getKey(), System.currentTimeMillis() - start);
        return response;
    }
}