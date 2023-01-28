import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private static int PORT = 8080;
    private static String ip = "localhost";

    public static void main(String[] args) {
        System.out.println("Игра в города: ");

        try (Socket clientSocket = new Socket(ip, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));){

            String currentCity = in.readLine();
            Scanner scanner = new Scanner(System.in);

            if (currentCity.equals("???")) {
                System.out.println("Введиде город с которого начнется игра: ");
                out.println(scanner.nextLine());
                System.out.println(in.readLine());
            } else {
                System.out.println("Город: " + currentCity);
                System.out.println("Введите город оканчивающийся на букву: " + currentCity.toCharArray()[currentCity.length() - 1]);

                out.println(scanner.nextLine());
                System.out.println(in.readLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
