package data;
import java.util.Random;


public class PasswordGenerator {

	public static String generate() {
		//Here are the values that are in the password
		char[] values1 = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		char[] values2 = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char[] values3 = {'1','2','3','4','5','6','7','8','9','0'};
		char[] values4 = {'.','-','_','+','!','?','='};
		char[] mycharArray = new char[6];
		
		//places the values in an array
		Random rand = new Random();
		mycharArray[0]= values1[rand.nextInt(values1.length)];
		mycharArray[1]= values1[rand.nextInt(values1.length)];
		mycharArray[2]= values1[rand.nextInt(values1.length)];
		mycharArray[3]= values2[rand.nextInt(values2.length)];
		mycharArray[4]= values3[rand.nextInt(values3.length)];
		mycharArray[5]= values4[rand.nextInt(values4.length)];
		
		//This part scrambles the position of the values with bogosort
		for (int i = 0; i < mycharArray.length; i++){  
		    int randomPosition = rand.nextInt(mycharArray.length);  
		
		    char temp = mycharArray[i];  
		    mycharArray[i] = mycharArray[randomPosition];  
		    mycharArray[randomPosition] = temp;  
		}
		
		//return the randomly sorted array
			
			return new String(mycharArray);
	}
}


