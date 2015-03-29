/**
 * 
 */
package data;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.*;


public class ClientInput extends Thread {
	private BufferedReader instream;
	private DataOutputStream outstream;
	private String inline;
	static Scanner keyb = new Scanner(System.in);
	private Socket sock;

	public ClientInput(Socket s) {
		sock = s;
	}


	/**
	 * This method checks if a String meets the requirements set for the RM20 command, set by the SISC protocol.
	 * @param str input String
	 * @return if true, the input String meets the requirements for the RM20 command.
	 */

	public static boolean checkRM20(String str) {
		int i = 0;
		int count = 0;
		int indices[] = new int[6];
		int j = 0;
		while(i < str.length()) {
			if(str.charAt(i) == '\"') {
				if(indices[5] == 0) {
					indices[j] = i;
					j++;
				}
				count++;
			}
			i++;
		}
		if(count == 6) {
			//check to see if there is one and only one space between the 2nd-3rd and 4th-5th quotations marks
			if(str.charAt(indices[1]+1)== ' ' && str.charAt(indices[1]+2) == '\"' 
					&& str.charAt(indices[3]+1) == ' ' && str.charAt(indices[3]+2) == '\"' 
					&& str.indexOf('\"', indices[5]) == str.lastIndexOf('\"')) {
				return true;				
			} else return false;
		}else return false;
	}


	@Override
	public void run() {	
		try {
			instream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			outstream = new DataOutputStream(sock.getOutputStream());
		} catch (IOException e1) {
			System.out.println("Error: "+e1.getMessage());
			//e1.printStackTrace();
		}

		Simulator.printmenu();

		while(true) {
			try {
				Thread.sleep(35);
			} catch (InterruptedException e2) {
				//doesn't really matter if interrupted
			}
			try{
				while (!(inline = instream.readLine().toUpperCase()).isEmpty()){
					boolean correctmsg = false;
					//System.out.println(inline);
					//When we get a message with RM20 8 we will reply with a message from the server.
					if (inline.startsWith("RM20 8")){
						inline = inline.substring(7, inline.length()).trim();
						//Validation check
						if(checkRM20(inline)) {
							correctmsg = true; 
							System.out.print("Tryk ENTER og derefter dit svar");
							Simulator.setInstruktionsDisplay(inline);
							Simulator.printmenu();
							System.out.print("\nBesked modtaget. Se instruktionsdisplay.\nTryk ENTER og derefter dit svar");
							String input = keyb.nextLine();
							if(input.equals("")) {
								outstream.writeBytes("RM20 B"+"\r\n");
							} else if(input.charAt(0) != '\"' && input.charAt(input.length()-1) != '\"') {
								input = "\""+input+"\"";
								outstream.writeBytes("RM20 A "+input+ "\r\n");
							} else if(input.charAt(input.length()-1) != '\"') {
								input = input+"\"";
								outstream.writeBytes("RM20 A "+input+ "\r\n");
							} else if(input.charAt(0) != '\"') {
								input = "\""+input;
								outstream.writeBytes("RM20 A "+input+ "\r\n");
							} else outstream.writeBytes("RM20 A "+input+ "\r\n");
						}
					}

					else if(inline.startsWith("P111")){
						if(inline.length() <= 35){
							if(inline.charAt(5)== '\"' && inline.charAt(inline.length()-1) == '\"'){
								Simulator.setInstruktionsDisplay(inline.substring(3, inline.length()-1).trim());
								Simulator.printmenu();
								outstream.writeBytes("P111 A"+"\r\n");
								correctmsg = true;
							}
							//							else{
							//								outstream.writeBytes("S"+"\r\n");
							//							}
						}
						//						else{
						//							outstream.writeBytes("S"+"\r\n");	
						//						}	
					}

					else if (inline.equals("DW")){
						Simulator.setWeightDisplay("");
						Simulator.printmenu();
						outstream.writeBytes("DW A"+"\r\n");
						correctmsg = true; 
					}

					else if (inline.startsWith("D")){
						if (inline.equals("D")){
							Simulator.setWeightDisplay("");
							Simulator.printmenu();
							outstream.writeBytes("D A"+"\r\n");
							correctmsg = true; 
						}

						else{
							if(inline.length() < 10 && inline.length() > 3){
								if(inline.charAt(2)== '\"' && inline.charAt(inline.length()-1) == '\"'){
									Simulator.setWeightDisplay(inline.substring(3, inline.length()-1).trim());
									Simulator.printmenu();
									outstream.writeBytes("D A"+"\r\n");
									correctmsg = true; 
								}					
							}

						}						
					}

					else if (inline.startsWith("T")){
						if (inline.equals("T")){

							Simulator.setTara(Simulator.getBrutto());

							if(String.valueOf(Simulator.getTara()).length() <= 7 ){
								outstream.writeBytes("T S      " + (Simulator.getTara()) + "kg"+"\r\n");
								correctmsg = true; 
							}
						}
						//						else{
						//							outstream.writeBytes("S" +"\r\n");
						//						}
						Simulator.printmenu();
					}

					else if (inline.equals("S")){
						Simulator.printmenu();
						outstream.writeBytes("S S      " + (Simulator.getBrutto()-Simulator.getTara())+ " kg"  +"\r\n");
						correctmsg = true; 
					}

					else if (inline.startsWith("B")){ // denne ordre findes ikke på en fysisk vægt
						if(inline.length() > 5){
							String temp= inline.substring(2,inline.length()).trim();
							if(temp.length() <= 7){
								Simulator.setBrutto(Double.parseDouble(temp));
								Simulator.printmenu();
								outstream.writeBytes("DB"+"\r\n");
								correctmsg = true; 
							}

						}

					}

					else if ((inline.equals("Q"))){
						System.out.println("");
						System.out.println("Program stoppet Q modtaget paa com port");
						System.in.close();
						System.out.close();
						instream.close();
						outstream.close();
						SimInput.stopGracefully();
						System.exit(0);

					}
					if(!correctmsg){
						outstream.writeBytes("S"+"\r\n");
					}
				}
			}catch(NullPointerException e1){
				//when a client terminates his connection i.e closes his computer or connection program
				//the connection is set to null -> this means that we have to handle the thread that is still running
				System.out.println("\nConnection has been terminated, closing thread");
				break;
			}
			catch (Exception e){
				System.out.println("Exception: "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}


