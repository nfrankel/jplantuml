package ch.frankel.jplantuml.batch.process;

import ch.frankel.jplantuml.api.JPlantUmlException;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.support.AbstractItemStreamItemReader;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.function.Predicate;

import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.isReadable;

public class FileItemReader extends AbstractItemStreamItemReader<Path> {

    private final Path inputDirectory;
    private final ConfigurableMimeFileTypeMap map;
    private Iterator<Path> files;

    public FileItemReader(Path inputDirectory, ConfigurableMimeFileTypeMap map) {
        Assert.notNull(inputDirectory);
        Assert.isTrue(isDirectory(inputDirectory));
        Assert.isTrue(isReadable(inputDirectory));
        this.map = map;
        this.inputDirectory = inputDirectory;
    }

    @Override
    public void open(ExecutionContext executionContext) {
        Predicate<Path> isReadableTextFile = f ->
            Files.isRegularFile(f) && Files.isReadable(f) && "text/plain".equals(map.getContentType(f.toFile()));
        try {
            files = Files.list(inputDirectory)
                .filter(isReadableTextFile)
                .iterator();
        } catch (IOException e) {
            throw new JPlantUmlException("Can not browse inputDirectory", e);
        }
    }

    @Override
    public Path read() {
        if (files.hasNext()) {
            return files.next();
        }
        return null;
    }
}
