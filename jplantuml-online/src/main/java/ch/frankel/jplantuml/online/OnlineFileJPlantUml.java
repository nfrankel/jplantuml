package ch.frankel.jplantuml.online;

import ch.frankel.jplantuml.api.JPlantUml;

import java.io.File;
import java.util.function.Function;

public class OnlineFileJPlantUml implements JPlantUml<File, File> {

    private final JPlantUml<String, byte[]> plantUml;
    private final Function<File, String> textFileReader;
    private final Function<byte[], File> imageFileWriter;

    public OnlineFileJPlantUml(Settings settings, String path) {
        this(settings, new ContentFileReader(), new ImageFileWriter(path));
    }

    OnlineFileJPlantUml(Settings settings, Function<File, String> textFileReader, Function<byte[], File> imageFileWriter) {
        plantUml = new OnlineJPlantUml(settings);
        this.textFileReader = textFileReader;
        this.imageFileWriter = imageFileWriter;
    }

    @Override
    public File apply(File description) {
        return textFileReader.andThen(plantUml.andThen(imageFileWriter)).apply(description);
    }
}
