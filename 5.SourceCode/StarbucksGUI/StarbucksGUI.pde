 //<>//
import starbucks.*;

//Each of us need to import our jar file here
import starbucksYH.*;

import java.awt.Point;
import java.util.Arrays;


boolean debug=false;
String[] screens = { "PinScreen", "MyCards", "AddCard", "MyCardsOptions","MyCardsMoreOptions", "MyCardsPay", "Rewards","Settings", "Store","Payments","SetPin"};
PImage[] screenImages = new PImage[screens.length];

int kx,ky;
int headerHeight=60;


String pin = "";
String pinInput = "";
int w=320;
int h=headerHeight+55*8;//before:480; now: 500
starbucks.AppAuthProxy app = new starbucks.AppAuthProxy() ;

// Each of us need to add our app here
starbucksYH.AppAuthProxy appYH=new starbucksYH.AppAuthProxy();

String[] lines ;

void setup() {


  size(320,500);  //480
  // Make a new instance of a PImage by loading an image file
  for (int i=0; i < screens.length; i++) {
    screenImages[i] = loadImage(screens[i]+".jpg");
    screenImages[i].resize(w,h);
  }
  textAlign(LEFT);
  fill(0, 102, 153, 204);
  frameRate(12);

}

void draw() {
 

  
  textSize(32);
  String screen=app.screen();
  int sIndex=Arrays.asList(screens).indexOf(screen);
  background(screenImages[sIndex]);
  //System.out.print(screen);
  switch(screen) 
        { 
            case "PinScreen": // Yinghua Project
                starbucksYH.PinEntryMachine pm;
                pm=appYH.getPinEntryMachin();
                pinInput=pm.d1()+pm.d2()+pm.d3()+pm.d4();            
                fill(0, 102, 153, 204);
                for (int i =0; i < min(pinInput.length(),4); i++) {
                  text (pinInput.substring(i,i+1), 40+i*70, 90, 50, 50);
                }
              
              lines = appYH.screenContents().split("\n");
              if(lines[3].trim().equals("Invalid Pin")){
                      textSize(18);
                      textAlign(CENTER);
                      fill(255, 255, 255, 255);
                      text ("Invalid Pin", 20, 160, 270, 50);
              }
              break;

            case "SetPin":  //Yinghua project
              lines = appYH.screenContents().split("\n");
              pin=lines[5].trim().replace("[","").replace("]","");
              fill(0, 102, 153, 204);
              for (int i =0; i < min(pin.length(),4); i++) {
                text (pin.substring(i,i+1), 40+i*70, 90, 50, 50);
              } 
              break;

            case "MyCards":  
              lines = app.screenContents().split("\n");
              String balance=lines[7];
                      textAlign(CENTER);
                      fill(255, 255, 255, 255);
                      text (balance, 20, 160, 270, 50);
              break;  
              
            case "AddCard":
              lines = app.screenContents().split("\n"); 
              String cardId=lines[4].trim().replace("[","").replace("]",""); //assertEquals("[123456789]", lines[4].trim()); 
              String cardcode=lines[5].trim().replace("[","").replace("]",""); //assertEquals("[999]", lines[5].trim());
              text (cardId, 20, 160, 270, 50);
              text (cardcode, 20, 180, 270, 50);
              break;            
             
            case "MyCardsMoreOptions":
              break;
            case "MyCardsPay":
              break;
            case "Rewards":
              break;
            case "Settings":
              break;
            case "Store":
              break;
            case "Payments":
              break;
             
        }
        
          //Synchronize the screen in all apps
          switch (screen) {
            case "PinScreen":
                if(appYH.screen().equals("PinScreen"))  
                    app.authenticated=false;
                //else if(appYH.screen().equals("MyCards"))  app.execute("A");
                else
                if(!appYH.screen().equals("PinScreen")) {
                    app.authenticated=true;app.execute("A");
                }
            break;
          case "SetPin":
            if(appYH.screen().equals("Settings")) 
                  app.execute("E");
           if(appYH.screen().equals("PinScreen"))  
                    app.authenticated=false;
            break;
          }
        
  if (debug) {
       fill(0, 102, 153);
      textSize(25);
      textAlign(LEFT);
      fill(255, 255, 255, 255);
      text ("-"+kx+"-"+ky+"-", 0, 20, 270, 50);
//      text (app.screen(), 0, 30, 270, 50);  // show the current screen of app
//      text (appYH.screen(), 0, 60, 270, 50);  // show the current screen of app Yinghua - it should be same as the screen of app
    }

}

void mousePressed() {
  //int x = 0;
  //int y = 260;
  //int button_width = 106;
  //int button_height = 55;
 
  
  Point kpRowColumn=new Point(3,8);
  Point kpLeftTop=new Point(0,headerHeight);  //260:keypad;40:touch
  Point buttonSize=new Point(106,55);

  //int kx,ky;  //keypress at (kx,ky):4x3
  kx=((int)(mouseX - kpLeftTop.x))/((int)buttonSize.x)+1;
  ky=((int)(mouseY - kpLeftTop.y))/((int)buttonSize.y)+1;
  
  
  //boolean kpzone= ( ky > 4 && ky<9 && kx<=3 && kx>=1 );
  //boolean tzone= (fullScreen || ky <= 4 );
   

  if((kx>=1 && kx<=kpRowColumn.x) && (ky>=1 && ky<=kpRowColumn.y)) {
    allJarsSync_keyInput();
  }
  

  Point menuSize=new Point(w/5,55);
  boolean hasMenu=(!app.screen().equals("PinScreen") && !app.screen().equals("AddCard"));
  if(hasMenu && ky==8) {
       char mA='A';
       int mIndex=((int)(mouseX - kpLeftTop.x))/((int)menuSize.x);
       char mTrigger=(char)((int)mA+mIndex);
       allJarsSync_menu(mTrigger);
  }
   
  if(app.screen().equals("AddCard") || app.screen().equals("SetPin")) {  //11/26/2018 add set pin
    if(kx==3 && ky==1)  allJarsSync_next() ;
    if(kx==1 && ky==1) allJarsSync_prev();
  }
  
  if(app.screen().equals("MyCards")) {
    if(kx==3 && ky==1)  allJarsSync_next();
  }

}

//try sync all team members' jars

void allJarsSync_keyInput() {
    switch(app.screen()){
      case "SetPin":
      case "PinScreen":
        appYH.touch(kx,ky);
        break;
      default: 
        appYH.touch(kx,ky);      
        app.touch(kx,ky);
    }
}

void allJarsSync_menu(char mTrigger) {
  app.execute(String.valueOf(mTrigger)) ;
  appYH.execute(String.valueOf(mTrigger)) ;
}


void allJarsSync_next() {
    switch(app.screen()){
      case "SetPin":
      case "PinScreen":
        appYH.next();
        break;
      default: 
        appYH.next();
        app.next();
    }
}


void allJarsSync_prev() {
    switch(app.screen()){
      case "SetPin":
      case "PinScreen":
        appYH.prev();
        break;
      default: 
        appYH.prev();
        app.prev();
    }

}
