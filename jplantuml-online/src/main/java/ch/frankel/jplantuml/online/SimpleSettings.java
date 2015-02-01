package ch.frankel.jplantuml.online;

import ch.frankel.jplantuml.api.JPlantUmlException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SimpleSettings implements Settings {

    private Properties props;

    public SimpleSettings() {
        props = new Properties();
        InputStream stream = getClass().getResourceAsStream(PLANTUML_PROPERTIES);
        try {
            props.load(stream);
        } catch (IOException e) {
            throw new JPlantUmlException("Cannot initialize PlantUML module", e);
        }
    }

    public String serverUrl() {
        return props.getProperty(SERVER_URL);
    }

    public String paramName() {
        return props.getProperty(PARAM_NAME);
    }

    public String imgCssSelector() {
        return props.getProperty(IMG_CSS_SELECTOR);
    }
}
