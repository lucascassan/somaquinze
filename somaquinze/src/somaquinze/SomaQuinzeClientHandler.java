package somaquinze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class SomaQuinzeClientHandler extends Thread {

    private Socket socket;
    private SomaQuinzeClient caller;
    private BufferedReader input;

    public SomaQuinzeClientHandler(Socket socket, SomaQuinzeClient caller) throws IOException {
        this.socket = socket;
        this.caller = caller;
        this.input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    }

    @Override
    public void run() {
        String message;
        while (true) {
            try {                
                if (this.socket.isConnected() && this.input != null) {
                    message = this.input.readLine();
                } else {
                    break;
                }
                
                if (message == null || message.equals("")) {
                    break;
                }
                
                StringTokenizer tokens = new StringTokenizer(message, "|");
                String resposta = tokens.nextToken();        
 
                
                if (resposta.equals("player")) {                    
                    this.caller.player = tokens.nextToken();
                }
                
                if (resposta.equals("ready")){
                    this.caller.pronto = true;
                }
                                                
                if (resposta.equals("jogada")) {
                    String turno = tokens.nextToken();
                    int posicao = Integer.parseInt(tokens.nextToken());           
                    int valor = Integer.parseInt(tokens.nextToken());
                    this.caller.board[posicao] = String.valueOf(valor);
                    if (turno.equals("1"))
                        this.caller.turno = "2";
                    else
                        this.caller.turno = "1";
                        
                }
                
                if (resposta.equals("final")) {
                    String player  = tokens.nextToken();
                    String sequencia = tokens.nextToken();
                    this.caller.Final(player, sequencia);
                }
                
                caller.repaint();
            } catch (Exception ex) {
                System.out.println("Deu erro no cliente");
                System.out.println(ex.getMessage());
                break;
            }
        }
    }
}
