package com.theo.pattern.iterator;

/**
 * @author huangsuixin
 * @date 2019/11/30 14:30
 */
public class Client {

    public static void main(String[] args) {
        MyAggregate myAggregate = new MyAggregate();

        myAggregate.addObject("aa");
        myAggregate.addObject("bb");
        myAggregate.addObject("cc");

        MyIterator iterator = myAggregate.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.getCurrentObj());
            iterator.next();
        }
    }
}
