package com.theo.pattern.bridge;

/**
 * @author huangsuixin
 * @date 2019/10/10 16:40
 * @description 品牌
 */
public interface Brand {
    void sale();
}

class Lenovo implements Brand {

    @Override
    public void sale() {
        System.out.println("销售联想");
    }
}

class Dell implements Brand {

    @Override
    public void sale() {
        System.out.println("销售戴尔");
    }
}
