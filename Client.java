
import java.awt.Dimension;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client 
    {
        public ObjectOutputStream sOutput;
        public ObjectInputStream sInput;
        private Socket socket;
        private String server, username;
        private int port;
        static  IM_Components_GUI gui1;
        
        public Client(String server, String username, int port)
            {
                this.port = port;
                this.username = username;
                this.server = server;
            } // client()
        
        public boolean start()
            {
                try{
                    socket = new Socket(server, port);
                } catch (Exception ex) {
                    System.out.println("Error connecting to server: "+ex);
                    return false;
                } // try-catch
                
                String msg = "Connection accepted: " + socket.getInetAddress() + ": " +socket.getPort();
                System.out.println(msg);
                
                try{
                    sInput = new ObjectInputStream(socket.getInputStream());
                    sOutput = new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException ex) {
                    System.out.println("Error in creating i/o streams: "+ex);
                    return false;
                }
                
                new ListenFromServer().start();
                
                try {
                    sOutput.writeObject(username);
                } catch(IOException ex) {
                    System.out.println("Exception in login: " + ex);
                    disconnect();
                    return false;
                }
                return true;
            } // start()
        
        void sendMessage(ChatMessage msg) {
            try{
                sOutput.writeObject(msg);
            } catch (IOException ex) {
                System.out.println("Exception writing to server: " + ex);
            } // try-catch
        } // sendMessage
        
        public void disconnect() {
            try {
                if(sInput != null)
                    sInput.close();
            } catch(Exception ex) {}
            
            try {
                if(sOutput != null)
                    sOutput.close();
            } catch(Exception ex) {}
            
            try {
                if(socket != null)
                    socket.close();
            } catch(Exception ex) {}
        } // disconnect()
        
        public static void main(String[] args) {
            gui1 = new IM_Components_GUI();
            gui1.setPreferredSize(new Dimension(400,156));
            gui1.setVisible(true);
        }
        
        class ListenFromServer extends Thread
            {
                public void run() {
                    while(true) {
                        try {
                            String msg = (String) sInput.readObject();
                            System.out.println("> " + msg);
                            gui1.set_Room(msg);
                        } catch (IOException ex) {
                            System.out.println("Server has closed the connection: "+ex);
                            break;
                        } catch (ClassNotFoundException e2) {} // try-catch
                    } // while
                } // run()
            } // ListenFromServer
    }


