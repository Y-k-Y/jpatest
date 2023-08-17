package com.y2klabs.jpatest.global.util;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

@Slf4j
public class HibernateUtils {

    private static final PersistenceUtil persistenceUnitUtil = Persistence.getPersistenceUtil();

    private HibernateUtils() {}

    public static boolean isLoaded(Object obj) {
        if (obj == null) return false;

        return persistenceUnitUtil.isLoaded(obj);
    }
}
