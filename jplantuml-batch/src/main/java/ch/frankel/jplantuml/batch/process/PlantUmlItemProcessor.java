package ch.frankel.jplantuml.batch.process;

import ch.frankel.jplantuml.batch.model.NamedBytes;
import ch.frankel.jplantuml.batch.model.NamedContent;
import ch.frankel.jplantuml.online.OnlineJPlantUml;
import ch.frankel.jplantuml.online.Settings;
import org.springframework.batch.item.ItemProcessor;

public class PlantUmlItemProcessor extends OnlineJPlantUml implements ItemProcessor<NamedContent, NamedBytes> {

    public PlantUmlItemProcessor(Settings settings) {
        super(settings);
    }

    @Override
    public NamedBytes process(NamedContent item) throws Exception {
        byte[] bytes = apply(item.content());
        return new NamedBytes(item.name(), bytes);
    }
}
