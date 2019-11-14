package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    Client() {
        String hostName = "localhost";
        int portNumber = 12345;

        try (
                Socket addressSocket = new Socket(hostName,
                        portNumber);
                PrintWriter out = new PrintWriter(addressSocket.getOutputStream(), true);
                ObjectInputStream in = new ObjectInputStream(addressSocket.getInputStream())
        ) {
            Object fromServer;
            String fromUser;
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while ((fromServer = in.readObject()) != null) {
                //objectToQuestion = fromServer;
                System.out.println(fromServer.toString());

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client c = new Client();
    }

}

