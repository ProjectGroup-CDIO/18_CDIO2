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
		String RM20 = "\"hey there\" \"how are you doing\" \"good?\"";
//		String RM20 = " \"\" \"\" \"\" ";
		assertTrue(ClientInput.checkRM20(RM20));
	}

}
