package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public Client() throws UnknownHostException, IOException, InterruptedException, ClassNotFoundException {
        System.out.println("Client connecting");
        Socket connection = new Socket("localhost", 2000);
        ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
        ObjectInputStream input = new ObjectInputStream(connection.getInputStream());

        Thread clientRead = new ClientRead(input);
        clientRead.start();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String userInput = in.nextLine();
            output.writeObject(userInput);
        }

        connection.close();
    }

    public static void main(String[] args) {
        try {
            new Client();
        } catch (Exception ex) {
        }
    }
}

class ClientRead extends Thread {

    ObjectInputStream ois;

    public ClientRead(ObjectInputStream ois) {
        this.ois = ois;
    }

    public void run() {
        while (true) {
            try {
                String received = (String) ois.readObject();
                System.out.println(received);
            } catch (Exception ex) {
            }
        }
    }
}