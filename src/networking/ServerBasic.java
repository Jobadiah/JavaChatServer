package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Apr 18, 2009 7:33:24 PM
 * @author s
 */
public class ServerBasic {

    ServerSocket server;

    public ServerBasic() throws IOException, ClassNotFoundException {
        this.server = new ServerSocket(2000);
        System.out.println("server waiting");
        Socket connection = server.accept();
        System.out.println("server got one");
        ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
//        ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());

        System.out.println("reading form client");
        String s = (String) input.readObject();
        System.out.println("client says :");
        System.out.println(s);
        System.out.println("echo back to client");
        output.writeObject(s);
        connection.close();
    }

//    public static void main(String[] args) {
//        try {
//            new ServerBasic();
//        } catch (IOException ex) {
//            Logger.getLogger(ServerBasic.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ServerBasic.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
}
