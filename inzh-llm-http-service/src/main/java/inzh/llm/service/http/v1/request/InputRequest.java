package inzh.llm.service.http.v1.request;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class InputRequest extends Request {
    
    private String model;
    private String input;
    private int[] tokens;

    public String getModel() {
        return model;
    }

    public String getInput() {
        return input;
    }

    public int[] getTokens() {
        return tokens;
    }
}
