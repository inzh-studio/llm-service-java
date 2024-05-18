package inzh.llm.service.http.v1.response;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class TokenizesReponse extends Response {

    private int[] tokens;

    public int[] getTokens() {
        return tokens;
    }

    public void setTokens(int[] tokens) {
        this.tokens = tokens;
    }

    public static TokenizesReponse create(int[] tokens, String key, long duration) {
        TokenizesReponse r = new TokenizesReponse();
        set(r, key, duration);
        r.tokens = tokens;
        return r;
    }
}
