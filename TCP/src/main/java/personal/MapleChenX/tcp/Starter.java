package personal.MapleChenX.tcp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import personal.MapleChenX.tcp.server.WebsocketServer;

@Component
public class Starter {

    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        // 获取Spring的ApplicationContext
        ApplicationContext context = SpringApplication.run(Starter.class, args);
        // 从ApplicationContext中获取Starter的实例
        Starter starter = context.getBean(Starter.class);
        // 使用注入的端口值
        System.out.println(starter.port);
        new WebsocketServer(starter.port);
    }
}