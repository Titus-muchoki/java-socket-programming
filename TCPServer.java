import java.io.*;
import java.net.*;
import java.util.Scanner;

 
class TCPServer {
    public static void main(String args[]) throws Exception {
        String serverName = "localhost";
        int port = 8722; // Same port number with the server
        Socket socket = null;//input streams are usually heavy resouces
        PrintStream toServer = null;
        BufferedReader fromServer = null;
 
        System.out.println("TCP Client launched, using server: " + serverName + ", Port: " + port);
 
        // Read from user input
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        Scanner scanner = new Scanner(System.in);
        do {// process messages sent from client
            System.out.print("Which operation would like to continue with? \n 1. Calculator \n 2. Convert string toUpperCase \n 3. Exit \n ");
            System.out.flush(); // used to  to force any buffered output to be written immediately
            userInput = scanner.nextLine();
            
            // if (userInput.getClass().equals("")) {
            //     break;
            // }
 
            // Open a new socket connection to the server with the specified port number
            socket = new Socket(serverName, port);
 
            // Send user input to server
            toServer = new PrintStream(socket.getOutputStream());
            toServer.println(userInput);
            System.out.println("[TCPClient] Send out user input [" + userInput + "] to Server.");
 
            // Get response from server
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String responseFromServer = fromServer.readLine();
            System.out.println("[TCPClient] Get response [" + responseFromServer + "] from Server.");
        } while (!userInput.equals("quit")); // End the client if 'quit' is an input

      
 
        // Close connection
        if (socket != null) {
            socket.close();
        }
        
    }
}
