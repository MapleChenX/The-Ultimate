package personal.MapleChenX.tcp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import personal.MapleChenX.tcp.config.ConfigReader;
import personal.MapleChenX.tcp.server.WebsocketServer;

@Component
public class Starter {
    public static void main(String[] args) {

        // String 转为 int
        String port = ConfigReader.getProperty("port");
        System.out.println("port: " + port);
        new WebsocketServer(Integer.parseInt(port));
    }
}