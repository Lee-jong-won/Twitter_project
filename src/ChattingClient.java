import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ChattingClient {
    private String host;
    private int port;

    public ChattingClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        try {
            // Connect client to server
            Socket client = new Socket(host, port);
            System.out.println("Client successfully connected to the server!");

            // Get Socket output stream (where the client sends messages)
            PrintStream output = new PrintStream(client.getOutputStream());

            // Ask for a nickname
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a nickname: ");
            String nickname = sc.nextLine();

            // Send nickname to server
            output.println(nickname);

            // Create a new thread for server messages handling
            new Thread(new ReceivedMessagesHandler(client.getInputStream())).start();

            // Read messages from the keyboard and send to the server
            System.out.println("Messages:\n");

            // While new messages
            while (sc.hasNextLine()) {
                output.println(sc.nextLine());
            }

            // End Ctrl D
            output.close();
            sc.close();
            client.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceivedMessagesHandler implements Runnable {
    private InputStream server;

    public ReceivedMessagesHandler(InputStream server) {
        this.server = server;
    }

    public void run() {
        // Receive server messages and print out to screen
        Scanner s = new Scanner(server);
        String tmp = "";
        while (s.hasNextLine()) {
            tmp = s.nextLine();
            if (tmp.charAt(0) == '[') {
                tmp = tmp.substring(1, tmp.length() - 1);
                System.out.println("\nUSERS LIST: " +
                        new ArrayList<String>(Arrays.asList(tmp.split(","))) + "\n");
            } else {
                try {
                    System.out.println("\n" + getTagValue(tmp));
                } catch (Exception ignore) {
                }
            }
        }
        s.close();
    }

    // I could use a javax.xml.parsers but the goal of Client.java is to keep everything tight and simple
    public static String getTagValue(String xml) {
        return xml.split(">")[2].split("<")[0] + xml.split("<span>")[1].split("</span>")[0];
    }
}
