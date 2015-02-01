package ch.frankel.jplantuml.batch.config;

import ch.frankel.jplantuml.batch.process.FileContentItemProcessor;
import ch.frankel.jplantuml.batch.process.FileExtensionStripperProcessor;
import ch.frankel.jplantuml.batch.process.FileItemReader;
import ch.frankel.jplantuml.batch.process.FileItemWriter;
import ch.frankel.jplantuml.batch.process.GlobalParametersEnricherProcessor;
import ch.frankel.jplantuml.batch.process.PlantUmlItemProcessor;
import ch.frankel.jplantuml.batch.model.NamedBytes;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@Configuration
@EnableBatchProcessing
@EnableConfigurationProperties(BatchSettings.class)
public class PlantUmlConfig {

    @Autowired
    private BatchSettings settings;

    @Bean
    public ItemReader<Path> reader() {
        return new FileItemReader(Paths.get(settings.directoryIn()), new ConfigurableMimeFileTypeMap());
    }

    @Bean
    @StepScope
    public ItemProcessor<Path, NamedBytes> processor(@Value("#{jobParameters[globalParams]}") String globalParams) {
        CompositeItemProcessor<Path, NamedBytes> processors = new CompositeItemProcessor<>();
        FileContentItemProcessor fileContentProcessor = new FileContentItemProcessor();
        GlobalParametersEnricherProcessor paramsEnricherProcessor = new GlobalParametersEnricherProcessor(globalParams);
        FileExtensionStripperProcessor fileExtensionStripperProcessor = new FileExtensionStripperProcessor();
        PlantUmlItemProcessor plantUmlProcessor = new PlantUmlItemProcessor(settings);
        processors.setDelegates(Arrays.asList(fileContentProcessor, paramsEnricherProcessor, fileExtensionStripperProcessor,
            plantUmlProcessor));
        return processors;
    }

    @Bean
    public ItemWriter<NamedBytes> writer() throws IOException {
        Path outputDirectory = Paths.get(settings.directoryOut());
        if (!Files.exists(outputDirectory)) {
            Files.createDirectory(outputDirectory);
        }
        return new FileItemWriter(settings.directoryOut());
    }

    @Bean
    public Job plantUmlJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("plantUmlJob")
            .incrementer(new RunIdIncrementer())
            .flow(s1)
            .end()
            .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Path> reader,
        ItemWriter<NamedBytes> writer, ItemProcessor<Path, NamedBytes> processor) {
        return stepBuilderFactory.get("step1")
            .<Path, NamedBytes>chunk(5)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }
}
