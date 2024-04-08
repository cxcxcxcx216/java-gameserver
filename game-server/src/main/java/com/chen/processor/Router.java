package com.chen.processor;

import com.chen.action.BaseAction;
import com.chen.action.LoginAction;
import com.chen.msg.ProtoMsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Router {

    private static final Logger logger = LoggerFactory.getLogger(Router.class);
    private static final Map<Integer,Processer> processorMap = new ConcurrentHashMap<>();

    private static final Map<Integer,Map<Integer,Class>> actionMap = new ConcurrentHashMap<>();

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
        Map<Integer, Class> classMap = actionMap.getOrDefault(msgId, null);
        if (classMap ==null) {
            classMap = new HashMap<>();
            classMap.put(processorId,action);
            actionMap.put(msgId,classMap);
        }else {
            classMap.put(processorId,action);
            actionMap.put(msgId,classMap);
        }
    }

    /**
     * 初始化消息处理器
     */
    public void init(){
        //注册对应的处理器
        register(101,new LoginProcessor());

        //注册对应处理器的对应消息
        register(1001,101, LoginAction.class);

    }

    public static void dispatcher(ProtoMsg msg) throws InstantiationException, IllegalAccessException {
        int code = msg.getCode();
        Map<Integer, Class> classMap = actionMap.getOrDefault(code, null);
        if (classMap ==null) {
            logger.warn("消息号错误！");
        }else {
            Iterator<Map.Entry<Integer, Class>> iterator = classMap.entrySet().iterator();
            Integer key;
            Class value;
            while (iterator.hasNext()) {
                Map.Entry<Integer, Class> next = iterator.next();
                key = next.getKey();
                value = next.getValue();

                BaseAction action = (BaseAction)value.newInstance();
                Processer processer = processorMap.get(key);
                processer.addTask(action);
                break;
            }

        }
    }
}
