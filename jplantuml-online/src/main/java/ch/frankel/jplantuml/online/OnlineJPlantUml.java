package ch.frankel.jplantuml.online;

import ch.frankel.jplantuml.api.JPlantUml;
import ch.frankel.jplantuml.api.JPlantUmlException;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class OnlineJPlantUml implements JPlantUml<String, byte[]> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileReader.class);
    public static final String SRC_ATTR = "src";
    private final Settings settings;

    public OnlineJPlantUml(Settings settings) {
        this.settings = settings;
    }

    @Override
    public byte[] apply(String description) {
        try {
            LOGGER.debug("Trying to connect to server {}", settings.serverUrl());
            Document document = Jsoup.connect(settings.serverUrl())
                .data(settings.paramName(), description)
                .post();
            Element img = document.select(settings.imgCssSelector()).first();
            String src = img.attr(SRC_ATTR);
            LOGGER.debug("Ouput image created {}", src);
            URL url = new URL(src);
            return IOUtils.toByteArray(url);
        } catch (IOException e) {
            LOGGER.error("An error occured during online processing", e);
            throw new JPlantUmlException(e);
        }
    }
}
