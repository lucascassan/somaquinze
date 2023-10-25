package somaquinze;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class SomaQuinzeClientMain {

    public SomaQuinzeClientMain(String serverAddress, int serverPort, SomaQuinzeClient caller) throws UnknownHostException, IOException {
        this.socket = new Socket(serverAddress, serverPort);
        //this.socket.setKeepAlive(true);
        handler = new SomaQuinzeClientHandler(socket, caller);
        this.handler.start();
        this.output = new PrintWriter(this.socket.getOutputStream(), true);
    }

    public void writeMessage(String outMessage) {
        this.output.println(outMessage);
    }

    public void closeConnection() throws IOException {
        this.handler.stop();
        this.output.close();
        this.socket.close();
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            this.closeConnection();
        } finally {
            super.finalize();
        }
    }


    private SomaQuinzeClientHandler handler;
    private Socket socket;
    private PrintWriter output;
}