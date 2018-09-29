import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer{
	static ServerSocket serverSocket;
	int clients;
	
	public void establish(){
		EchoServer newListener = new EchoServer();
		newListener.setUpSocket();
	}
	void setUpSocket(){
		
		try{
			serverSocket = new ServerSocket(1234);
			clients = 0;
		}catch(IOException e){
			System.out.println("Could not listen on port: 1234");
			System.exit(-1);
		}
		
			while(true){
				try{
				Socket clientSocket = serverSocket.accept();
				MultiClientServer server= new MultiClientServer(clientSocket);
				server.start();
				clients++;
				}catch(IOException e){
				System.out.println("Accept failed: 1234");
				System.exit(-1);
			}
		
		}
	}
	
	class MultiClientServer extends Thread{
		Socket serverSocket = null;
		int i;
		
		public MultiClientServer(Socket s){
			serverSocket = s;
			i=0;
		}
		public void run(){
			String inputLine = " ";
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				out = new PrintWriter(serverSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
			
				try{
				while(true){
					inputLine = in.readLine();
					if(inputLine.equals("Bye.")){
						break;
					}
					if(inputLine!=null){
						out.println("answer: "+inputLine);
						i++;
					}
				}
				}catch(IOException ioe){
					System.out.println("Failed in reading, writing");
					System.exit(-1);
				}
				clients--;
				serverSocket.close();
			}  catch (IOException ioe) {
				// TODO Auto-generated catch block
				System.out.println("Failed in creating streams");
				System.exit(-1);
			}
		
			
		}
	}
}