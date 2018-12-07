package starbucksTeam;

import starbucks.*;

import java.util.Map;

public class Coffe implements IItemJar {


    private TouchScreen tsf9;
    private Screen coffe = new Screen("Coffe");
    private IKeyNTouchObserver setCoffeh;
    private IDisplayComponent CoffeComponentScreen;


    //private KeyPad kp9;

    @Override
    public void load(IApp authApp, IFrame frame, Map<String, Screen> refScreens) {
        tsf9 = new TouchScreen(true);
        coffe.addSubComponent( tsf9 ) ;
        setCoffeh = new CoffeHandler(frame,refScreens,tsf9,CoffeComponentScreen);

        ((ITouchScreenSubject)tsf9).attach( setCoffeh ) ;


    }

    @Override
    public void accept(IAppVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Screen screen() {
        return coffe;
    }
}
