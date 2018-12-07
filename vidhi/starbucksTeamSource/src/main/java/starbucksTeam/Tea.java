package starbucksTeam;

import starbucks.*;

import java.util.Map;

public class Tea implements IItemJar {


    private TouchScreen tsf9;
    private Screen tea = new Screen("Tea");
    private IKeyNTouchObserver setTeah;
    private IDisplayComponent TeaComponentScreen;


    //private KeyPad kp9;

    @Override
    public void load(IApp authApp, IFrame frame, Map<String, Screen> refScreens) {
        tsf9 = new TouchScreen(true);
        tea.addSubComponent( tsf9 ) ;
        setTeah = new TeaHandler(frame,refScreens,tsf9,TeaComponentScreen);

        ((ITouchScreenSubject)tsf9).attach( setTeah ) ;


    }

    @Override
    public void accept(IAppVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Screen screen() {
        return tea;
    }
}
