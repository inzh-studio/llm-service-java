package inzh.llm.service;

import java.nio.file.Path;

/**
 *
 * @author Jean-Raffi Nazareth
 */
public class Configuration {
    
    private Path home;
    private Path modelPath;
    private Path logPath;

    public Path getHome() {
        return home;
    }

    public void setHome(Path home) {
        this.home = home;
    }

    public Path getModelPath() {
        return modelPath;
    }

    public void setModelPath(Path modelPath) {
        this.modelPath = modelPath;
    }

    public Path getLogPath() {
        return logPath;
    }

    public void setLogPath(Path logPath) {
        this.logPath = logPath;
    }
}
