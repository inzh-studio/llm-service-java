package inzh.llm.service.prompt;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class PromptGenerator {

    private String delimiter = "[delim]";
    private final StringBuilder prompt = new StringBuilder();

    public PromptGenerator(String init, String delimiter) {
        prompt.append(init);
        this.delimiter = delimiter;
    }

    public PromptGenerator user(String userName) {
        prompt.append(delimiter).append(userName).append(": ");
        return this;
    }

    public PromptGenerator message(String message) {
        prompt.append(message);
        return this;
    }

    @Override
    public String toString() {
        return prompt.toString();
    }
}
