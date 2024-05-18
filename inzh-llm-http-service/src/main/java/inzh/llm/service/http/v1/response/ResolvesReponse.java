package inzh.llm.service.http.v1.response;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class ResolvesReponse extends Response {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ResolvesReponse create(String text, String key, long duration) {
        ResolvesReponse r = new ResolvesReponse();
        set(r, key, duration);
        r.text = text;
        return r;
    }
}
