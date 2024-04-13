package com.chen.client;

public class ClientApplication {

    public static void main(String[] args) throws Exception {
        String host = "localhost";
        int port = 8080;
//        if (args.length > 0) {
//            host = args[0];
//        }
//        if (args.length > 1) {
//            port = Integer.parseInt(args[1]);
//        }
        new EchoClient(host, port).run();
    }
}
