package starbucksTeam;
import starbucks.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestSetPin {
	IApp app;
	IItemJar setPin;
	@Before
	public void setUp() throws Exception {
		app = new AppAuthProxy() ;
		setPin= new PinScreen();      //add team members screens.
		setPin.accept((IAppVisitor)app);
	}

	@Test
	public void testLoadSuccess() {
		String s=setPin.screen().name();
		assertEquals(s,"SetPin");
		
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
        app.touch(1,4) ;
        assertEquals("SetPin", app.screen());
        app.touch(1,5) ;  // 1
        app.touch(2,5) ;  // 2
        app.touch(3,1) ;
        //app.next() ; //save valid key
        assertEquals("PinScreen", app.screen());
        
	}

}
