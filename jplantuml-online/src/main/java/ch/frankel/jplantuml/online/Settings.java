package ch.frankel.jplantuml.online;

public interface Settings {

    public static final String PLANTUML_PROPERTIES = "/plantuml.properties";
    public static final String SERVER_URL = "server.url";
    public static final String PARAM_NAME = "param.name";
    public static final String IMG_CSS_SELECTOR = "img.css.selector";

    String serverUrl();

    String paramName();

    String imgCssSelector();
}
