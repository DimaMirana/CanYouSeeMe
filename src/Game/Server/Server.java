package Game.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Server is Running");
        try {
            while (true) {
                Game game = new Game();
                Game.Player player1 = game.new Player(listener.accept(), '1');
                Game.Player player2 = game.new Player(listener.accept(), '2');
                player1.setOpponent(player2);
                player2.setOpponent(player1);

                player1.start();
                player2.start();
            }
        } finally {
            listener.close();
        }
    }
}

class Game {

    public synchronized boolean legalMove(int location, Player player) {
        return true;
    }

    class Player extends Thread {

        char mark;
        Player opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        

        public Player(Socket socket, char mark) {
            this.socket = socket;
            this.mark = mark;
            
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
                output.println("WELCOME " + mark);
                output.println("MESSAGE Waiting for opponent to connect");
                if (mark == '1') {
                    output.println("PlayerOne has been Connected");
                }
                if (mark == '2') {
                    output.println("PlayerTwo has been connected");
                }
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }

        public void setOpponent(Player opponent) {
            this.opponent = opponent;
        }

        @Override
        public void run() {
            try {
                
                

                output.println("All players connected");
                while (true) 
                {
                    String clientMsg = input.readLine();
                    int position;
                    System.out.println("\n" + clientMsg);

                    if (clientMsg.startsWith("Player One ")) {
                        position = Integer.parseInt(clientMsg.substring(11, clientMsg.length()));
                        opponent.output.println("Player+One" + position);
                    }

                    if (clientMsg.startsWith("Player Two ")) {
                        position = Integer.parseInt(clientMsg.substring(11, clientMsg.length()));
                        opponent.output.println("Player+Two" + position);
                    }
                    
                    if(clientMsg.equals("LOSER"))
                    {
                        opponent.output.println("WINNER");
                        output.println("LOSER");
                    }
                    
                }
            } catch (IOException e) {

            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }

        }
    }
}
