import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static int PORT = 8080;
    private static String currentCity = "???";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {

            System.out.println("Server started! ");

            while (true) {

                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                    System.out.println("New connection accepted: " + clientSocket.getInetAddress());
                    out.println(currentCity);
                    System.out.println("Текущий город: " + currentCity);

                    String cityClient = in.readLine();
                    cityClient = cityClient.toLowerCase();

                    if (currentCity.equals("???")) {
                        currentCity = cityClient;
                        out.println("OK");
                    } else {
                        char lastChar = currentCity.charAt(currentCity.length() - 1);
                        char firstChar = cityClient.charAt(0);

                        if (lastChar == firstChar) {
                            currentCity = cityClient;
                            out.println("OK");
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
