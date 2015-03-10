package data;

import java.util.ArrayList;
import java.util.List;



public class OperatoerDAO implements IOperatoerDAO {

	
	private class Operatoer{
		int oprId;                     
		String oprNavn;                
		String ini;                 
		String cpr;                 
		String password;

		public Operatoer(int oprId, String oprNavn, String ini, String cpr, String password){
			super();
			this.oprId = oprId;
			this.oprNavn = oprNavn;
			this.ini = ini;
			this.cpr = cpr;
			this.password = password;

		}
	}
		


	private ArrayList<Operatoer> DATAList;
	public OperatoerDAO(){

		DATAList = new ArrayList<Operatoer>();

		// ADD POEPLE
		DATAList.add(new Operatoer(10, "Sysadmin",null,null,"02324it!"));
		DATAList.add(new Operatoer(11,"Lars Larsen","LaLa","123456-7890","z_Dc2e"));
		DATAList.add(new Operatoer(12,"Helmut Huthut","HeHu","012345-6789","2Z_fge"));
		DATAList.add(new Operatoer(13,"Peder Person","PePe","111111-2222","F4_rte"));
		DATAList.add(new Operatoer(14,"Test Testason","TeTe","222222-3333","2247!aH"));

		
	}

	@Override
	public OperatoerDTO getOperatoer(int oprId) throws DALException {
		for(int i = 0; i < DATAList.size(); i++){
			if(DATAList.get(i).oprId == oprId) {
				return new OperatoerDTO(DATAList.get(i).oprId, DATAList.get(i).oprNavn,
						DATAList.get(i).ini, DATAList.get(i).cpr, DATAList.get(i).password);
			}
		}
		return new OperatoerDTO(0, "NULL","NULL", "NULL", "NULL");
	}

	@Override
	public List<OperatoerDTO> getOperatoerList() throws DALException {
		List<OperatoerDTO> List = new ArrayList<OperatoerDTO>();
		for(int i = 0; i < DATAList.size(); i++) {
			List.add(i, new OperatoerDTO(DATAList.get(i).oprId, DATAList.get(i).oprNavn,
					DATAList.get(i).ini, DATAList.get(i).cpr, DATAList.get(i).password));
		}
		return List;
	}

	@Override
	public void createOperatoer(OperatoerDTO opr) throws DALException {
		String password = PasswordGenerator.generate();
		if(opr.oprId < 100){
			DATAList.add(new Operatoer(opr.oprId,opr.oprNavn,opr.ini,opr.cpr,password));
			System.out.println("New user created with password: " + password);
		}else{
			System.out.println("Id > 99 so no user created.");
		}
	}
	/*
	 * (non-Javadoc)
	 * @see Data.IOperatoerDAO#updateOperatoer(Data.OperatoerDTO)
	 *You can update everything except the ID, If you wish to update ID
	 *You have to delete the previous user with the ID and create him
	 *again.
	 */

	@Override
	public void updateOperatoer(OperatoerDTO opr) throws DALException {
		for(int i = 0; i< DATAList.size();i++){
			if(opr.oprId == DATAList.get(i).oprId){
				if(!opr.ini.equals("NULL")){
					DATAList.get(i).ini = opr.ini;	
				}if(!opr.oprNavn.equals("NULL")){
					DATAList.get(i).oprNavn = opr.oprNavn;
				}if(!opr.password.equals("NULL")){
					DATAList.get(i).password = opr.password;
				}if(!opr.cpr.equals("NULL")){
					DATAList.get(i).cpr = opr.cpr;
				}
			}
		}


	}


	@Override
	public void deleteOperatoer(OperatoerDTO opr) throws DALException {
		for(int i = 0; i< DATAList.size();i++){
			if(opr.oprId == DATAList.get(i).oprId){
				DATAList.remove(i);
			}

		}

	}

}