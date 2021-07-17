package com.joutvhu.training.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobProvider {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private JobListener jobListener;

    @Autowired
    @Qualifier("fileStep")
    private Step fileStep;

    @Autowired
    @Qualifier("logStep")
    private Step logStep;

    @Autowired
    @Qualifier("threadStep")
    private Step threadStep;

    @Bean
    public Job getJob() {
        return jobBuilderFactory
                .get("training")
                .listener(jobListener)
                .start(fileStep)
                .next(logStep)
                .next(threadStep)
                .build();
    }
}
