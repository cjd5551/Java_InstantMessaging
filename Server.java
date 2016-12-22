
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server 
    {
        private static int uniqueId;
        private ArrayList<ClientThread> al;
        private SimpleDateFormat sdf;
        private int port;
        private boolean keepGoing;
        
        public Server(int port)
            { 
                this.port = port; 
                sdf = new SimpleDateFormat("HH:mm:ss");
                al = new ArrayList<ClientThread>();
            }
        
        public void start() throws IOException
            {
                keepGoing = true;
                
                try{
                    ServerSocket ss = new ServerSocket(port);
                    
                    while(keepGoing)
                        {
                            System.out.println("Server waiting for clients on port: " + port);
                            Socket s = ss.accept();
                            
                            if(!keepGoing)
                                break;
                            
                            ClientThread ct = new ClientThread(s);
                            al.add(ct);
                            ct.start();
                        }
                    
                    ss.close();
                    for(int i = 0; i < al.size(); i++)
                        {
                            ClientThread ct2 = al.get(i);
                            ct2.sInput.close();
                            ct2.sOutput.close();
                            ct2.socket.close();
                        } // for loop
                } catch (Exception ex) {
                    System.out.println("Closing due to exception: " + ex);
                } // try-catch
            } // start()
        
        protected void stop() 
            {
                keepGoing = false;
                try {
                    new Socket("localhost", port);
                } catch (Exception ex) {}
            } // stop()
        
        private synchronized void broadcast(String message, String user)
            {
                String time = sdf.format(new Date());
                String Out_Msg = user + " [" + time + " ]: " + message;
                System.out.println("Broadcasting \"" + Out_Msg + "\"...");
                
                for (int i = al.size(); --i >= 0;)
                    {
                        ClientThread ct = al.get(i);
                        
                        if(!ct.writeMsg(Out_Msg))
                            {
                                al.remove(i);
                                System.out.println("Disconnected user (removed): " + ct.username);
                            } // if
                    } // for
            } // broadcast()
        
        synchronized void remove(int id)
            {
                for(int i = 0; i < al.size(); ++i)
                    {
                        ClientThread ct = al.get(i);
                        
                        if(ct.id == id)
                        {
                            al.remove(i);
                            return;
                        } // if
                    } // for
            } // remove()
        
        public static void main (String[] args) throws IOException
            {
                int portNumber = 5000;
                
                switch(args.length)
                    {
                        case 1:
                            try{
                                portNumber = Integer.parseInt(args[0]);
                            } catch (Exception e) {
                                System.out.println("Invalid port number: " + portNumber);
                                return;
                            }
                        case 0:
                            break;
                        default:
                            System.out.println("Usage is: > java Server [port number]");
                            return;
                    } // switch
                
                Server server = new Server(portNumber);
                server.start();
            } // main
        
        class ClientThread extends Thread 
            {
                Socket socket;
                ObjectOutputStream sOutput;
                ObjectInputStream sInput;
                int id;
                String username;
                ChatMessage cm;
                String date;

                public ClientThread(Socket socket)
                    {
                        id = ++uniqueId;
                        this.socket = socket;

                        System.out.println("System creating thread i/o stream...");

                        try {
                            sOutput = new ObjectOutputStream(socket.getOutputStream());
                            sInput = new ObjectInputStream(socket.getInputStream());
                            username = (String) sInput.readObject();
                            System.out.println(username + " just connected.");
                        } catch (IOException ex) {
                            System.out.println("Exception creating i/o streams: " + ex);
                            return;
                        } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex); 
                        } // try-catch

                        date = new Date().toString();
                    } // ClientThread constructor

                public void run()
                    {
                        boolean keepGoing = true;

                        while(keepGoing)
                            {
                                try {
                                    cm = (ChatMessage)sInput.readObject();
                                } catch (IOException ex) {
                                    System.out.println(username + " - Exception reading: " + ex);
                                } catch (ClassNotFoundException ex2) { 
                                    System.out.println("Class exception: " + ex2);
                                } catch (Exception ex) {} // try-catch

                                String message = cm.getMessage();

                                switch(cm.getType())
                                    {
                                        case ChatMessage.message:
                                            broadcast(message, username);
                                            break;
                                        case ChatMessage.logout:
                                            System.out.println(username + " disconnected with LOGOUT message.");
                                            keepGoing = false;
                                            break;
                                        case ChatMessage.who_is_in:
                                            writeMsg("List of the users connected at "+sdf.format(new Date()));
                                            for(int i = 0; i < al.size(); ++i)
                                                {
                                                    ClientThread ct = al.get(i);
                                                    writeMsg((i+1) + ") " + ct.username + " since " + ct.date);
                                                } // for
                                            break;
                                        default:
                                            break;
                                    } // switch
                            } // while
                        remove(id);
                        close();
                    } // run()

                private void close()
                    {
                        try {
                            if(sOutput != null)
                                sOutput.close();
                        } catch (Exception ex) {}

                        try {
                            if (sInput != null)
                                sInput.close();
                        } catch (Exception ex) {}

                        try {
                            if (socket != null)
                                socket.close();
                        } catch (Exception ex) {}
                    } // close ()

                public boolean writeMsg(String msg) {
                    if(!socket.isConnected()) {
                        close();
                        return false;
                    }

                    try {
                        sOutput.writeObject(msg);
                    } catch (IOException ex) {
                        System.out.println("Error sending message to " + username);
                    }
                    return true;
                } // writeMsg()

            } // ClientThread Class
    } // Server Class
