package starbucksTeam;

import starbucks.*;

import java.util.Map;

public class Order implements IItemJar {

    private TouchScreen tsf9;
    private Screen order = new Screen("Order");
    private IKeyNTouchObserver setOrderh;


    //private KeyPad kp9;

    @Override
    public void load(IApp authApp, IFrame frame, Map<String, Screen> refScreens) {
        tsf9 = new TouchScreen(true);
        order.addSubComponent( tsf9 ) ;
        setOrderh = new OrderHandler(frame,refScreens,tsf9);

        ((ITouchScreenSubject)tsf9).attach( setOrderh ) ;


    }

    @Override
    public void accept(IAppVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Screen screen() {
        return order;
    }
}
