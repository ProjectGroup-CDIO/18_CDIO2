package data;




public class OperatoerDTO{

	int oprId;                     
	String oprNavn;                
	String ini;                 
	String cpr;                 
	String password;
	
	
	public OperatoerDTO(int oprId, String oprNavn, String ini, String cpr, String password){
		this.oprId = oprId;
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.password = password;
	
	}
	
	public int getOprId() {
		return oprId;
	}
	public String getOprNavn() {
		return oprNavn;
	}
	public String getIni() {
		return ini;
	}
	public String getCpr() {
		return cpr;
	}
	public String getPassword() {
		return password;
	}

}




//	public int getOprId(String cpr)throws DataException {
//		int i = 0;
//		while(true){
//			try {
//				if (operatoer.get(i).cpr.equals(cpr)){
//					return operatoerList.get(i).oprId;
//				}
//			}catch(IndexOutOfBoundsException e){
//				throw new DataException("Fandt ikke et cpr nr der passsede");
//			}
//			i++;
//		}
//	}
//	
////	public void setOprId(int currentoprId, int newoprId) throws DataException {
////		int i = 0;
////		while(true){
////			try{
////				if(operatoerList.get(i).oprId == currentoprId){
////					operatoerList.get(i).oprId = newoprId;
////				}
////			}catch(IndexOutOfBoundsException e){
////				throw new DataException("Fandt ikke et oprId der passsede");
////
////
////			}
////		}
////	}
//	
//	public String getOprNavn(int oprId) throws DataException {
//		int i = 0;
//		while(true){
//			try{
//				if(operatoerList.get(i).oprId == oprId){
//					return operatoerList.get(i).oprNavn;
//				}
//			}catch(IndexOutOfBoundsException e){
//				throw new DataException("Fandt ikke et oprId der passsede");
//			}
//		}
//	}
//	
////	public void setOprNavn(String oprNavn, int oprId) throws DataException {
////		int i = 0;
////		while(true){
////			try{
////				if(operatoerList.get(i).oprId == oprId){
////					operatoerList.get(i).oprNavn = oprNavn;
////				}
////			}catch(IndexOutOfBoundsException e){
////				throw new DataException("Fandt ikke et oprId der passsede");
////
////
////			}
////		}
////	}
//	
//	public String getIni(int oprId) throws DataException {
//		int i = 0;
//		while(true){
//			try{
//				if(operatoerList.get(i).oprId == oprId){
//					return operatoerList.get(i).ini;
//				}
//			}catch(IndexOutOfBoundsException e){
//				throw new DataException("Fandt ikke et oprId der passsede");
//			}
//		}
//	}
//	
////	public void setIni(String ini, int oprId) throws DataException {
////		int i = 0;
////		while(true){
////			try{
////				if(operatoerList.get(i).oprId == oprId){
////					operatoerList.get(i).ini = ini;
////				}
////			}catch(IndexOutOfBoundsException e){
////				throw new DataException("Fandt ikke et oprId der passsede");
////
////
////			}
////		}
////
////
////	}
//	
//	public String getPassword(int oprId) throws DataException {
//		int i = 0;
//		while(true){
//			try{
//				if(operatoerList.get(i).oprId == oprId){
//					return operatoerList.get(i).password;
//				}
//			}catch(IndexOutOfBoundsException e){
//				throw new DataException("Fandt ikke et oprId der passsede");
//			}
//		}
//	}
//	
////	public void setPassword(String password,int oprId) throws DataException {
////		int i = 0;
////		while(true){
////			try{
////				if(operatoerList.get(i).oprId == oprId){
////					operatoerList.get(i).password = password;
////				}
////			}catch(IndexOutOfBoundsException e){
////				throw new DataException("Fandt ikke et oprId der passsede");
////
////
////			}
////		}
////	}
//	
//	public String getCpr(int oprId) throws DataException {
//		int i = 0;
//		while(true){
//			try{
//				if(operatoerList.get(i).oprId == oprId){
//					return operatoerList.get(i).cpr;
//				}
//			}catch(IndexOutOfBoundsException e){
//				throw new DataException("Fandt ikke et oprId der passsede");
//			}
//		}
//	}
//}
