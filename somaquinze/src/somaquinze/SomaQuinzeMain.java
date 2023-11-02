package somaquinze;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SomaQuinzeMain extends Thread {

    private List<SomaQuinzeServerConnection> clientes;
    private ServerSocket server;
    
    public String[] board = new String[9];
    public String turno = "1";
    public int pontuacaoX;
    public int pontuacaoO;

    public SomaQuinzeMain(int porta) throws IOException {
        this.server = new ServerSocket(porta);
        System.out.println(this.getClass().getSimpleName() + " rodando na porta: " + server.getLocalPort());
        this.clientes = new ArrayList<>();
        
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }
    }

    @Override
    public void run() {
        Socket socket;
        while (true) {
            try {
                socket = this.server.accept();
                SomaQuinzeServerConnection cliente = new SomaQuinzeServerConnection(socket);
                novoCliente(cliente);
                (new SomaQuinzeServerHandler(cliente, this)).start();
            } catch (IOException ex) {
                System.out.println("Erro 4: " + ex.getMessage());
            }
        }
    }

    public synchronized void novoCliente(SomaQuinzeServerConnection cliente) throws IOException {
        clientes.add(cliente);
        
        if (clientes.size() == 1) {
            cliente.getOutput().println("player" + "|" + "1");
        } else {
            cliente.getOutput().println("player" + "|" + "2");
        }
    }

    public synchronized void removerCliente(SomaQuinzeServerConnection cliente) {
        clientes.remove(cliente);
        
        try {
            cliente.getInput().close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        cliente.getOutput().close();
        try {
            cliente.getSocket().close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List getClientes() {
        return clientes;
    }



}
