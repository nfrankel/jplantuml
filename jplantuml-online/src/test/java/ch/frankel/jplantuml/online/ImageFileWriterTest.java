package ch.frankel.jplantuml.online;

import ch.frankel.jplantuml.api.JPlantUmlException;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ImageFileWriterTest {

    private ImageFileWriter writer;

    @Test(expectedExceptions = NullPointerException.class)
    public void should_throw_exception_when_path_null() {
        writer = new ImageFileWriter(null);
        writer.apply(new byte[0]);
    }

    @Test(expectedExceptions = JPlantUmlException.class)
    public void should_throw_exception_when_file_not_exists() {
        writer = new ImageFileWriter("/xyz/dummy");
        writer.apply(new byte[0]);
    }

    @Test(expectedExceptions = JPlantUmlException.class)
    public void should_throw_exception_when_file_not_writable() throws IOException {
        File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), null);
        tempFile.deleteOnExit();
        tempFile.setReadOnly();
        writer = new ImageFileWriter(tempFile.getAbsolutePath());
        writer.apply(new byte[0]);
    }

    @Test
    public void should_write_to_file() throws IOException {
        File tempFile = File.createTempFile(String.valueOf(System.currentTimeMillis()), null);
        tempFile.deleteOnExit();
        writer = new ImageFileWriter(tempFile.getAbsolutePath());
        String text = "dummy test";
        writer.apply(text.getBytes());
        Assert.assertEquals(FileUtils.readFileToString(tempFile), text);
    }
}
