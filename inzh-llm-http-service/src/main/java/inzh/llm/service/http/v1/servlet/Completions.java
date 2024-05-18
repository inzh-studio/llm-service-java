package inzh.llm.service.http.v1.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import inzh.llm.service.InferenceConfiguration;
import inzh.llm.service.LlmModelService;
import inzh.llm.service.LlmModelService.Event;
import inzh.llm.service.gguf.GGUFFile;
import inzh.llm.service.gguf.GGUFFileService;
import inzh.llm.service.http.ApplicationService;
import inzh.llm.service.http.v1.request.CompletionsRequest;
import inzh.llm.service.http.v1.request.CompletionsRequest.Member;
import inzh.llm.service.http.v1.request.CompletionsRequest.Message;
import inzh.llm.service.http.v1.response.CompletionsPartialResponse;
import inzh.llm.service.http.v1.response.CompletionsResponse;
import inzh.llm.service.http.v1.servlet.generic.ApplicationServlet;
import inzh.llm.service.prompt.PromptGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Completions extends ApplicationServlet {

    public Completions(ApplicationService application) {
        super(application);
    }

    @Override
    protected void doPost(HttpServletRequest r, HttpServletResponse re) throws ServletException, IOException {
        CompletionsRequest sr = mapper.readValue(r.getInputStream(), CompletionsRequest.class);
        doCompletionsRequest(sr, r, re);
    }
    
    private CompletionsRequest.Member getMember(List<CompletionsRequest.Member> members, String role){
        if(members == null){
            return null;
        }
        
        for(CompletionsRequest.Member member : members){
            if(role.equals(member.getRole())){
                return member;
            }
        }
        return null;
    }
    
    private List<Member> readOrSetMembers(CompletionsRequest cr){
        List<Member> members = cr.getMembers() == null ? new ArrayList<>() : new ArrayList<>(cr.getMembers());
        CompletionsRequest.Member user = getMember(members, Member.USER);
        if(user == null){
            user = Member.create(Member.USER, "User");
            members.add(user);
        }
        
        Member bot = getMember(members, Member.BOT);
        if(bot == null){
            bot = Member.create(Member.BOT, "Bot");
            members.add(bot);
        }
        return members;
    }

    protected void doCompletionsRequest(CompletionsRequest cr, HttpServletRequest r, HttpServletResponse re) throws IOException{
        
        GGUFFileService service = application().getFileService();
        GGUFFile file = service.get(cr.getModel());
        if(file == null){
            writeFormatedError(re, HttpServletResponse.SC_BAD_REQUEST, "Model not found");
            return;
        }  
        
        if(!service.isOpen(file)){
            writeFormatedError(re, HttpServletResponse.SC_BAD_REQUEST, "Model not open");
            return;
        }
        
        InferenceConfiguration configuration = cr.getConfiguration() == null ? 
                InferenceConfiguration.createDefault() : 
                InferenceConfiguration.setDefault(cr.getConfiguration());
        
        List<Member> members = readOrSetMembers(cr);
        Member bot = getMember(members, Member.BOT);
        Member user = getMember(members, Member.USER);
        
        LlmModelService mService = service.getService(file);
        
        String context = cr.getContext().replaceAll("\\$\\{user.name\\}", user.getName());
        context = context.replaceAll("\\$\\{bot.name\\}", bot.getName());
        
        PromptGenerator g = new PromptGenerator(context, configuration.getStopStrings()[0]);
        for(Message m : cr.getMessages()){
            g.user(getMember(members, m.getRole()).getName()).message(m.getContent());
        }
        
        g.user(bot.getName());
        
        String prompt = g.toString();

        if(cr.getConfiguration() != null && cr.getConfiguration().isStream()){
            re.setCharacterEncoding("UTF-8");
            re.setContentType("text/event-stream");
            try(PrintWriter w = re.getWriter()) {
                long start = System.currentTimeMillis();
                Iterable<Event> os = mService.stream(prompt, configuration);
                if(os != null){
                    for (Event o : os) {
                        CompletionsPartialResponse response = CompletionsPartialResponse.create(o, cr.getKey(), System.currentTimeMillis() - start);
                        write(w, response);
                        start = System.currentTimeMillis();
                    }
                }
            }
        }else{
            long start = System.currentTimeMillis();
            String text = mService.complete(prompt, configuration);
            CompletionsResponse response = CompletionsResponse.create(text, cr.getKey(), System.currentTimeMillis() - start);
            super.write(response, re, HttpServletResponse.SC_OK);
        }
    }
    
    protected void write(PrintWriter w, Object o) throws JsonProcessingException{
        w.write("data: " + mapper.writeValueAsString(o) + "\n\n");
        w.flush();
    }
}
