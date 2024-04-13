package com.chen;


import com.chen.action.BaseAction;
import com.chen.annotation.Action;
import com.chen.config.PkgConfig;
import com.chen.processor.Router;
import com.chen.utils.PackageScanner;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Set;


@Slf4j
public class Application {
    public Application() {
    }

    public static void main(String[] args) throws Exception {

        Router router = new Router();
        router.init();
        BootServer bootServer = new BootServer();
        bootServer.createServer(9001);
    }
}