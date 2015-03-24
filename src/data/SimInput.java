package data;
import java.util.Scanner;

public class SimInput extends Thread {

	static Scanner keyb = new Scanner(System.in);

	public void run() {
		while(true) {
			String input = keyb.nextLine();

			if(!(input.isEmpty())) {
				if(input.startsWith("T")) {
					Simulator.setTara(Simulator.getBrutto());
					if(String.valueOf(Simulator.getTara()).length() < 7 ){
						System.out.println("T S      " + Simulator.getTara() + "kg"+"\r\n");
					}else{
						System.out.println("You done goofed in the tara!");
					}
					Simulator.printmenu();	
				} else if(input.startsWith("B")) {
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
				}
			}	
		}
	}
}
