package ch.frankel.jplantuml.online;

import ch.frankel.jplantuml.api.JPlantUml;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.testng.Assert.assertEquals;

public class OnlinePlantUmlIT {

    private JPlantUml<String, byte[]> plantUml;

    @BeforeMethod
    protected void setUp() {
        plantUml = new OnlineJPlantUml(new SimpleSettings());
    }

    @DataProvider
    public Object[][] data() {
        Object[][] datas = new Object[3][2];
        datas[0] = new String[] { "A --> B", "/OnlinePlantUmlIT-sequence.png" };
        datas[1] = new String[] { "class A", "/OnlinePlantUmlIT-class.png" };
        datas[2] = new String[] { "component A", "/OnlinePlantUmlIT-component.png" };
        return datas;
    }

    @Test(dataProvider = "data")
    public void should_create_corresponding_byte_array(String description, String file) throws IOException {
        InputStream stream = getClass().getResourceAsStream(file);
        byte[] reference = new byte[stream.available()];
        stream.read(reference);
        byte[] result = plantUml.apply(description);
        assertEquals(result, reference);
    }
}
