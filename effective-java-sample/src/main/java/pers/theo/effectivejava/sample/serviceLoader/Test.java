package pers.theo.effectivejava.sample.serviceLoader;

/**
 * @author theohuang
 * @date 2019/12/27
 */
public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("pers.theo.effectivejava.sample.serviceLoader.UserServiceProviderImpl");
            UserService userService = ServiceManager.getService("登录注册");

            userService.register();
            userService.login();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
