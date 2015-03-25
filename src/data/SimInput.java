package data;
import java.io.IOException;
import java.util.Scanner;

public class SimInput extends Thread {
	
	private volatile static boolean stop = false;
	
	public static void stopGracefully (){
		stop = true;
	}

	static Scanner keyb = new Scanner(System.in);
	
	@Override
	public void run() {
		while(!stop) {
			String input = keyb.nextLine();

			if(!(input.isEmpty())) {
				if(input.toUpperCase().startsWith("T")) {
					Simulator.setTara(Simulator.getBrutto());
					Simulator.setBrutto(0);
					if(String.valueOf(Simulator.getTara()).length() < 7 ){
						System.out.println("T S      " + Simulator.getTara() + "kg"+"\r\n");
					}else{
						System.out.println("You done goofed in the tara!");
					}
					Simulator.printmenu();	
				} else if(input.toUpperCase().startsWith("B")) {
					String temp = input.substring(1, input.length()).trim();
					if(temp.length() < 7) {
						double tempDouble;
						try {
							tempDouble = Double.parseDouble(temp);
							Simulator.setBrutto(tempDouble);	
						} catch (NumberFormatException e) {
							System.out.println("Error: "+e.getMessage());
							e.printStackTrace();
						}
						Simulator.printmenu();
					}
				} else if(input.toUpperCase().startsWith("Q")) {
					System.out.println("");
					System.out.println("Program stoppet Q modtaget paa com port");
					try {
						System.in.close();
					} catch (IOException e) {
						System.out.println("Fejl ved lukning af System.in.close()");
						e.printStackTrace();
					}
					System.out.close();
					System.exit(0);
					SimInput.stopGracefully();
				}
			}	
		}
	}
	
}
