package ch.frankel.jplantuml.batch.config;

import ch.frankel.jplantuml.online.Settings;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "plantuml")
public class BatchSettings implements Settings {

    private String serverUrl;
    private String paramName;
    private String imgCssSelector;
    private String directoryIn;
    private String directoryOut;

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public void setImgCssSelector(String imgCssSelector) {
        this.imgCssSelector = imgCssSelector;
    }

    public void setDirectoryIn(String directoryIn) {
        this.directoryIn = directoryIn;
    }

    public void setDirectoryOut(String directoryOut) {
        this.directoryOut = directoryOut;
    }

    @Override
    public String serverUrl() {
        return serverUrl;
    }

    @Override
    public String paramName() {
        return paramName;
    }

    @Override
    public String imgCssSelector() {
        return imgCssSelector;
    }

    public String directoryIn() {
        return directoryIn;
    }

    public String directoryOut() {
        return directoryOut;
    }
}
