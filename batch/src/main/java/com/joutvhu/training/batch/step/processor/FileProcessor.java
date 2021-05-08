package com.joutvhu.training.batch.step.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class FileProcessor implements ItemProcessor<String, String> {
    @Override
    public String process(String item) throws Exception {
        return null;
    }
}
