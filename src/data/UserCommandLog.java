package data;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class UserCommandLog {
	public void UpdateLog(String command, String oprId){

		Calendar cal = Calendar.getInstance();
    	cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("LogFile.txt", true)))) {
			    out.println("OprId: " + oprId + " command: "+ command  + sdf.format(cal.getTime()));
			}catch (IOException e) {
			    System.out.println("Exception occured no log created.");
			
			}
	
		}
		
	}
