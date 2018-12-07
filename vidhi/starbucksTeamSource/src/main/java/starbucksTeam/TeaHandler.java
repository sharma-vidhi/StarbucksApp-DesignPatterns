package starbucksTeam;

import starbucks.*;

import java.util.Map;

public class TeaHandler implements IKeyNTouchObserver {
    private IFrame frame;
    private Map<String, Screen> refScreens;
    private IDisplayComponent TeaComponentScreen;
    String finalOrder;
    //this.setContent();
    StarbuckBuilder tea = new StarbuckBuilder();
    public TeaHandler(IFrame frame, Map<String, Screen> refScreens, TouchScreen tsf9, IDisplayComponent TeaComponentScreen) {

        this.refScreens=refScreens;
        this.frame = frame ;
        this.TeaComponentScreen = TeaComponentScreen;
    }

    @Override
    public void setPosEvent(int x, int y) {
        boolean clickFlavourTea,clickSizeS,clickSizeT,clickSizeG,clickMilkA,clickMilk2,clickMilkC,clickAdd;

        clickFlavourTea = (x==2 && y==4)  ;
        clickSizeS= (x==1 && y==5);
        clickSizeT= (x==2 && y==5);
        clickSizeG= (x==3 && y==5);
        clickMilkA =(x==1 && y==7);
        clickMilk2 =(x==2 && y==7);
        clickMilkC =(x==3 && y==7);

        clickAdd=(x==3 && y==1);


        System.err.println( "Touch Screen Touched at (" + x + ", " + y + ")" ) ;
        if(clickFlavourTea) {
            tea.withFlavour(Flavour.Tea);
            return ;
        }
        if( clickSizeS) {
            tea.withSize(Size.Short);
            return;
        }
        if(clickSizeT) {
            tea.withSize(Size.Tall);
            return;
        }
        if(clickSizeG) {
             tea.withSize(Size.Grande);
            return;
        }
        if(clickMilkA) {
            tea.withMilk(Milk.Almond);
            return;
        }
        if(clickMilk2) {
            tea.withMilk(Milk.Milk2);
            return;
        }
        if(clickMilkC) {
            tea.withMilk(Milk.Coconut);
            return;
        }
        if(clickAdd){
            tea.finalReceipt();
            init();
            frame.setCurrentScreen(refScreens.get("MyCards"));
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
