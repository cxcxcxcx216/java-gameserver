package com.chen.processor;

import com.chen.action.BaseAction;
import com.chen.annotation.Action;
import com.chen.config.PkgConfig;
import com.chen.entity.Pair;
import com.chen.msg.ProtoMsg;

import com.chen.net.Session;
import com.chen.utils.PackageScanner;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class Router {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);
    private static final Map<Integer, Processor> processorMap = new ConcurrentHashMap<>();

    private static final Map<Integer, Pair<Integer,Class<?>>> actionMap = new ConcurrentHashMap<>();

    /**
     * 注册消息处理器
     * @param processorId
     * @param processor
     */
    public void register(int processorId, Processor processor){
        logger.info("注册处理器{}==={}",processorId, processor);
        processorMap.put(processorId, processor);
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
    public void init() {
        Set<Class<?>> actions = PackageScanner.scan(PkgConfig.actionPkg,Action.class);
        Iterator<Class<?>> iterator = actions.iterator();
        while (iterator.hasNext()) {
            Class<?> clazz = iterator.next();
            Action annotation = clazz.getAnnotation(Action.class);
            int msgId = annotation.msgId();
            int processorId = annotation.processorId();
            register(msgId,processorId, clazz);
            log.info("注册消息处理器:{},{},{}",msgId,processorId,clazz);
        }


        Set<Class<?>> processors = PackageScanner.scan(PkgConfig.actionPkg, com.chen.annotation.Processor.class);
        processors.forEach(clazz -> {
            com.chen.annotation.Processor annotation = clazz.getAnnotation(com.chen.annotation.Processor.class);
            int processorId = annotation.processorId();
            try {
                register(processorId, (Processor) clazz.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    public static void dispatcher(ProtoMsg msg, Session session) throws InstantiationException, IllegalAccessException {
        int msgId = msg.getMsgId();
        Pair<Integer, Class<?>> classMap = actionMap.getOrDefault(msgId, null);
        Integer processorId = classMap.getFirst();
        Class<?> clazzAction = classMap.getSecond();
        BaseAction action = (BaseAction)clazzAction.newInstance();
        action.setMsg(msg);
        action.setSession(session);
        Processor processor = processorMap.get(processorId);
        processor.addTask(action);

    }
}
