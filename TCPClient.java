import java.io.*;
import java.net.*;
import java.util.Scanner;
 
class TCPClient {
    public static void main(String args[]) throws Exception {
        int port = 8722;
        Socket socket = null;
        BufferedReader reader = null; // Local reader from the client
        PrintStream outputStream = null; // Output stream to the client
 
        String clientRequest;
        String responseToClient = "";
        ServerSocket ss = new ServerSocket(port);
        System.out.println("TCP Server is starting up, listening at port " + port + ".");
 
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Get request from client
            socket = ss.accept();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientRequest = reader.readLine().toString();
            System.out.println("[TCPServer] Get request [" + clientRequest + "] from Client.");
            if(clientRequest.equals("1")){
        
                char operator;
                Double number1, number2, result;

                // create an object of Scanner class
                Scanner input = new Scanner(System.in);

                // ask users to enter operator
                System.out.println("Choose an operator: +, -, *, or /");
                operator = input.next().charAt(0);

                // ask users to enter numbers
                System.out.println("Enter first number");
                number1 = input.nextDouble();

                System.out.println("Enter second number");
                number2 = input.nextDouble();

                switch (operator) {

                // performs addition between numbers
                case '+':
                    result = number1 + number2;
                    responseToClient = result.toString();
                    break;

                // performs subtraction between numbers
                case '-':
                    result = number1 - number2;
                    responseToClient = result.toString();
                    break;

                // performs multiplication between numbers
                case '*':
                    result = number1 * number2;
                    responseToClient = result.toString();
                    break;

                // performs division between numbers
                case '/':
                    result = number1 / number2;
                    responseToClient = result.toString();
                    break;

                default:
                    System.out.println("Invalid operator!");
                    break;
                }
            }else if(clientRequest.equals("2")){
                System.out.println("Which text do you wat to convert");
                String userText = scanner.nextLine();
                responseToClient = userText.toUpperCase();

            }else if(clientRequest.equals("3")){
                System.out.println("Goodbye");
                break;

            }else{
                System.out.println("Invalid input");
                return;
            }
            // Send response to client
            outputStream = new PrintStream(socket.getOutputStream());
            outputStream.println(responseToClient);
            System.out.println("[TCPServer] Send out response [" + responseToClient + "] to Client.");
        }
    }
}
