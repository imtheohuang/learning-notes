# 2019/08/15 学习笔记

- Fast - fail  assert

- 使用语言特性，一种工厂模式
```java
public class Java5Demo {

    public static void main(String[] args) {

        String[] values = of("hello", "world");

        Integer[] data = of(1, 2, 3, 4);
    }

    // 使用语言特性，工厂模式
    @SafeVarargs
    private static <T> T[] of(T... values) {
        return values;
    }
}

```

- try-with-resources特性  
实现AutoCloseable接口的资源，可以通过改语法实现自动关闭流
```java
        // before
        FileInputStream read = null;
        try {
            read = new FileInputStream("D://");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // after
        // 在try小括号里面初始化流，在大括号里面执行操作，流会自动关闭
        try (FileInputStream read = new FileInputStream("D://")) {
            read.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
```

- @Override加与不加要根据使用场景来判断，不加可以提高兼容性
```java
public interface EchoService{
    @Deprecated
    String echo(String message){
        return null;
    }
    String echo(String message, String ... other){
        return null;
    }
}

public class EchoServiceImpl implements EchoService {
        // 不加@Override以提高兼容性
        String echo(String message){
            return null;
        }
        
        @Override
        String echo(String message, String ... other){
            return null;
        }
}
```
