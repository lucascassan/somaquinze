package somaquinze;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SomaQuinzeServerHandler extends Thread {

    private SomaQuinzeServerConnection cliente;
    private SomaQuinzeMain caller;

    public SomaQuinzeServerHandler(SomaQuinzeServerConnection cliente, SomaQuinzeMain caller) throws IOException {
        this.cliente = cliente;
        this.caller = caller;
    }

    private void encerrar() {
        this.caller.removerCliente(this.cliente);
    }
    
    private String verificaVencedor() {
        for (int a = 0; a < 8; a++) {
            String line = null;
 
            switch (a) {
            case 0:
                line = this.caller.board[0] + this.caller.board[1] + this.caller.board[2];
                break;
            case 1:
                line = this.caller.board[3] + this.caller.board[4] + this.caller.board[5];
                break;
            case 2:
                line = this.caller.board[6] + this.caller.board[7] + this.caller.board[8];
                break;
            case 3:
                line = this.caller.board[0] + this.caller.board[3] + this.caller.board[6];
                break;
            case 4:
                line = this.caller.board[1] + this.caller.board[4] + this.caller.board[7];
                break;
            case 5:
                line = this.caller.board[2] + this.caller.board[5] + this.caller.board[8];
                break;
            case 6:
                line = this.caller.board[0] + this.caller.board[4] + this.caller.board[8];
                break;
            case 7:
                line = this.caller.board[2] + this.caller.board[4] + this.caller.board[6];
                break;
            }
      
            if ("XXX".equals(line)) {
                return "X";
            } else if ("OOO".equals(line)) {
                return "O";
            }
        }
         
        for (int a = 0; a < 9; a++) {
            if (Arrays.asList(this.caller.board).contains(String.valueOf(a + 1))) {
                break;
            } else if (a == 8) {
                return "draw";
            }
        }
        
        return null;
    }

    public synchronized void messageDispatcher(String message) throws IOException {
        List<SomaQuinzeServerConnection> clientes = this.caller.getClientes();
        for (SomaQuinzeServerConnection cli : clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                cli.getOutput().println(message);
                cli.getOutput().flush();
            }
        }
    }

    @Override
    public void run() {
        String message;
        while (true) {
            try {
                if (this.cliente.getSocket().isConnected() && this.cliente.getInput() != null) {
                    message = this.cliente.getInput().readLine();
                } else {
                    break;
                }
                
                if (message == null || message.equals("")) {
                    break;
                }
                
               // if (message.equals("casa")) {
                 //   message = "casa";
               // }
                
                messageDispatcher(message); 
                System.out.println(message);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
        
        encerrar();
    }
}
