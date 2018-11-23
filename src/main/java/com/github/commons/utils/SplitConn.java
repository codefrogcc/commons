package com.github.commons.utils;

import java.util.ArrayList;
import java.util.List;

public class SplitConn {

    public static <T> List<List<T>> splitList(List<T> list, int n) {
        List<List<T>> strList = new ArrayList<>();
        if (list == null) return strList;
        int size = list.size();
        int quotient = size / n; // 商数
        int remainder = size % n; // 余数
        int offset = 0; // 偏移量
        int len = quotient > 0 ? n : remainder; // 循环长度
        int start = 0;    // 起始下标
        int end = 0;    // 结束下标
        List<T> tempList = null;
        for (int i = 0; i < len; i++) {
            if (remainder != 0) {
                remainder--;
                offset = 1;
            } else {
                offset = 0;
            }
            end = start + quotient + offset;
            tempList = list.subList(start, end);
            start = end;
            strList.add(tempList);
        }
        return strList;
    }
}

