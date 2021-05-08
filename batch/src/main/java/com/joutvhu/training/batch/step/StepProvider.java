package com.joutvhu.training.batch.step;

import com.joutvhu.training.batch.step.processor.FileProcessor;
import com.joutvhu.training.batch.step.reader.FileReader;
import com.joutvhu.training.batch.step.tasklet.LogTasklet;
import com.joutvhu.training.batch.step.writer.FileWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepProvider {
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Value("${app.training.chunk.size}")
    private int chunkSize;

    @Bean(name = "fileStep")
    public Step getFileStep(
            FileReader reader,
            FileProcessor processor,
            FileWriter writer
    ) {
        return stepBuilderFactory
                .get("fileStep")
                .<String, String>chunk(chunkSize)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean(name = "logStep")
    public Step getLogStep(
            LogTasklet tasklet
    ) {
        return stepBuilderFactory
                .get("logStep")
                .tasklet(tasklet)
                .build();
    }
}
