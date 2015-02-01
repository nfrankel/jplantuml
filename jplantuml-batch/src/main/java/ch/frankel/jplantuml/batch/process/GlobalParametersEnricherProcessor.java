package ch.frankel.jplantuml.batch.process;

import ch.frankel.jplantuml.batch.model.NamedContent;
import ch.frankel.jplantuml.online.ContentFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class GlobalParametersEnricherProcessor implements ItemProcessor<NamedContent, NamedContent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentFileReader.class);

    private final String[] globalParameters;

    public GlobalParametersEnricherProcessor(String globalParameters) {
        if (globalParameters == null) {
            this.globalParameters = null;
        } else {
            this.globalParameters = globalParameters.split(";");
        }
    }

    @Override
    public NamedContent process(NamedContent item) throws Exception {
        if (globalParameters == null) {
            LOGGER.info("No global parameters to apply");
            return item;
        }
        String lineSeparator = System.getProperty("line.separator");
        StringBuilder builder = new StringBuilder();
        for (String globalParameter: globalParameters) {
            builder.append(globalParameter);
            LOGGER.info("Global parameter {} applied", globalParameter);
            builder.append(lineSeparator);
        }
        String content = builder.append(lineSeparator).append(item.content()).toString();
        LOGGER.debug("File content with parameter(s)\n\t{}", content);
        return new NamedContent(item.name(), content);
    }
}
