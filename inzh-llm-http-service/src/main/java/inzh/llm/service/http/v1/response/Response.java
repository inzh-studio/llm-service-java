package inzh.llm.service.http.v1.response;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public abstract class Response {
    
    protected long duration;
    protected String key;

    public long getDuration() {
        return duration;
    }
    
    public String getKey() {
        return key;
    }
    
    public static Response set(Response r, String key, long duration) {
        r.key = key;
        r.duration = duration;
        return r;
    }
}
