package chen.action;

import chen.entity.Player;
import chen.msg.ProtoMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseAction<T> implements Runnable{

    private T t;
    private ProtoMsg msg;
    public void doAction(ProtoMsg msg, T t){

    }

    @Override
    public void run() {
        doAction(msg,t);
    }
}
