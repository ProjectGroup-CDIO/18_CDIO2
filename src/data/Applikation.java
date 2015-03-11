package data;
import java.util.List;
import java.util.Scanner;

import data.IOperatoerDAO.DALException;
/*
 * This is the runable applikation
 * From here we will build up the applikation so that the entire project works
 * Here we are to create the UI and get userinputs
 * Userinputs are used in the IFunk and through this send through the system
 * 
 */
public class Applikation extends Funktionalitet  {

	IFunktionalitet funktion = new Funktionalitet();
	Scanner tastatur = new java.util.Scanner(System.in);
	UserCommandLog updateData = new UserCommandLog();

	public void App() throws DALException {
		System.out.println("______________________________________");
		System.out.println();
		System.out.println("Enter user ID followed by a password");
		System.out.println("");
		ProgramRestart();
	}



	private void ProgramRestart() throws DALException{
		System.out.println("Enter user-ID");
		System.out.println("To exit the program write exit");
		while(tastatur.hasNext()){
			String brugerId = tastatur.nextLine();
			if(brugerId.toLowerCase().contains("exit")){
				System.exit(1);
			}
			System.out.println("Enter password");
			String BrugerPass = tastatur.nextLine();
			
			
			if(BrugerPass.equals(funktion.getOperatoer(Integer.parseInt(brugerId)).password)){
				System.out.println("Access granted");
				while(true){
					if(Integer.parseInt(brugerId) < 11){
						funktion.setAdmin(true);
					}else{
						funktion.setAdmin(false);
					}
					String valg;

					if(Integer.parseInt(brugerId) > 11){
						int oprId = 11;
						funktion.getOperatoer(oprId);
					}

					if(funktion.isAdmin()){

						System.out.println("________________________________________");
						System.out.println(" Welcome back, admin! What do you want to do");
						System.out.println("List of operators, press 1");
						System.out.println("Delete operator, press 2");
						System.out.println("Update operator, press 3");
						System.out.println("Show operator, press 4");
						System.out.println("Create operator, press 5");
						System.out.println("Logout, press 6");

						valg = tastatur.nextLine();
						adminOptions(valg);

					}else{
						System.out.println("____________________________________________");
						System.out.println();
						System.out.println("Welcome back, Operatoer! What do you want to do?");
						System.out.println("Showing information for you operator ID: " + brugerId);
						System.out.println("Name: " + funktion.getOperatoer(Integer.parseInt(brugerId)).oprNavn);
						System.out.println("CPR: " + funktion.getOperatoer(Integer.parseInt(brugerId)).cpr);
						System.out.println("Ini: "+ funktion.getOperatoer(Integer.parseInt(brugerId)).ini);
						System.out.println("Password: " + funktion.getOperatoer(Integer.parseInt(brugerId)).password);

						System.out.println("These are your options:");
						System.out.println("Change password, press 1");
						System.out.println("To use the weight, press 2");
						System.out.println("To log out, press 3");

						valg = tastatur.nextLine();
						oprOptions(valg,brugerId);

					}
				}
			}else{
				System.out.println("Access denied - wrong password");

			}

		}

	}

	private void oprOptions(String valg, String brugerId) throws DALException {
		int id = Integer.parseInt(brugerId);
		switch(valg){
		case "1":
			System.out.println("You wish to change your password:");
			System.out.println("Please enter old password");
			String oldPass = tastatur.nextLine();
			System.out.println("Please enter your new password:");
			String newPass1 = tastatur.nextLine();
			System.out.println("Please confirm your new password by entering it again:");
			String newPass2 = tastatur.nextLine();
		
			
			if(funktion.getOperatoer(id).password.equals(oldPass)&& newPass1.equals(newPass2)){
				try{
					funktion.updateOperator(new OperatoerDTO(id, "NULL", "NULL", "NULL", newPass1)); 
				}catch(NumberFormatException e){
					System.out.println("You wrote a non valid integer - please write one between 11 - 99");
					oprOptions("1", brugerId);
				}
			}
			break;
		case "2":
			System.out.println("To use the weight(note this will be logged):");
			System.out.println("Please enter your tara:");
			String tara = tastatur.nextLine();
			System.out.println("Please enter the netto:");
			String netto = tastatur.nextLine();
			try{
				System.out.println(funktion.weightCalc(Double.parseDouble(netto), Double.parseDouble(tara), brugerId));
			}catch(NumberFormatException e){
				System.out.println("You wrote a non valid double! - please write two correctly!");
				oprOptions("2", brugerId);
			}
			break;
		case "3":
			System.out.println("You are now logged out - please follow the steps to login again");
			ProgramRestart();
			break;
		}

	}



	private void adminOptions(String valg) throws DALException {
		switch(valg){

		case "1":
			int nrOfOpr = funktion.ShowOperators().size();
			List<OperatoerDTO> List = funktion.ShowOperators();
			for (int x = 0; x<nrOfOpr; x++){
				System.out.println(List.get(x).oprId+", Navn:"+List.get(x).oprNavn+", ini:"+List.get(x).ini+", cpr:"+List.get(x).cpr+", Pass:"+List.get(x).password);
			}
			
			break;
		case "2":
			System.out.println("Write ID of person to be deleted");
			String valg2 = tastatur.nextLine();
			funktion.deleteOperator(funktion.getOperatoer(Integer.parseInt(valg2)));
			break;
		case "3":
			System.out.println("Please enter the oprID for the operator you want to change)");
			String oprId = tastatur.nextLine();
			System.out.println("Please enter the Name for the operator you want to update.NULL is interpreted as do not change(remember it has to be capital!)");
			String name = tastatur.nextLine();
			System.out.println("Please enter the ini for the operator you want to update.NULL is interpreted as do not change(remember it has to be capital!)");
			String ini = tastatur.nextLine();
			System.out.println("Please enter the cpr for the operator you want to update.NULL is interpreted as do not change(remember it has to be capital!)");
			String cpr = tastatur.nextLine();
			System.out.println("Please enter the password for the operator you want to update.NULL is interpreted as do not change(remember it has to be capital!)");
			String pass = tastatur.nextLine();

			try{
				funktion.updateOperator(new OperatoerDTO(Integer.parseInt(oprId), name, ini, cpr, pass)); 
			}catch(NumberFormatException e){
				System.out.println("You wrote a non valid integer - please write one between 11 - 99");
				adminOptions("3");
			}
			break;
		case "4":
			System.out.println("Write the ID of the operator which you wish to look at");
			String IdInput = tastatur.nextLine();
			try{
				System.out.println(funktion.getOperatoer(Integer.parseInt(IdInput)).oprId+", "+funktion.getOperatoer(Integer.parseInt(IdInput)).oprNavn+", "+funktion.getOperatoer(Integer.parseInt(IdInput)).ini+", "+funktion.getOperatoer(Integer.parseInt(IdInput)).cpr+", "+funktion.getOperatoer(Integer.parseInt(IdInput)).password);
			}catch(NumberFormatException e){
				System.out.println("You wrote a non valid integer - please write one between 11 - 99");
				adminOptions("4");
			}
			break;
		case "5":
			System.out.println("Creating is done by following these steps: Write the operatoer's ID nr - between 11-99");
			oprId = tastatur.nextLine();
			System.out.println("Please enter the Name for the operator you want to create.NULL is interpreted as do not change(remember it has to be capital!)");
			name = tastatur.nextLine();
			System.out.println("Please enter the ini for the operator you want to create.NULL is interpreted as do not change(remember it has to be capital!)");
			ini = tastatur.nextLine();
			System.out.println("Please enter the cpr for the operator you want to create.NULL is interpreted as do not change(remember it has to be capital!)");
			cpr = tastatur.nextLine();
			System.out.println("Password is generated by internal programs, please write it down. If you loose it you can retrive it again");
			pass = "NULL";

			try{
				funktion.createOperator(new OperatoerDTO(Integer.parseInt(oprId), name, ini, cpr, pass)); 
			}catch(NumberFormatException e){
				System.out.println("You wrote a non valid integer - please write one between 11 - 99");
				adminOptions("5");
			}
			break;

		case "6":
			System.out.println("You are now logged out - please follow the steps to login again");
			ProgramRestart();
			break;
		}		
	}

}





