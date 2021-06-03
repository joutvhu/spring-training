package com.joutvhu.training.batch.step.reader;

import com.joutvhu.training.batch.model.domain.UserInfo;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class UserReaderProvider {
    @Value("${app.file.input.path}")
    private String fileInput;

    @Bean("userReader")
    public FlatFileItemReader reader() {
        return new FlatFileItemReaderBuilder<>()
                .name("userReader")
                .resource(new ClassPathResource(fileInput))
                .delimited()
                .names(new String[]{"username", "firstName", "lastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper() {{
                    setTargetType(UserInfo.class);
                }})
                .build();
    }
}
