## 服务提供者框架

### 定义

什么是服务提供者框架？

服务提供者框架是指这一个系统:多个服务提供者来实现一个服务，系统为客户端的服务提供者提供多个实现，并且把他们从多个实现中解耦出来。咋一看这个定义，一脸懵逼。那么我们就来看一下他们之间的关系图吧。

### 讲解

服务提供框架有4个组件，依次是服务接口，服务器提供者接口，提供者注册API，服务访问API。

- 服务接口
  在服务接口中定义一些提供具体服务的方法，假设我们要提供一个注册登录的服务`UserService`。那么这个服务接口中肯定有`login()` , `register()` 方法。我们再去创建这个服务接口的具体实现类去实现login(),register()方法。
- 服务提供者接口 
  在服务提供者接口里，就是去定义提供什么样子的服务的方法。我们上面创建了一个提供“注册登录”的服务。那么这里我们肯定要去定义一个能获取“注册登录”的服务的方法，假设是`getUserService()`,返回类型是`UserService`。然后在去创建服务提供者接口的具体实现类去这个 `getUserService()` ，那么我们怎么去实现呢？我们只需要返回一个 `UserService` 的具体实现类即可。
- 提供者注册API:
  其实是服务提供者接口的具体实现类里面去注册这个API，在类中的静态初始化块中去注册API，因为你只有注册了API，才能享有享受服务的权利。这些注册过的服务集中交给 `ServiceManager` 管理。

- 服务访问API

  既然已经注册了API，那么我们可以向`ServiceManager` 申请具体的服务，可以获得具体服务的实例，就可以调用服务里面的方法。服务访问API是“灵活的静态工厂”，它构成了服务提供者框架的基础。 

### JDBC 

为什么要讲到JDBC?其实我们可以仔细回想一下JDBC的基本步骤，是不是和我们上面的步骤类似。没错，JDBC也是用到了服务提供者框架。 

``` java
Class.forName("com.mysql.jdbc.Driver");   
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");
```

- `Class.forName()` 这个方法其实是利用反射的方法获得服务提供者接口的具体实现类。`Connection` 就是服务接口，`DriverManager`就是我们上面提供的服务管理类，`getConnection()`就是从服务管理类里面获取指定名字的且已经注册过的服务。

- `DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","1234");`这个方法返回的是一个服务接口的具体实现类的实例，把这个实现类的实例赋值给`Connection`的服务接口，就可以调用具体服务的里面的一些方法，接口回调嘛。
- 观看JDBC源码，JDBC中的服务接口具体实现类中其实调用了`DriverManager.registerDriver()`静态工厂方法去注册服务的api。`java.sql.Driver`就是服务提供者接口，`com.mysql.jdbc.Driver`是服务提供者具体的实现类。

### Java 代码实现



  