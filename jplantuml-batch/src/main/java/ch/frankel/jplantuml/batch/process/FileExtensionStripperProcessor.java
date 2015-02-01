package ch.frankel.jplantuml.batch.process;

import ch.frankel.jplantuml.batch.model.NamedContent;
import ch.frankel.jplantuml.online.ContentFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class FileExtensionStripperProcessor implements ItemProcessor<NamedContent, NamedContent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileReader.class);

    String strip(String fileName) {
        int dotPosition = fileName.lastIndexOf('.');
        if (dotPosition == -1) {
            return fileName;
        }
        return fileName.substring(0, dotPosition);
    }

    @Override
    public NamedContent process(NamedContent item) throws Exception {
        String originalFileName = item.name();
        LOGGER.debug("File name to strip {}", originalFileName);
        String strippedFileName = strip(originalFileName);
        LOGGER.info("Root file name generated {}", strippedFileName);
        return new NamedContent(strippedFileName, item.content());
    }
}
