import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame {
	
	// Text area for displaying contents
	private JTextArea jta = new JTextArea();

	public static void main(String[] args) {
		new Server();
	}

	public Server() {
		// Place text area on the frame
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);

		setTitle("Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!

		try {
			
			// Create a server socket (why is the below line never used?)
			ServerSocket serverSocket = new ServerSocket(8000); // 8000 is port
			jta.append("Server started at " + new Date() + '\n');

			// Listen for a connection request
			Socket socket = serverSocket.accept();

			// Create data input and output streams
			DataInputStream inputFromClient = new DataInputStream(
					socket.getInputStream() );
			DataOutputStream outputToClient = new DataOutputStream(
					socket.getOutputStream() );

			while (true) {
				// Receive radius from the client
				double theAnnualInterestRate = inputFromClient.readDouble();
				double theNumberOfYears = inputFromClient.readDouble();
				double theLoanAmount = inputFromClient.readDouble();

				double theAnnualInterestRateAsADecimal = theAnnualInterestRate/100;
				
				// Compute
				double theInterest = theLoanAmount * theAnnualInterestRateAsADecimal * theNumberOfYears;
				
				double theResultingTotal = theInterest + theLoanAmount;

				// Send area back to the client
				outputToClient.writeDouble(theResultingTotal);

				jta.append("Total: " + theResultingTotal + '\n');
			}
		}
		catch(IOException ex) {
			System.err.println(ex);
		}
	}
}