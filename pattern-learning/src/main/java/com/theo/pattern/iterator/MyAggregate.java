package com.theo.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的聚合类
 *
 * @author huangsuixin
 * @date 2019/11/30 14:23
 */
public class MyAggregate {
    private List<Object> list = new ArrayList<>();

    public MyAggregate() {
    }

    public void addObject(Object object) {
        this.list.add(object);
    }

    public void removeObject(Object o) {
        this.list.remove(o);
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public MyIterator createIterator() {
        return new ConcreteIterator();
    }

    /**
     * 使用内部类定义迭代器
     */
    private class ConcreteIterator implements MyIterator {
        /**
         * 游标，用于记录遍历的位置
         */
        private int cursor;

        @Override
        public void first() {
            cursor = 0;
        }

        @Override
        public void next() {
            if (cursor < list.size() - 1) {
                cursor++;
            }
        }

        @Override
        public boolean hasNext() {
            return cursor < list.size() - 1;
        }

        @Override
        public boolean isFirst() {
            return cursor == 0;
        }

        @Override
        public boolean isLast() {
            return cursor == list.size() - 1;
        }

        @Override
        public Object getCurrentObj() {
            return list.get(cursor);
        }
    }
}

