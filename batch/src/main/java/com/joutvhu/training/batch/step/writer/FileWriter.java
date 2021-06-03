package com.joutvhu.training.batch.step.writer;

import com.joutvhu.training.batch.dao.UserDao;
import com.joutvhu.training.batch.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class FileWriter implements ItemWriter<UserDto> {
    private final UserDao userDao;

    @Override
    public void write(List<? extends UserDto> items) throws Exception {
        log.debug("Insert {} users", items.size());
        userDao.insert((List<UserDto>) items);
    }
}
