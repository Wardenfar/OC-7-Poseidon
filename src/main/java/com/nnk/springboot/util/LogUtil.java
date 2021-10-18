package com.nnk.springboot.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class LogUtil {

    public static void logEntityAdded(String entity, Integer id) {
        log.info("{} with id={} added/updated", entity, id);
    }

    public static void logEntityDeleted(String entity, Integer id) {
        log.info("{} with id={} deleted", entity, id);
    }
}
