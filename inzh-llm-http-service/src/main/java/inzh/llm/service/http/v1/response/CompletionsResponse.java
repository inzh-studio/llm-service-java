package inzh.llm.service.http.v1.response;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class CompletionsResponse extends Response {

    private String text;

    public String getText() {
        return text;
    }
    
    public static CompletionsResponse create(String text, String key, long duration) {
        CompletionsResponse r = new CompletionsResponse();
        set(r, key, duration);
        r.text = text;
        return r;
    }
    
}
