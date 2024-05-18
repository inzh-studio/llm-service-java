package inzh.llm.service.http.v1.response;

import inzh.llm.service.LlmModelService;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class CompletionsPartialResponse extends Response {

    private String text;

    public String getText() {
        return text;
    }

    public static CompletionsPartialResponse create(LlmModelService.Event o, String key, long duration) {
        CompletionsPartialResponse r = new CompletionsPartialResponse();
        set(r, key, duration);
        r.text = o.text;
        return r;
    }

}
