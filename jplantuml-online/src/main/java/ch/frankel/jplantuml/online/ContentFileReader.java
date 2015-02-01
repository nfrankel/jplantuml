package ch.frankel.jplantuml.online;

import ch.frankel.jplantuml.api.JPlantUmlException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class ContentFileReader implements Function<File, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileReader.class);

    @Override
    public String apply(File file) {
        try {
            LOGGER.debug("File to read {}", file.getAbsoluteFile());
            String content = FileUtils.readFileToString(file);
            LOGGER.info("File {} read\n", file.getAbsolutePath());
            LOGGER.debug("File content {}\t", content);
            return content;
        } catch (IOException e) {
            LOGGER.error("An error occured during file reading", e);
            throw new JPlantUmlException(e);
        }
    }
}
