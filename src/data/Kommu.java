package data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;

public class Kommu {

	@SuppressWarnings("deprecation")
	public void Kommu() {
		JFrame frame = new JFrame("asd");
		//Data input
		String IPinput = (String) JOptionPane.showInputDialog(frame, "input IP address");
		String PortAdrres =(String) JOptionPane.showInputDialog(frame, "input port address");

		Socket s = null;  
		DataOutputStream outputStream = null;
		InputStreamReader inputstream = null;
		
		System.out.println("IpAdress " +IPinput);
		System.out.println("Port address " + PortAdrres);
		
		Scanner scanner = new Scanner(System.in);

		try {
			s = new Socket(IPinput,Integer.parseInt(PortAdrres));
			
			outputStream = new DataOutputStream(s.getOutputStream());
			
			inputstream = new InputStreamReader(s.getInputStream());
			
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: hostname");
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to: hostname");
		}
		
		BufferedReader d = new BufferedReader(inputstream);
		
		//Check for om ports og sockets ikke er null, hvis de ikke er 
		if(s != null && outputStream != null && inputstream != null){
			//scanner tjekker for om der er en string
			while(scanner.hasNext()){
				
				PrintWriter w = new PrintWriter(outputStream,true);
				
				String dataInput = scanner.nextLine();
				System.out.println(dataInput);
				
				try{
					
					w.print(dataInput + "\r\n");
					w.flush();
					//this get the reply from the server if any
					String ReplyMess = d.readLine();
					if(ReplyMess != null){
					System.out.println(ReplyMess);
					}
					
				} catch (UnknownHostException e) {
					System.err.println("Trying to connect to unknown host: " + e);
				} catch (IOException e) {
					System.err.println("IOException:  " + e);
				}
				

			}

		}
	}
}
