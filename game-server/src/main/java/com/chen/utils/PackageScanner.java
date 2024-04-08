package com.chen.utils;

import com.chen.annotation.Action;
import org.reflections.Reflections;

import java.util.Set;

public class PackageScanner {

    public static Set<Class<?>> scan(String packageName){

        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Action.class);
        return classes;

    }
}
