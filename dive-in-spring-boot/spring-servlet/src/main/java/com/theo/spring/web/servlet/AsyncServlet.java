package com.theo.spring.web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author huangsuixin
 * @date 2019/09/27 10:26
 * @description 异步 Servlet
 */
@WebServlet(
        asyncSupported = true,
        name = "asyncServlet",
        urlPatterns = "/async-servlet"
)
public class AsyncServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 判断是否支持异步
        if (req.isAsyncSupported()) {
            // 创建 AsyncContext
            AsyncContext asyncContext = req.startAsync();
            asyncContext.addListener(new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent asyncEvent) throws IOException {
                    println("执行完成");
                }

                @Override
                public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                    println("执行超时");

                }

                @Override
                public void onError(AsyncEvent asyncEvent) throws IOException {
                    println("执行错误");

                }

                @Override
                public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                    println("执行开始执行");

                }
            });

            ServletResponse servletResponse = asyncContext.getResponse();
            servletResponse.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = servletResponse.getWriter();

            writer.println("Hello, World");
            writer.flush();

        }
    }



    private static void println(Object Object) {

        String name = Thread.currentThread().getName();
        System.out.println("AsyncServlet[" + name + "]" + Object);
    }
}
