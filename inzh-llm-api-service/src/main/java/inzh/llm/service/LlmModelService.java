package inzh.llm.service;

import de.kherud.llama.InferenceParameters;
import de.kherud.llama.LlamaModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class LlmModelService implements AutoCloseable {

    private final LlamaModel model;

    public LlmModelService(LlamaModel model) {
        this.model = model;
    }

    public String complete(String prompt) {
        return complete(prompt, InferenceConfiguration.createDefault());
    }

    public String complete(String prompt, InferenceConfiguration configuration) {
        configuration = configuration == null ? InferenceConfiguration.createDefault() : configuration;
        return model.complete(configuration.toParameters(prompt));
    }
    
    public static class Event {
        
        public String text;
        
        public static Event create(String text){
            Event s = new Event();
            s.text = text;
            return s;
        }
    }
    
    private final class LocalIterator implements Iterator<Event> {

        private final Iterator<LlamaModel.Output> source;

        private LocalIterator(Iterable<LlamaModel.Output> source) {
            this.source = source.iterator();
        }

        @Override
        public boolean hasNext() {
            return source.hasNext();
        }

        @Override
        public Event next() {
            LlamaModel.Output output = source.next();
            return Event.create(output.text);
        }
    }

    public Iterable<Event> stream(String prompt) {
        return stream(prompt, InferenceConfiguration.createDefault());
    }

    public Iterable<Event> stream(String prompt, InferenceConfiguration configuration) {
        if(prompt == null){
            return null;
        }
        configuration = configuration == null ? InferenceConfiguration.createDefault() : configuration;
        InferenceParameters parameters = configuration.toParameters(prompt);
        return () -> new LocalIterator(model.generate(parameters));
    }

    public String resolve(int[] tokens) {
        if(tokens == null){
            return null;
        }
        return model.decode(tokens);
    }

    public List<String> resolve(List<int[]> tokenss) {
        List<String> re = new ArrayList<>();
        for (int[] tokens : tokenss) {
            re.add(resolve(tokens));
        }
        return re;
    }

    public int[] tokenize(String text) {
        if(text == null){
            return null;
        }
        return model.encode(text);
    }

    public List<int[]> tokenize(List<String> prompts) {
        List<int[]> re = new ArrayList<>();
        for (String prompt : prompts) {
            re.add(tokenize(prompt));
        }
        return re;
    }

    public float[] embedding(String prompt) {
        if (prompt == null) {
            return null;
        }
        return model.embed(prompt);
    }

    public List<float[]> embedding(List<String> prompts) {
        List<float[]> re = new ArrayList<>();
        for (String prompt : prompts) {
            re.add(embedding(prompt));
        }
        return re;
    }

    @Override
    public void close() throws Exception {
        model.close();
    }
}
