package com.joutvhu.training.batch.step;

import com.joutvhu.training.batch.model.domain.UserInfo;
import com.joutvhu.training.batch.model.dto.UserDto;
import com.joutvhu.training.batch.step.processor.FileProcessor;
import com.joutvhu.training.batch.step.tasklet.ThreadTasklet;
import com.joutvhu.training.batch.step.tasklet.LogTasklet;
import com.joutvhu.training.batch.step.writer.FileWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
            @Qualifier("userReader") ItemReader<UserInfo> reader,
            FileProcessor processor,
            FileWriter writer
    ) {
        return stepBuilderFactory
                .get("fileStep")
                .<UserInfo, UserDto>chunk(chunkSize)
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

    @Bean(name = "threadStep")
    public Step getThreadStep(
            ThreadTasklet tasklet
    ) {
        return stepBuilderFactory
                .get("threadStep")
                .tasklet(tasklet)
                .build();
    }
}
