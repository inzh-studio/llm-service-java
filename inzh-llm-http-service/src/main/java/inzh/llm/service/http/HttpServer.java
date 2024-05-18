package inzh.llm.service.http;

import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class HttpServer {

    public static Server create(int port){
        return create(port, "127.0.0.1");
    }
    
    public static Server create(int port, String host){
        
        Server server = new Server();
        
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        if(host != null){
            connector.setHost(host);
        }
        
        // Hide server info
        for(ConnectionFactory x  : connector.getConnectionFactories()) {
            if(x instanceof HttpConnectionFactory) {
                ((HttpConnectionFactory)x).getHttpConfiguration().setSendServerVersion(false);
            }
        }
        
        server.setConnectors(new Connector[] { connector });
        
        return server;
    }
}
