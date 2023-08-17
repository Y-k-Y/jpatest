package com.y2klabs.jpatest.global.util;

import java.util.Collection;

public class DtoUtils {

    private DtoUtils() {}

    public static boolean isNotYetLoadedOrEmpty(Collection<?> entity) {
        return !HibernateUtils.isLoaded(entity) || entity.isEmpty();
    }
}
