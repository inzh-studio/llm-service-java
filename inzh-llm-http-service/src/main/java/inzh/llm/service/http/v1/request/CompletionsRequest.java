package inzh.llm.service.http.v1.request;

import inzh.llm.service.InferenceConfiguration;
import java.util.List;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class CompletionsRequest extends Request {
    
    public static class Message {
        
        private String role;
        private String content;

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }
    
    public static class Member {
        
        public final static String USER = "user";
        public final static String BOT = "bot";
        
        private String role;
        private String name;

        public String getRole() {
            return role;
        }

        public String getName() {
            return name;
        }
        
        public static Member create(String role, String name){
            Member m = new Member();
            m.role = role;
            m.name = name;
            return m;
        }
    }
    
    
    private String context;
    private String model;
    private List<Member> members;
    private List<Message> messages;
    private InferenceConfiguration configuration;

    public String getContext() {
        return context;
    }

    public String getModel() {
        return model;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public InferenceConfiguration getConfiguration() {
        return configuration;
    }
}
