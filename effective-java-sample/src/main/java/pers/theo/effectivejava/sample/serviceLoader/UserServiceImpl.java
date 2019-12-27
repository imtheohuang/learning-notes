package pers.theo.effectivejava.sample.serviceLoader;

/**
 * 服务接口实现类
 *
 * @author theohuang
 * @date 2019/12/27
 */
public class UserServiceImpl implements UserService {

    public void login() {
        System.out.println("login invoke");
    }

    public void register() {
        System.out.println("register invoke");
    }
}
