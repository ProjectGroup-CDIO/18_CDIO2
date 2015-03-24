package data;


import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Simulator {

	static ServerSocket listener;
	static Scanner keyb = new Scanner(System.in);
	static SimInput simIn = new SimInput();
	static ClientInput clientIn = new ClientInput();
	private static double brutto = 0;
	private static double tara = 0;
	private static String inline;
	private static String instruktionsDisplay= "";
	private static int portdst = 8000;
	private static Socket sock;
	


	public static void main(String[] args) throws IOException {

		System.out.println("Indtast ønsket port# eller tryk ENTER for port 8000");
		//printmenu();

		String input;
		int inputInt = 0;

		while(true){
			input = keyb.nextLine();
			if(input.equals("")){
				listener = new ServerSocket(portdst);
				break;
			}
			try {
				inputInt = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				System.out.println("Error: "+e.getMessage());
				//System.out.println(e.getMessage());
			}
			//Hvis der er number format exception er inputInt = 0 og if-sætningen køres ikke
			if(inputInt >= 1 && inputInt <= 65536) {
				portdst = inputInt;
				try {
					listener = new ServerSocket(portdst);
					break;
				} catch (BindException e1) {
					System.out.println("Error: "+e1.getMessage()+".. Try again");
					//e1.printStackTrace();
				}
			} else System.out.println("Port# skal være 1 - 65536");
		}
		
		System.out.println("Server venter på forbindelse på port " + portdst);
		//This halts the program until someone makes a connection with it.
		sock = listener.accept();

		simIn.start();
		clientIn.start();

	}

	public static void printmenu(){
		for (int i=0;i<25;i++) System.out.println(" ");
		System.out.println("*************************************************");
		System.out.println("Netto: " + (brutto-tara)+ " kg"                   );
		System.out.println("Instruktionsdisplay: " +  instruktionsDisplay    );
		System.out.println("*************************************************");
		System.out.println("                                                 ");
		System.out.println("                                                 ");
		System.out.println("Debug info:                                      ");
		try {
			System.out.println("Hooked up to " + sock.getInetAddress()            );
		} catch (NullPointerException e) {
			System.out.println("Hooked up to n/a");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Brutto: " + (brutto)+ " kg"                       );
		System.out.println("Streng modtaget: "+inline)                         ;
		System.out.println("                                                 ");
		System.out.println("Denne vegt simulator lytter på ordrene           ");
		System.out.println("D, RM20 8, S, T, B, Q                                ");
		System.out.println("paa kommunikationsporten.                        ");
		System.out.println("******");
		System.out.println("Tast T for tara (svarende til knaptryk paa vegt)");
		System.out.println("Tast B for ny brutto (svarende til at belastningen paa vegt ændres)");
		System.out.println("Tast Q for at afslutte program program");
		System.out.println("Indtast (T/B/Q for knaptryk / brutto ændring / quit)");
		System.out.print  ("Tast her: ");
	}

	public static ServerSocket getListener() {
		return listener;
	}

	public static Socket getSock() {
		return sock;
	}

	public static synchronized int getPortdst() {
		return portdst;
	}

	public static synchronized void setPortdst(int portdst) {
		Simulator.portdst = portdst;
	}

	public static synchronized String getInstruktionsDisplay() {
		return instruktionsDisplay;
	}

	public static synchronized void setInstruktionsDisplay(String instruktionsDisplay) {
		Simulator.instruktionsDisplay = instruktionsDisplay;
	}
	
	public static synchronized double getBrutto() {
		return brutto;
	}

	public static synchronized void setBrutto(double brutto) {
		Simulator.brutto = brutto;
	}

	public static synchronized double getTara() {
		return tara;
	}

	public static synchronized void setTara(double tara) {
		Simulator.tara = tara;
	}

	
	
	

}
