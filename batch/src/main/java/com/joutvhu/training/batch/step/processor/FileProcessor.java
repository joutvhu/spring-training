package com.joutvhu.training.batch.step.processor;

import com.joutvhu.training.batch.model.domain.UserInfo;
import com.joutvhu.training.batch.model.dto.UserDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class FileProcessor implements ItemProcessor<UserInfo, UserDto> {
    @Override
    public UserDto process(UserInfo item) throws Exception {
        log.debug("Processing user: {}", item.getUsername());
        return UserDto.builder()
                .username(item.getUsername())
                .fullName(item.getFirstName() + " " + item.getLastName())
                .password("123456")
                .build();
    }
}
