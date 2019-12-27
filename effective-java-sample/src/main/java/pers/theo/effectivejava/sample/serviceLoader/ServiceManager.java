package pers.theo.effectivejava.sample.serviceLoader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理类
 *
 * @author theohuang
 * @date 2019/12/27
 */
public class ServiceManager {
    private ServiceManager() {

    }

    private static final Map<String, UserServiceProvider> PROVIDERS = new ConcurrentHashMap<String, UserServiceProvider>();

    public static void registerProvider(String name, UserServiceProvider provider) {
        PROVIDERS.put(name, provider);
    }

    public static UserService getService(String name) {
        UserServiceProvider provider = PROVIDERS.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("No provider register with name " + name);
        }
        return provider.getUserService();
    }
}
