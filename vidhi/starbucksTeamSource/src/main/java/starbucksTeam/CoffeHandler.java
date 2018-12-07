package starbucksTeam;

import starbucks.*;

import java.util.Map;

public class CoffeHandler  implements IKeyNTouchObserver {
    private IFrame frame;
    private Map<String, Screen> refScreens;
    private IDisplayComponent CoffeComponentScreen;
    String finalOrder;
    //this.setContent();
    StarbuckBuilder coffe = new StarbuckBuilder();
    public CoffeHandler(IFrame frame, Map<String, Screen> refScreens, TouchScreen tsf9,IDisplayComponent CoffeComponentScreen) {

        this.refScreens=refScreens;
        this.frame = frame ;
        this.CoffeComponentScreen = CoffeComponentScreen;
    }

    @Override
    public void setPosEvent(int x, int y) {
        boolean clickFlavourCMO,clickFlavourCL,clickFlavourCM,clickSizeS,clickSizeT,clickSizeG,clickMilkA,clickMilk2,clickMilkC,clickAdd;

        clickFlavourCMO = (x==1 && y==4)  ;
        clickFlavourCL= (x==2 && y==4)  ;
        clickFlavourCM= (x==3 && y==4)  ;
        clickSizeS= (x==1 && y==5);
        clickSizeT= (x==2 && y==5);
        clickSizeG= (x==3 && y==5);
        clickMilkA =(x==1 && y==7);
        clickMilk2 =(x==2 && y==7);
        clickMilkC =(x==3 && y==7);

        clickAdd=(x==3 && y==1);




        System.err.println( "Touch Screen Touched at (" + x + ", " + y + ")" ) ;
        if(clickFlavourCMO) {
            coffe.withFlavour(Flavour.CMO);
            return ;
        }

        if(clickFlavourCL) {
            coffe.withFlavour(Flavour.CL);
            return;
        }
        if(clickFlavourCM) {
            coffe.withFlavour(Flavour.CM);
            return;
        }
        if( clickSizeS) {
            coffe.withSize(Size.Short);
            return;
        }
        if(clickSizeT) {
            coffe.withSize(Size.Tall);
            return;
        }
        if(clickSizeG) {
            coffe.withSize(Size.Grande);
            return;
        }
        if(clickMilkA) {
            coffe.withMilk(Milk.Almond);
            return;
        }
        if(clickMilk2) {
            coffe.withMilk(Milk.Milk2);
            return;
        }
        if(clickMilkC) {
            coffe.withMilk(Milk.Coconut);
            return;
        }
        if(clickAdd){

            coffe.finalReceipt();
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
