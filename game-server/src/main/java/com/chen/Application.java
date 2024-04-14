package com.chen;


import com.chen.processor.Router;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.util.Properties;


@Slf4j
public class Application {
    public Application() {
    }

    public static void main(String[] args) throws Exception {

        String arg = "E:\\java-gameserver\\java-gameserver\\config\\server.properties";
        if (args.length>0) {
            log.info(args[0]);
            arg = args[0];
        }

        Properties properties = new Properties();
        properties.load(new FileInputStream(arg));
        String property = properties.getProperty("server.port");
        Integer port = Integer.valueOf(property);
        log.info("port:{}",port);

        Router router = new Router();
        router.init();
        BootServer bootServer = new BootServer();
        bootServer.createServer(port);
    }
}