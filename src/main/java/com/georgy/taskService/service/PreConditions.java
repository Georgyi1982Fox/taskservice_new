package com.georgy.taskService.service;

import com.georgy.taskService.exceptions.EntityNotFoundException;

public final class PreConditions{
    private PreConditions(){
        throw new AssertionError();
    }
    public static <T> T checkEntityExists(final T entity){
        if (entity == null){
            throw new EntityNotFoundException();
        }
        return entity;
    }
    public static void checkEntityExists(final boolean entityExists){
        if (!entityExists) {
            throw new EntityNotFoundException();
        }
    }
}
