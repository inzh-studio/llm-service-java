package inzh.llm.service.http.v1.response;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class EmbeddingsResponse extends Response {

    private float[] embedding;

    public float[] getEmbedding() {
        return embedding;
    }

    public void setEmbedding(float[] embedding) {
        this.embedding = embedding;
    }

    public static EmbeddingsResponse create(float[] embedding, String key, long duration) {
        EmbeddingsResponse r = new EmbeddingsResponse();
        set(r, key, duration);
        r.embedding = embedding;
        return r;
    }
}
