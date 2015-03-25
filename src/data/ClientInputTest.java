package data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ClientInputTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCheckRM20() {
		String RM20 = "\"hey there\" \"how are you doing\" \"good?\"\r\n";
//		String RM20 = " \"\" \"\" \"\" ";
		assertTrue(ClientInput.checkRM20(RM20));
	}
	@Test
	public void testCheckRM20nr2(){
		String RM20 = "\"hey there \"how are you doing\" \"good?\"\r\n";
		assertFalse(ClientInput.checkRM20(RM20));
	}

}
