package chen.action;


import chen.annotation.Action;
import chen.entity.Player;
import chen.msg.ProtoMsg;

@Action(msgId = 1001,processorId = 101,desc = "登录处理器")
public class LoginAction extends BaseAction<Player>{

    @Override
    public void doAction(ProtoMsg msg, Player player) {

    }
}
