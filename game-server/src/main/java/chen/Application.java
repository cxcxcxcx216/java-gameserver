package chen;


import chen.processor.Router;

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