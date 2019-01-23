package com.mycurdpro.common.activiti;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

public class UuidGenerator implements IdGenerator {

    @Override
    public String getNextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}