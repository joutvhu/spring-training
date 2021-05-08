package com.joutvhu.training.batch.step.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
    }
}
