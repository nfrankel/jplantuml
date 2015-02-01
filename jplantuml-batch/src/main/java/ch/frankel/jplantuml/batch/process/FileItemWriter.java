package ch.frankel.jplantuml.batch.process;

import ch.frankel.jplantuml.batch.model.NamedBytes;
import ch.frankel.jplantuml.online.ImageFileWriter;
import org.springframework.batch.item.ItemWriter;

import java.nio.file.Paths;
import java.util.List;

public class FileItemWriter implements ItemWriter<NamedBytes> {

    public static final String PNG_EXT = ".png";
    private final String outputDirectory;

    public FileItemWriter(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    public void write(List<? extends NamedBytes> items) throws Exception {
        for (NamedBytes namedBytes : items) {
            ImageFileWriter writer = new ImageFileWriter(Paths.get(outputDirectory, namedBytes.name() + PNG_EXT).toString());
            writer.apply(namedBytes.bytes());
        }
    }
}
