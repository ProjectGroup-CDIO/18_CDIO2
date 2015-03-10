package data;

import java.util.List;

import data.IOperatoerDAO.DALException;

public interface IFunktionalitet {
	
	void createOperator(OperatoerDTO opr)throws DALException;
	void deleteOperator(OperatoerDTO opr)throws DALException;
	OperatoerDTO getOperatoer(int oprId)throws DALException;
	public void updateOperator(OperatoerDTO opr)throws DALException;
	public List<OperatoerDTO> ShowOperators()throws DALException;
	public double weightCalc(double Netto, double TaraString, String id);
	public void isAdmin(boolean AdminActive);
	public boolean adminState();
	

}
