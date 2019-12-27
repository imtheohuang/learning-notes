package pers.theo.effectivejava.sample.serviceLoader;

/**
 * 服务提供者具体实现类
 *
 * @author theohuang
 * @date 2019/12/27
 */
public class UserServiceProviderImpl implements UserServiceProvider {
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    static {
        ServiceManager.registerProvider("登录注册", new UserServiceProviderImpl());
    }
}
