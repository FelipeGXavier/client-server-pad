import dna.ComplementaryStrand;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable{

    private final Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ComplementaryStrand complementaryStrand = new ComplementaryStrand();
        try {
            ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            String tape = complementaryStrand.getComplementaryStrand(message.split(""));
            ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
            oos.writeObject(tape);
            ois.close();
            oos.close();
            this.socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
