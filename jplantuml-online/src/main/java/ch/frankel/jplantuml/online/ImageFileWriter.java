package ch.frankel.jplantuml.online;

import ch.frankel.jplantuml.api.JPlantUmlException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class ImageFileWriter implements Function<byte[], File> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileReader.class);

    private final String path;

    public ImageFileWriter(String path) {
        this.path = path;
    }

    @Override
    public File apply(byte[] bytes) {
        File file = new File(path);
        try {
            LOGGER.debug("File to write {}", file.getAbsoluteFile());
            FileUtils.writeByteArrayToFile(file, bytes);
            LOGGER.info("File written {} ", file.getAbsoluteFile());
            return file;
        } catch (IOException e) {
            LOGGER.error("An error occured during file writing", e);
            throw new JPlantUmlException(e);
        }
    }
}
