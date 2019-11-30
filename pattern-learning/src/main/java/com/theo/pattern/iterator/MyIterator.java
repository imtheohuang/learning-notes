package com.theo.pattern.iterator;

/**
 * 自定义的迭代器
 * @author huangsuixin
 * @date 2019/11/30 14:19
 */
public interface MyIterator {
    /**
     * 将游标指向第一个
     */
    void first();

    /**
     * 将游标指向下一个
     */
    void next();

    /**
     * 判断是否有下一个
     * @return boolean
     */
    boolean hasNext();

    /**
     * 判断是否为第一个
     * @return boolean
     */
    boolean isFirst();

    /**
     * 判断是否为最后一个
     * @return boolean
     */
    boolean isLast();

    /**
     * 获取当前元素
     * @return {@link Object}
     */
    Object getCurrentObj();
}
