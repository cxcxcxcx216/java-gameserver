package com.chen.processor;

import com.chen.action.BaseAction;
import com.chen.annotation.Action;
import com.chen.annotation.Processor;
import com.chen.config.PkgConfig;
import com.chen.entity.Pair;
import com.chen.msg.ProtoMsg;

import com.chen.utils.PackageScanner;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class Router {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);
    private static final Map<Integer,Processer> processorMap = new ConcurrentHashMap<>();

    private static final Map<Integer, Pair<Integer,Class<?>>> actionMap = new ConcurrentHashMap<>();

    /**
     * 注册消息处理器
     * @param processorId
     * @param processer
     */
    public void register(int processorId,Processer processer){
        logger.info("注册处理器{}==={}",processorId,processer);
        processorMap.put(processorId,processer);
    }


    /**
     * 注册对应消息
     * @param msgId
     * @param action
     * @param processorId
     */
    public void register(int msgId,int processorId,Class action){
        logger.info("注册对应的消息{}==={}",msgId,msgId);
        Pair<Integer, Class<?>> actionMapping = actionMap.getOrDefault(msgId, null);
        actionMapping = new Pair<Integer, Class<?>>(processorId,action);
        actionMap.put(msgId,actionMapping);
    }

    /**
     * 初始化消息处理器
     */
    public void init() throws Exception {
        Set<Class<?>> actions = PackageScanner.scan(PkgConfig.actionPkg);
        Iterator<Class<?>> iterator = actions.iterator();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            Action annotation = clazz.getAnnotation(Action.class);
            int msgId = annotation.msgId();
            int processorId = annotation.processorId();
            register(msgId,processorId, clazz);
            log.info("注册消息处理器:{},{},{}",msgId,processorId,clazz);
        }


        //注册对应的处理器
        register(101,new LoginProcessor());

//        Set<Class<?>> processors = PackageScanner.scan(PkgConfig.actionPkg);
//        Iterator<Class<?>> processorClazzs = processors.iterator();
//        while (processorClazzs.hasNext()) {
//            Class<?> clazz = processorClazzs.next();
//            Processor processor = clazz.getAnnotation(Processor.class);
//            int processorId = processor.processorId();
//            //注册对应的处理器
//            register(processorId,new LoginProcessor());
//        }

    }

    public static void dispatcher(ProtoMsg msg) throws InstantiationException, IllegalAccessException {
        int code = msg.getCode();
        Pair<Integer, Class<?>> classMap = actionMap.getOrDefault(code, null);

        Integer processorId = classMap.getFirst();
        Class<?> clazzAction = classMap.getSecond();
        BaseAction action = (BaseAction)clazzAction.newInstance();

        Processer processer = processorMap.get(processorId);
        processer.addTask(action);

    }
}
