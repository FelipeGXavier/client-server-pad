import dna.ComplementaryStrand;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClient {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        InetAddress host = InetAddress.getLocalHost();
        Socket socket;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        while (true) {
            System.out.println("Entre com a fita: ");
            String dna = scanner.nextLine();
            if (dna.equalsIgnoreCase("exit")) {
                break;
            }
            socket = new Socket(host.getHostName(), 9876);
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Sending request to Socket Server");
            oos.writeObject(dna);
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);
        }
        assert ois != null;
        ois.close();
        oos.close();
    }
}
