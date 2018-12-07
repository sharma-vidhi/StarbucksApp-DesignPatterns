package starbucksTeam;

import java.util.Map;
import java.util.ArrayList;
import starbucks.*;

public class OrderHandler implements IKeyNTouchObserver{
    private IFrame frame;
    private Map<String, Screen> refScreens;
    private IDisplayComponent OrderComponentScreen;

    public OrderHandler(IFrame frame,Map<String, Screen> refScreens, IDisplayComponent OrderComponentScreen)
    {
        this.refScreens=refScreens;
        this.frame = frame ;
        this.OrderComponentScreen = OrderComponentScreen;

    }

    public void setPosEvent(int x, int y) {

        boolean clickCoffe,clickTea;

        clickCoffe = (x==2 && y==1)  ;  	clickTea = (x==2 && y==2);

        System.err.println( "Touch Screen Touched at (" + x + ", " + y + ")" ) ;
        if(clickCoffe) {

            //this.frame.setCurrentScreen(refScreens.get("Coffe"));
            this.frame.setCurrentScreen((IScreen)this.refScreens.get("Coffe"));
            return;

        }

        if(clickTea) {

            this.frame.setCurrentScreen(refScreens.get("Tea"));
            return;
        }
    }



    @Override
    public void init() {


    }


    @Override
    public void keyEventUpdate(int i, String s) {

    }
}
