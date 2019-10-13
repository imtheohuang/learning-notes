package com.theo.pattern.composite;

/**
 * @author huangsuixin
 * @date 2019/10/10 17:10
 * @description 抽象组件
 */
public interface Component {

    void operation();
}


/**
 * 叶子组件
 */
interface Leaf extends Component {

}

/**
 * 容器组件
 */
interface Composite extends Component {

    void add(Component component);

    void remove(Component component);

    Component getChild(int index);
}


