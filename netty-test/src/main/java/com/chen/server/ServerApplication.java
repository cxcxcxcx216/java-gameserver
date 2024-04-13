package com.chen.server;

public class ServerApplication {
    public static void main(String[] args) throws Exception {
        int port = 8080;
//        if (args.length > 0) {
//            port = Integer.parseInt(args[0]);
//        }
        new EchoServer(port).run();
    }

}
