package chen.msg;


public class BaseMsg {
    private short head;
    private short code;
    private byte[] data;

    public BaseMsg() {
    }

    public short getHead() {
        return this.head;
    }

    public void setHead(short head) {
        this.head = head;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}