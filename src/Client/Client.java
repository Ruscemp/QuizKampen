package Client;

import java.io.*;
import java.net.Socket;


public class Client {

    Client() {
        String hostName = "localhost";
        int portNumber = 33332;

        try (
                Socket addressSocket = new Socket(hostName,
                        portNumber);
                ObjectInputStream in = new ObjectInputStream(addressSocket.getInputStream());
                PrintWriter out = new PrintWriter(addressSocket.getOutputStream(), true)
        ) {
            Object fromServer;
            String fromUser;

            while (true) {
                fromServer = in.readObject();
                System.out.println(fromServer.toString());

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    out.println(fromUser);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client c = new Client();
    }

}
