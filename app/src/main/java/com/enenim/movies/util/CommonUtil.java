package com.enenim.movies.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by enenim on 4/20/17.
 */

public class CommonUtil {
    public static <T> List<T> convertIteratorToList(Iterator<T> iter) {
        List<T> copy = new ArrayList<T>();
        while (iter.hasNext())
            copy.add(iter.next());
        return copy;
    }
}
