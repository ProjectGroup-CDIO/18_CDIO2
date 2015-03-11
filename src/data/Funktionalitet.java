package data;

import java.util.List;

import data.IOperatoerDAO.DALException;

public class Funktionalitet implements IFunktionalitet {

	IOperatoerDAO OPdata = new OperatoerDAO();
	UserCommandLog Log = new UserCommandLog();
	
	boolean ADMIN = true;
	int vaegt;
	int tara;
	int brutto = vaegt+tara;
	public boolean adminLogin;
	
	@Override
	public void createOperator(OperatoerDTO opr) throws DALException {
		if(ADMIN) {
		try {
			OPdata.createOperatoer(opr);

		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*Check if the operator has been created correctly
		 *Checks if the given Operatoer has been created by checking 
		 *if the given opr matches with an excisting opr in the list of
		 *operatoers.
		 */ 
		}else{
			System.out.println("Faliure to create user. - not admin");
		}
		if(OPdata.getOperatoer(opr.oprId) == opr){
			System.out.println("Succes - new opr has been created.");
		}else{
			System.out.println("Faliure - found no opr that matched with a opr in the list.");
		}
	}
	
	@Override
	public void deleteOperator(OperatoerDTO opr) throws DALException {
		if(ADMIN) {
			OPdata.deleteOperatoer(opr);
		}
		/*
		 * Check for if the opr has been deleted, we do this by expecting 
		 * a null from the getOperatoer method, if no user is found a null
		 * is returned.
		 */
		if(OPdata.getOperatoer(opr.oprId).oprId != opr.oprId){
			System.out.println("Succes - opr has been deleted");
		}else{
			System.out.println("Faliure - opr was not deleted ");
		}


	}
	@Override
	public void updateOperator(OperatoerDTO opr) throws DALException {
		if(ADMIN) {
			OPdata.updateOperatoer(opr);

			/*
			 * Check if the operatoer has been updated, since the opr
			 * could update any part of the opr in the datalayer, we check
			 * if all parts of the new opr matches with everything from a opr 
			 * in the database(array list)
			 */
			if(OPdata.getOperatoer(opr.oprId).oprId == opr.oprId &&
					OPdata.getOperatoer(opr.oprId).cpr == opr.cpr &&
					OPdata.getOperatoer(opr.oprId).ini == opr.ini &&
					OPdata.getOperatoer(opr.oprId).oprNavn == opr.oprNavn &&
					OPdata.getOperatoer(opr.oprId).password == opr.password){
				System.out.println("Succes - Everything matches accordingly");
			}else{
				System.out.println("Faliure - Not everything or nothing matched");
				System.out.println("You expected:" );
				System.out.println(OPdata.getOperatoer(opr.oprId).cpr + " to be "+ opr.cpr );
				System.out.println(OPdata.getOperatoer(opr.oprId).ini + " to be "+ opr.ini );
				System.out.println(OPdata.getOperatoer(opr.oprId).oprId + " to be "+ opr.oprId);
				System.out.println(OPdata.getOperatoer(opr.oprId).oprNavn + " to be "+ opr.oprNavn );
				System.out.println(OPdata.getOperatoer(opr.oprId).cpr + " to be "+ opr.cpr );
			}
		}else{
			OPdata.updateOperatoer(opr);
		}

	}
	@Override
	public List<OperatoerDTO> ShowOperators() throws DALException {
		if(ADMIN) {
			System.out.println();
			return OPdata.getOperatoerList();	
		}else{
			System.out.println("Faliure to show the list. - not admin");	
		}

		return null;

	}
	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
			return	OPdata.getOperatoer(oprId);
		}
	
	//Just something initially made - should not look like this.
	public void isAdmin(boolean AdminActive){
		ADMIN = AdminActive;
	}

	@Override
	public boolean adminState() {	
		return ADMIN;
	}

	@Override
	public double weightCalc(double Netto, double Tara, String id) {
		Log.UpdateLog("Afvejning:Netto: "+Netto +" Tara: "+Tara, id);
		return Netto + Tara;
	}
}
