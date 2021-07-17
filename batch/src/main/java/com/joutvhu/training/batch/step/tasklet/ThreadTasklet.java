package com.joutvhu.training.batch.step.tasklet;

import com.joutvhu.training.batch.service.ThreadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class ThreadTasklet implements Tasklet {
    private final ThreadService threadService;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.debug("Start ThreadTasklet");
        threadService.execute();
        log.debug("End ThreadTasklet");
        return RepeatStatus.FINISHED;
    }
}
