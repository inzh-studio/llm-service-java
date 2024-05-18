package inzh.llm.service.http;

import inzh.llm.service.Configuration;
import inzh.llm.service.gguf.GGUFFile;
import inzh.llm.service.http.v1.ServletContextHandlerBuilder;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Main {
    
    private static Path parsePath(String str){
        return Paths.get(str);
    }

    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();
        
        String serverHost = "127.0.0.1";
        Integer serverPort = 18000;
        String[] openOnStart = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--home":
                    configuration.setHome(parsePath(args[i + 1]));
                    break;
                    
                case "--modelPath":
                case "-mp":
                    configuration.setModelPath(parsePath(args[i + 1]));
                    break;
                    
                case "--logPath":
                case "-lp":
                    configuration.setLogPath(parsePath(args[i + 1]));
                    break;
                    
                case "--port":
                case "-p":
                    serverPort = Integer.valueOf(args[i + 1]);
                    break;
                    
                case "--host":
                case "-h":
                    serverHost = args[i + 1];
                    break;
                    
                case "--open":
                    openOnStart = args[i + 1].split(",");
                    break;
            }
        }

        ApplicationService a = new ApplicationService(configuration);
        ApplicationService.init(a);
        
        if(openOnStart != null){
            for(String model : openOnStart){
                GGUFFile file = a.getFileService().get(model);
                if(file == null){
                    throw new IOException("No model found: " + model);
                }
                a.getFileService().open(file, null);
            }
        }else{
            a.getFileService().get();
        }
        
        Server s = HttpServer.create(serverPort, serverHost);
        GzipHandler gzipHandler = new GzipHandler();
        s.setHandler(gzipHandler);
        
        ServletContextHandlerBuilder b = new ServletContextHandlerBuilder();
        gzipHandler.setHandler(b.application(a).build());
        
        s.start();
    }
}
