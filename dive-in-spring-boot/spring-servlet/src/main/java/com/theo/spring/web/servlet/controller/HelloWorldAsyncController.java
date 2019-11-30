package com.theo.spring.web.servlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author huangsuixin
 * @date 2019/09/26 20:39
 * @description Hello Wold 异步 {@link RestController}
 */
@RestController
@EnableScheduling
public class HelloWorldAsyncController {

    private final BlockingQueue<DeferredResult<String>> queue = new ArrayBlockingQueue<>(5);

    private final Random random = new Random();

    @Scheduled(fixedRate = 5000)
    public void process() throws InterruptedException {
        DeferredResult<String> result = null;
        do {
            result = queue.take();
            long timeout = random.nextInt(100);
            // 模拟等待时间， RPC 或者 DB 查询
            Thread.sleep(timeout);
            // 计算结果
            result.setResult("Hello, World!");
            println("执行计算结果, 消耗 ： " + timeout + "ms.");
        } while (result != null);
    }

    @GetMapping("hello-world")
    public DeferredResult<String> helloWorld() {

        DeferredResult<String> result = new DeferredResult<>(50L);

        // 入队操作
        queue.offer(result);

        println("Hello World");

        result.onCompletion(() -> println("执行结束"));

        result.onTimeout(() -> println("执行超时"));
        return result;
    }

    @GetMapping("callable-hello-world")
    public Callable<String> callableHelloWorld() {
        final long startTime = System.currentTimeMillis();

        println("Hello, World ");

        return () -> {
            long cosTime = System.currentTimeMillis() - startTime;
            println("执行计算结果, 消耗 ： " + cosTime + "ms.");
            return "Hello, World";
        };
    }

    @GetMapping("completion-stage")
    public CompletionStage<String> completionStage() {
        final long startTime = System.currentTimeMillis();

        println("hello world");

        return CompletableFuture.supplyAsync(() -> {
            long cosTime = System.currentTimeMillis() - startTime;
            println("执行计算结果, 消耗 ： " + cosTime + "ms.");
            return "hello world";
        });
    }

    private static void println(Object Object) {

        String name = Thread.currentThread().getName();
        System.out.println("HelloWorldAsyncController[" + name + "]" + Object);
    }
}
