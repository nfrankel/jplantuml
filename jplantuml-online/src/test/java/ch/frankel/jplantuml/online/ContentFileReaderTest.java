package ch.frankel.jplantuml.online;

import ch.frankel.jplantuml.api.JPlantUmlException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class ContentFileReaderTest {

    private ContentFileReader reader;

    @BeforeMethod
    protected void setUp() {
        reader = new ContentFileReader();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void should_throw_exception_when_file_null() {
        reader.apply(null);
    }

    @Test(expectedExceptions = JPlantUmlException.class)
    public void should_throw_exception_when_file_not_exists() {
        File file = new File("dummy");
        reader.apply(file);
    }
}
