/**
 * 
 */
package data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class ClientInput extends Thread {
	private static BufferedReader instream;
	private static DataOutputStream outstream;
	private static String inline;
	static Scanner keyb = new Scanner(System.in);

	public void run() {	
		try {
			instream = new BufferedReader(new InputStreamReader(Simulator.getSock().getInputStream()));
		} catch (IOException e1) {
			System.out.println("Error: "+e1.getMessage());
			e1.printStackTrace();
		}

		try {
			outstream = new DataOutputStream(Simulator.getSock().getOutputStream());
		} catch (IOException e1) {
			System.out.println("Error: "+e1.getMessage());
			e1.printStackTrace();
		}
		Simulator.printmenu();
		while(true) {
			try{

				while (!(inline = instream.readLine().toUpperCase()).isEmpty()){
					//When we get a message with RM20 8 we will reply with a message from the server.
					if (inline.startsWith("RM20 8")){
						inline = inline.substring(7, inline.length());

						String input = keyb.nextLine();
						outstream.writeBytes(input+ "\r\n");
					}
					else if (inline.startsWith("D")){
						if (inline.equals("D"))
							Simulator.setInstruktionsDisplay("");
						else
							Simulator.setInstruktionsDisplay(inline.substring(2, inline.length()).trim());
						Simulator.printmenu();
						outstream.writeBytes("D A"+"\r\n");
					}
					else if (inline.startsWith("T")){
						Simulator.setTara(Simulator.getBrutto());
						if(String.valueOf(Simulator.getTara()).length() < 7 ){
							outstream.writeBytes("T S      " + (Simulator.getTara()) + "kg"+"\r\n");
						}else{
							outstream.writeBytes("You done goofed in the tara!");
						}
						Simulator.printmenu();
					}
					else if (inline.startsWith("S")){
						Simulator.printmenu();
						outstream.writeBytes("S S      " + (Simulator.getBrutto()-Simulator.getTara())+ "kg"  +"\r\n");
					}
					else if (inline.startsWith("B")){ // denne ordre findes ikke på en fysisk vægt
						String temp= inline.substring(2,inline.length()).trim();
						if(temp.length() < 7){
							Simulator.setBrutto(Double.parseDouble(temp));
							Simulator.printmenu();
							outstream.writeBytes("DB"+"\r\n");
						}
					}
					else if ((inline.startsWith("Q"))){
						System.out.println("");
						System.out.println("Program stoppet Q modtaget paa com port");
						System.in.close();
						System.out.close();
						instream.close();
						outstream.close();
						System.exit(0);
					}
				}
			}
			catch (Exception e){
				System.out.println("Exception: "+e.getMessage());
			}
		}
	}
}

