package com.study.container.collection.copy.util;

/**
 * @USER: jys
 * @DATE: 2023/5/25
 * @TIME: 10:45
 * @DAY_NAME_FULL: 星期四
 */

import java.util.Iterator;

/**
 * 抄 java.lang.Iterable 接口
 * 没抄完，埋个坑
 *
 * @param <T>
 */
public interface MyIterable<T> {

    /**
     * 返回一个 T 类型的迭代器
     *
     * @return 一个迭代器
     */
    Iterator<T> iterator();

}
