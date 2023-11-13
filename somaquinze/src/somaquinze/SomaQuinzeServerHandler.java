package somaquinze;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

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
    
     private int GetCasa(int i){
        try {
            int a = Integer.parseInt(this.caller.board[i]);
            return a;
        } catch (Exception e) {
            return -999;
        }
    }
    
    private String VerificaSomaCasas(int i1, int i2, int i3){
        int aux = (GetCasa(i1)+GetCasa(i2)+GetCasa(i3));
        if (aux == 15)
            return String.valueOf(i1)+String.valueOf(i2)+String.valueOf(i3);
        else
            return "";
    }
    
    private String verificaVencedor() {
          String result = "";
        int Possiveis[][] = {
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}
                };
        for (int i = 0; i < 8; i++) {
            result = VerificaSomaCasas(Possiveis[i][0], Possiveis[i][1],Possiveis[i][2]);
            if (!result.equals(""))
                break;                 
        }
        if (!result.equals(""))
            return result;
        else                 
            return "";
    }
    
    private boolean VerificaEmpate(){
        for (int i = 0; i < 9; i++) {
            if (GetCasa(i)== -999)    
                return false;
        }
        return true;
    }

    public synchronized void messageDispatcher(String message) throws IOException {
        List<SomaQuinzeServerConnection> clientes = this.caller.getClientes();
        for (SomaQuinzeServerConnection cli : clientes) {
            if (cli.getSocket() != null && cli.getSocket().isConnected() && cli.getOutput() != null) {
                cli.getOutput().println(message);
                cli.getOutput().flush();
                
                 if (clientes.size()==2 && !this.caller.pronto ){
                    cli.getOutput().println("ready");
                    cli.getOutput().flush();    
                 }
                
            }
        }     
    }

    @Override
    public void run() {
        String message;
        String response ="";

        while (true) {
            try {
                if (this.cliente.getSocket().isConnected() && this.cliente.getInput() != null) {
                    message = this.cliente.getInput().readLine();
                } else {
                    break;
                }
                StringTokenizer tokens = new StringTokenizer(message, "|");
                String auxMessage = tokens.nextToken();  
                
                if (message == null || message.equals("")) {
                    break;
                }
                
               if (auxMessage.equals("jogada")){
                   String jogador = tokens.nextToken();
                   String casa    = tokens.nextToken();
                   String valor   = tokens.nextToken();
                   this.caller.board[Integer.parseInt(casa)] = valor;
                    response = verificaVencedor();
                    if (!response.equals(""))
                        response = "final|"+jogador+"|"+response;
                    else if (VerificaEmpate())
                        response = "draw";
                    
               }
                
                messageDispatcher(message); 
                
                if (!response.equals("")){
                    TimeUnit.MILLISECONDS.sleep(300);
                    messageDispatcher(response); 
                    this.caller.resetMatch();
                }
                
                System.out.println(message);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }
        }
        encerrar();
    }
}
