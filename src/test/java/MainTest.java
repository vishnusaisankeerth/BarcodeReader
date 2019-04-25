import static org.junit.Assert.*;

import org.iiitb.BarcodeReader.DatabaseConnection;
import org.iiitb.model.register;
import org.junit.Test;

public class MainTest {

	@Test
	public void TestBreakFast() {
		register dummy_register = new register();
		register original = new register();
		dummy_register.setbf("Yes");
		dummy_register.setlunch("Yes");
		dummy_register.setdinner("Yes");
		dummy_register.setRollNum("IMT2015510");
		dummy_register.setDate("24-04-2019");
				
		DatabaseConnection dc = new DatabaseConnection();
		original = dc.get_student_date_status("IMT2015510","2019-04-24");
		System.out.println(dummy_register.getbf());
		assertEquals(dummy_register.getbf(),original.getbf());
	}
	@Test
	public void TestLunch() {
		register dummy_register = new register();
		register original = new register();
		dummy_register.setbf("Yes");
		dummy_register.setlunch("Yes");
		dummy_register.setdinner("Yes");
		dummy_register.setRollNum("IMT2015510");
		dummy_register.setDate("24-04-2019");
				
		DatabaseConnection dc = new DatabaseConnection();
		original = dc.get_student_date_status("IMT2015510","2019-04-24");
		System.out.println(dummy_register.getbf());
		assertEquals(dummy_register.getlunch(),original.getlunch());
	}

}
