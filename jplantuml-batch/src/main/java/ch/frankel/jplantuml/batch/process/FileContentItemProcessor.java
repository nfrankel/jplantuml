package ch.frankel.jplantuml.batch.process;

import ch.frankel.jplantuml.batch.model.NamedContent;
import ch.frankel.jplantuml.online.ContentFileReader;
import org.springframework.batch.item.ItemProcessor;

import java.nio.file.Path;

public class FileContentItemProcessor extends ContentFileReader implements ItemProcessor<Path, NamedContent> {

    @Override
    public NamedContent process(Path item) throws Exception {
        String content = apply(item.toFile());
        String fileName = item.getFileName().toString();
        return new NamedContent(fileName, content);
    }
}
