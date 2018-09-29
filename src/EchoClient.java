import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EchoClient{
	public void establish(){
		Socket echoSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		
		try{
			Scanner scanner = new Scanner(System.in);
			echoSocket = new Socket("127.0.0.1", 1234);
			//echoSocket = new Socket("194.47.41.47", 1234);

			out = new PrintWriter(echoSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			
			System.out.println("You're now connected to the server");
			
			String userInput;
			do{
				userInput=scanner.next();
				out.println(userInput);
				System.out.println("echo:"+in.readLine());
			
			}while(userInput.compareTo("Bye.")!=0);
				System.out.println("Bye");
			}catch(UnknownHostException e){
				System.err.println("Don't know about host.");
				System.exit(1);
		}catch(IOException e){
			System.err.println("Couldn't get I/O");
			System.exit(1);
		}
	}
}