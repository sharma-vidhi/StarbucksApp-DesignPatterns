package starbucksTeam;
import starbucks.*;

import static junit.framework.TestCase.assertEquals;


import org.junit.Before;
import org.junit.Test;

public class TestOrder {
	IApp app;
	IItemJar setPin;
	IItemJar setOrder;
	IItemJar setCoffee;
	IItemJar setTea;
	String[] lines;
	@Before
	public void setUp() throws Exception {
		app = new AppAuthProxy() ;
		setPin= new PinScreen();      //add team members screens.
		setOrder =new Order();
		setCoffee =new Coffe();
		setTea =new Tea();

		setPin.accept((IAppVisitor)app);
		setOrder.accept((IAppVisitor)app);
		setCoffee.accept((IAppVisitor)app);
		setTea.accept((IAppVisitor)app);
	}

	@Test
	public void testLoadSuccess() {
		//String s=setPin.screen().name();
		//assertEquals(s,"SetPin");

		String o= setOrder.screen().name();
		assertEquals(o,"Order");


		
        String[] lines ;
        lines = app.screenContents().split("\n");  //to get info, you must get from lines.
        assertEquals("PinScreen", app.screen());
        app.touch(1,5) ;  // 1
        app.touch(2,5) ;  // 2
        app.touch(3,5) ;  // 3
        app.touch(1,6) ;  // 4
        assertEquals("MyCards", app.screen());
        app.execute("E") ; // Settings Page
        assertEquals("Settings", app.screen());
		app.touch(1,3) ;
		//app.touch(2,3) ;
		//app.touch(3,3) ;
		assertEquals("Order", app.screen());
		app.touch(2,1);
		assertEquals("Coffe", app.screen()); // Coffee screen
		app.touch(1,4);
		app.touch(3,1);
		assertEquals("MyCards", app.screen());
		app.execute("E") ; // Settings Page
		assertEquals("Settings", app.screen());
		app.touch(1,3) ;
		//app.touch(2,3) ;
		//app.touch(3,3) ;
		assertEquals("Order", app.screen());
		app.touch(2,2);
		assertEquals("Tea", app.screen()); // Tea screen





        
	}


}
