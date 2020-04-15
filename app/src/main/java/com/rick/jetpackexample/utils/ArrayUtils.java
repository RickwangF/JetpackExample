package com.rick.jetpackexample.utils;

import java.lang.reflect.Array;

public class ArrayUtils {
    public static boolean isEmpty(Array array) {
        return array == null || Array.getLength(array) == 0;
    }
}
