package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 * Main Server Class. Which dispatches a thread per client.
 * 
 * @author Fahad Shaon fahadshaon AT gmail.com
 */
public class Server extends Thread {

    private ServerSocket server;
    private Vector<ServerThreadPerClient> allCommunicationThreads;
    private int clientCount;

    public Server() throws IOException {
        this.server = new ServerSocket(2000);
        this.allCommunicationThreads = new Vector<ServerThreadPerClient>();
        clientCount = 0;
    }

    public void run() {

        while (true) {
            try {
                Socket connection = server.accept();
                ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
                clientCount++;
                ServerThreadPerClient stpc = new ServerThreadPerClient(input, output,
                        "Client " + clientCount, this);

                this.allCommunicationThreads.add(stpc);
                stpc.start();
            } catch (Exception ex) {
            }
        }
    }

    void sendToAll(String clientName, String string) {

        for (ServerThreadPerClient serverCommunicationThread : allCommunicationThreads) {
            if (!serverCommunicationThread.getClientName().equals(clientName) &&
                    serverCommunicationThread.getKeepRunning()) {
                try {
                    serverCommunicationThread.writeMessage(clientName + ": " + string);
                } catch (Exception ex) {
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
//        new Thread(new Server()).start();
        Thread server = new Server();
        server.start();
    }
}

class ServerThreadPerClient extends Thread {

    ObjectInputStream ois;
    ObjectOutputStream oos;
    String clientName;
    Server server;

    public ServerThreadPerClient(ObjectInputStream ois, ObjectOutputStream oos, String clientName, Server server) {
        this.ois = ois;
        this.oos = oos;
        this.clientName = clientName;
        this.server = server;

    }

    public String getClientName() {
        return clientName;
    }
    boolean keepRunning = true;

    public boolean getKeepRunning() {
        return keepRunning;
    }

    public void run() {
        while (keepRunning) {
            try {
                String received = (String) ois.readObject();
                System.out.println("Recived from \"" + clientName + "\": " + received);
                server.sendToAll(clientName, received);
            //Thread.sleep(300);
            } catch (IOException ex) {
                System.out.println("Client died:" + clientName);
                keepRunning = false;
                break;
            } catch (Exception ex) {
            }
        }
    }

    public void writeMessage(String str) throws IOException {
        oos.writeObject(str);
    }
}
