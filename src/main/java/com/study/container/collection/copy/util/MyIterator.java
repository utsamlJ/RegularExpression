package com.study.container.collection.copy.util;

/**
 * @USER: jys
 * @DATE: 2023/5/24
 * @TIME: 17:59
 * @DAY_NAME_FULL: 星期三
 */

/**
 * 抄 java.util.Iterator 迭代器写的
 *
 * @param <E> 泛型
 */
public interface MyIterator<E> {

    /**
     * 判断后面有没有元素了，如果有返回 true ，否则返回 false
     *
     * @return true(后面还有元素)/false(后面没有元素了)
     */
    boolean hasNext();

    /**
     * 返回迭代的下一个元素
     *
     * @return 返回下一个元素
     */
    E next();

    /**
     * 移除集合中next返回的元素。
     * 该方法必须在每次迭代中，调用next方法之后才能使用。
     * 每一次调用next方法后，只能使用一次该方法(remove方法)
     * (后面的话未证实)(移除下一个元素，并且不影响迭代)
     * <p>
     * 会有两种异常：
     * UnsupportedOperationException - 表明不支持移除操作
     * IllegalStateException - 在每一次迭代中，调用next方法前使用了该方法。
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * 该方法没抄全，因为做的操作没看懂。
     * 先挖个坑，后面再填
     */
    default void forEachRemaining() {

    }

}
