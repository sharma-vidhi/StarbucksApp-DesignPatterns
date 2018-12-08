import starbucksSM.*; //<>//

import starbucksSM.*;

import starbucks.*;

//Each of us need to import our jar file here
import starbucksYH.*;

import java.awt.Point;
import java.util.Arrays;


int rectX, rectY, rectWidth, rectHeight;

boolean rectOver = false;
boolean debug=true;
boolean enablePay = false;
boolean pinSelected = false;
boolean overFour = false;
boolean overSix = false;
boolean overZero = false;
boolean sixPinEnabled = false;
boolean fourPinEnabled = false;
boolean zeroPinEnabled = false;
boolean cardIdFocus = false;
boolean cardfocus = true;
String cursor = "_";

boolean blink = true;


float a = 95;

color buttontHighlight;
color pinBackground;
color fourPinBackground;
color sixPinBackground;
color zeroPinBackground;

String[] screens = { "PinScreen", "MyCards", "AddCard", "MyCardsOptions","MyCardsMoreOptions", "MyCardsPay", "Rewards","Settings", "Store","Payments","SetPin"};
String[] keypadButtons = { "One", "Two", "Three", "Four","Five", "Six", "Seven","Eight", "Nine","Spacer","Zero","BackSpace"};
PImage[] screenImages = new PImage[screens.length];
PImage[] keypadButtonImages = new PImage[keypadButtons.length];
PImage[] keypadButtonRollImages = new PImage[keypadButtons.length];
PImage[] keypadButtonDownImages = new PImage[keypadButtons.length];
ImageButtons[] keypadImageButtons = new ImageButtons[keypadButtons.length];

PImage[] keypadButtonImagesPS = new PImage[keypadButtons.length];
PImage[] keypadButtonRollImagesPS = new PImage[keypadButtons.length];
PImage[] keypadButtonDownImagesPS = new PImage[keypadButtons.length];
ImageButtons[] keypadImageButtonsPS = new ImageButtons[keypadButtons.length];

int kx,ky;
int headerHeight=60;

PFont font;
PImage screenBlur;
PImage mycardPayTouch;
PImage pinSelect;

String pin = "";
String pinInput = "";
int w=320;
int h=headerHeight+55*8;//before:480; now: 500
starbucks.AppAuthProxy app = new starbucks.AppAuthProxy() ;

// Each of us need to add our app here
starbucksYH.AppAuthProxy appYH=new starbucksYH.AppAuthProxy();
starbucksSM.AppAuthProxy appSM=new starbucksSM.AppAuthProxy();

starbucksSM.PinEntryMachine pm;

String[] lines ;
String balance = "$0.00";

void setup() {


  size(320,500);  //480
  rectX = 0;
  rectY = 20;
  rectWidth = 100;
  rectHeight = 30;
  buttontHighlight = color(49,49,49);
  pinBackground = color(76,76,76);
  // Make a new instance of a PImage by loading an image file
  
  for (int i=0; i < screens.length; i++) {
    screenImages[i] = loadImage("/Images/"+screens[i]+".jpg");
    screenImages[i].resize(w,h);
  }
  screenBlur = loadImage("/Images/MyCardsPayBlur.png");
  screenBlur.resize(w,h);
  mycardPayTouch = loadImage("/Images/MyCardsPayTouch.png");
  mycardPayTouch.resize(w,h);
  //pinSelect = loadImage("/Images/PinSetting.jpg");
  //pinSelect.resize(w,h);
  
  for (int i = 0; i<keypadButtons.length; i++)
  {
    keypadButtonImages[i] = loadImage("/keypadButtons/AddCard/new/"+keypadButtons[i]+".png");
   // keypadButtonImages[i].resize(106,56);
    keypadButtonRollImages[i] = loadImage("/keypadButtons/AddCard/new/"+keypadButtons[i]+"Roll.png");
   // keypadButtonRollImages[i].resize(106,56);
    keypadButtonDownImages[i] = loadImage("/keypadButtons/AddCard/new/"+keypadButtons[i]+"Down.png");
   // keypadButtonDownImages[i].resize(106,56);
    
    keypadButtonImagesPS[i] = loadImage("/keypadButtons/PinScreen/"+keypadButtons[i]+".png");
    keypadButtonImagesPS[i].resize(106,56);
    keypadButtonRollImagesPS[i] = loadImage("/keypadButtons/PinScreen/"+keypadButtons[i]+"Roll.png");
    keypadButtonRollImagesPS[i].resize(106,56);
    keypadButtonDownImagesPS[i] = loadImage("/keypadButtons/PinScreen/"+keypadButtons[i]+"Down.png");
    keypadButtonDownImagesPS[i].resize(106,56);
    
    if(i<3)
    {
      keypadButtonImages[i].resize(106,55);
      keypadButtonRollImages[i].resize(106,55);
      keypadButtonDownImages[i].resize(106,55);
      keypadImageButtons[i] = new ImageButtons(i*106, h-((4)*56), 106, 56, keypadButtonImages[i], keypadButtonRollImages[i], keypadButtonDownImages[i]);
      keypadImageButtonsPS[i] = new ImageButtons(i*106, h-((4)*56), 106, 56, keypadButtonImagesPS[i], keypadButtonRollImagesPS[i], keypadButtonDownImagesPS[i]);
    }
    else if(i<6)
    {
      keypadImageButtons[i] = new ImageButtons((i%3)*106, h-((3)*56), 106, 56, keypadButtonImages[i], keypadButtonRollImages[i], keypadButtonDownImages[i]);
      keypadImageButtonsPS[i] = new ImageButtons((i%3)*106, h-((3)*56), 106, 56, keypadButtonImagesPS[i], keypadButtonRollImagesPS[i], keypadButtonDownImagesPS[i]);
    }
    else if(i<9)
    {
      keypadImageButtons[i] = new ImageButtons((i%3)*106, h-((2)*56), 106, 56, keypadButtonImages[i], keypadButtonRollImages[i], keypadButtonDownImages[i]);
      keypadImageButtonsPS[i] = new ImageButtons((i%3)*106, h-((2)*56), 106, 56, keypadButtonImagesPS[i], keypadButtonRollImagesPS[i], keypadButtonDownImagesPS[i]);
    }
    else {
      keypadImageButtons[i] = new ImageButtons((i%3)*106, h-((1)*56), 106, 56, keypadButtonImages[i], keypadButtonRollImages[i], keypadButtonDownImages[i]);
      keypadImageButtonsPS[i] = new ImageButtons((i%3)*106, h-((1)*56), 106, 56, keypadButtonImagesPS[i], keypadButtonRollImagesPS[i], keypadButtonDownImagesPS[i]);
    }
    
    
  }
  textAlign(LEFT);
  fill(0, 102, 153, 204);
  frameRate(12);

}

void draw() {
 

  
  textSize(32);
  String screen=appSM.screen();
  int sIndex=Arrays.asList(screens).indexOf(screen);
  background(screenImages[sIndex]);
 // text ("-"+appSM.screen()+"-", 0, 20, 270, 50);
  update();
  //System.out.print(screen);
  switch(screen) 
        { 
            case "PinScreen": // Yinghua Project
                
                  

                for (int i = 0; i<keypadButtons.length; i++)
                  {
                    keypadImageButtonsPS[i].update();
                    keypadImageButtonsPS[i].display();
                  }
                  //YH
                 pm=appSM.getPm();
                pinInput=pm.d1()+pm.d2()+pm.d3()+pm.d4()+pm.d5()+pm.d6();            
                int p=0;
                if(sixPinEnabled)
                {
                  sixPins();
                  fill(0, 102, 153, 204);
                  textSize(18);
                  textAlign(CENTER, CENTER);
                  for (int i =0; i < min(pinInput.length(),6); i++) {
                    p = i*40 + (i+1)*11;
                  text (pinInput.substring(i,i+1), p + 20, 90 + 20);
                }
                }
                else
                {
                  loadDefaultFont();
                  fill(0, 102, 153, 204);
                  for (int i =0; i < min(pinInput.length(),4); i++) {
                  text (pinInput.substring(i,i+1), 40+i*70, 110, 50, 50);
                }
                }
                
              //YH
              lines = appSM.screenContents().split("\n");
              if(lines[3].trim().equals("Invalid Pin")){
                      textSize(15);
                      textAlign(CENTER);
                      fill(255, 255, 255, 255);
                      text ("Invalid Pin", 20, 160, 270, 50);
              }
              break;

            case "SetPin":  //Yinghua project
                //lines = appSM.screenContents().split("\n");
                //pin=lines[5].trim().replace("[","").replace("]","");
                //fill(0, 102, 153, 204);
                //for (int i =0; i < min(pin.length(),4); i++) {
                //  text (pin.substring(i,i+1), 40+i*70, 90, 50, 50);
                //} 
                fill(color(72,72,72));
                noStroke();
                rect(10, 50, 100, 30, 50, 0,0,0);
                fill(color(68,68,68));
                rect(110, 50, 100, 30, 0, 0,0,0);
                fill(color(40,40,40));
                rect(210, 50, 50, 30, 0, 0,0,0);
                fill(color(30,30,30));
                rect(260, 50, 100, 30, 0, 0,0,0);
                fill(color(255,255,255));
                font = loadFont("Calibri-Bold-15.vlw");
                textSize(15);
                text ("Please set your passcode",70, 70 );
                stroke(3);
                
                pinOptions();
                for (int i = 0; i<keypadButtons.length; i++)
                  {
                    keypadImageButtonsPS[i].update();
                    keypadImageButtonsPS[i].display();
                  }
                pm=appSM.getPm();
                pinInput=pm.d1()+pm.d2()+pm.d3()+pm.d4()+pm.d5()+pm.d6();            
                int q=0;
                if(sixPinEnabled)
                {
                  sixPins();
                  fill(0, 102, 153, 204);
                  textSize(18);
                  for (int i =0; i < min(pinInput.length(),6); i++) {
                    q = i*40 + (i+1)*11;
                  text (pinInput.substring(i,i+1), q + 20, 90 + 20);
                }
                }
                else
                {
                  fill(0, 102, 153, 204);
                  for (int i =0; i < min(pinInput.length(),4); i++) {
                  text (pinInput.substring(i,i+1), 40+i*70, 90, 50, 50);
                }
                }
              break;

            case "MyCards":  
            loadDefaultFont();
              a = 95;
              fill(255,255,255);
              font = loadFont("Georgia-50.vlw");
              lines = appSM.screenContents().split("\n");
              balance=lines[7];
              textFont(font);
              textAlign(CENTER);
              
              //text (balance, 20, 160, 270, 50);
              text (balance, 160, 330);
              //loadDefaultFont();
              break;  
              
            case "AddCard":
              for (int i = 0; i<keypadButtons.length; i++)
              {
                keypadImageButtons[i].update();
                keypadImageButtons[i].display();
              }
              font = loadFont("YuGothic-Bold-20.vlw");
              lines = appSM.screenContents().split("\n"); 
              String cardId=lines[4].trim().replace("[","").replace("]",""); //assertEquals("[123456789]", lines[4].trim()); 
              String cardcode=lines[5].trim().replace("[","").replace("]",""); //assertEquals("[999]", lines[5].trim());
               if(frameCount % 10 == 0){
                blink = !blink;
              }
              textFont(font);
              fill(50);
              if(blink){
                text (cardId, 96, 158);
                text (cardcode, 144, 199);
              }
              else{
                if(cardfocus)
                text (cardId+cursor, 96, 158);
                else
                text (cardId, 96, 158);
                if(cardIdFocus)
                text (cardcode+cursor, 144, 199);
                else
                text (cardcode, 144, 199);
                //text(cardId+cursor, 100, 100, 200, 200);
              }
              loadDefaultFont();
              break;            
             
            case "MyCardsMoreOptions":
              lines = appSM.screenContents().split("\n");
              String ba = balance; 
              textAlign(CENTER);
              fill(255,255,255,255);
              text(ba,260, 130);
              break;
            case "MyCardsPay":
            font = loadFont("SegoeUI-Semibold-18.vlw");
              lines = appSM.screenContents().split("\n");
              cardId =lines[6].trim().replace("[","").replace("]","");           
              textAlign(CENTER);
              textFont(font);
              fill(50);
              //text (balance, 20, 160, 270, 50);
              //textSize(20);
              text (cardId, 75, 91, 225, 91);  
               if(kx==2 && (ky == 4) || (ky == 5) || (ky == 6))
               {
                 fill(50);
                 background(screenBlur);
                 textSize(20);
                 text (cardId, 75, 91, 225, 91); 
                 strokeWeight(3);
                 line(a, 110, a, 182);
                 a = a + 3.5;
                if (a > 275) { 
                  background(mycardPayTouch); 
                  kx = 3;
                  loadDefaultFont();
                  if(enablePay)
                  {
                   invokePay();
                   enablePay = false;
                  }
                }
                
               }
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
          //switch (screen) {
          //  case "PinScreen":
          //      if(appYH.screen().equals("PinScreen"))  
          //          app.authenticated=false;
          //      //else if(appYH.screen().equals("MyCards"))  app.execute("A");
          //      else
          //      if(!appYH.screen().equals("PinScreen")) {
          //          app.authenticated=true;app.execute("A");
          //      }
          //  break;
          //case "SetPin":
          //  if(appYH.screen().equals("Settings")) 
          //        app.execute("E");
          // if(appYH.screen().equals("PinScreen"))  
          //          app.authenticated=false;
          //  break;
          //}
        
  if (debug) {
       fill(0, 102, 153);
      textSize(25);
      textAlign(LEFT);
      fill(255, 255, 255, 255);
      text ("-"+kx+"-"+ky+"-", 0, 20, 270, 50);
     // text (mouseX + " : " + mouseY, 0, 20, 270, 50);
      //text (screen, 0, 30, 270, 50);  // show the current screen of app
//      text (appYH.screen(), 0, 60, 270, 50);  // show the current screen of app Yinghua - it should be same as the screen of app
    }

}

boolean overMyCardsPayCircle() {
  float disX = 270 - mouseX;
  float disY = 225 - mouseY;
  int diameter = 60;
  if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
    return true;
  } else {
    return false;
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
  boolean hasMenu=(!appSM.screen().equals("PinScreen") && !appSM.screen().equals("AddCard"));
  if(hasMenu && ky==8) {
       char mA='A';
       int mIndex=((int)(mouseX - kpLeftTop.x))/((int)menuSize.x);
       char mTrigger=(char)((int)mA+mIndex);
       allJarsSync_menu(mTrigger);
  }
   
  if(appSM.screen().equals("AddCard") || appSM.screen().equals("SetPin")) {  //11/26/2018 add set pin
    if(kx==3 && ky==1)  allJarsSync_next() ;
    if(kx==1 && ky==1) allJarsSync_prev();
  }
  
  if(appSM.screen().equals("MyCards")) {
    if(kx==3 && ky==1)  allJarsSync_next();
  }

}

//try sync all team members' jars

void allJarsSync_keyInput() {
  //YH-app
  text ("-"+appSM.screen()+"-", 0, 20, 270, 50);
    switch(appSM.screen()){
      case "SetPin":
        if(overFour)
        appSM.execute("4");
        if(overSix)
        {
        appSM.execute("6");
        }
        if(kx==2 && ky==4)
        {
          sixPinEnabled = true;
          fourPinEnabled = false;
          zeroPinEnabled = false;
        }
        if(kx==1 && ky==4)
        {
          sixPinEnabled = false;
          fourPinEnabled = true;
          zeroPinEnabled = false;
        }
        if(kx==3 && ky==4)
        {
          sixPinEnabled = false;
          fourPinEnabled = false;
          zeroPinEnabled = true;
        }
          
        if(overZero)
        
        break;
      case "MyCardsPay":
         if (overMyCardsPayCircle()) 
         {
          kx = 3;  // set to some random keys
          ky = 3;  
         }
         //YH-app
         appSM.touch(kx,ky);
         
         break;
      //case "Settings":
      //  if ((kx == 1 || kx == 2) && (ky == 1 || ky == 2)) {
      //          kx = 2;  // set to some random keys
      //          ky = 1;  
      //        }
      //   appSM.touch(kx,ky); 
      //  break;
      case "MyCards":
      if ((kx == 2 && ky == 4) || (kx == 3 && ky == 3)) 
          {
            kx = 1;  // set to some random keys
            ky = 4; 
          }
        if ( mouseX >= 60 && mouseX <= 260 &&
        mouseY >= 295 && mouseY <= 370) 
        { 
          kx = 2;
          ky = 4;
        }
        
        if (overMyCardsPayCircle()){
          kx = 3;
          ky = 3;
          enablePay = true;
        }   
        appSM.touch(kx,ky);
        
        break;
      case "PinScreen":
      //YH
        appSM.touch(kx,ky);
        break;
        case "AddCard":
      //YH
        if(ky == 2 && (kx ==1 || kx ==2 || kx ==3))
        {
          cardfocus = true;
          cardIdFocus = false; 
        }
         if(ky == 3 && (kx ==1 || kx ==2))
         {
           cardfocus = false;
          cardIdFocus = true;
         }
      default: 
        appSM.touch(kx,ky);
    }
}

void allJarsSync_menu(char mTrigger) {
  //text (mTrigger+"1", 0, 30, 270, 50);  // show the current screen of app
  //text ("-"+kx+"-"+ky+"-", 0, 20, 270, 50);
  appSM.execute(String.valueOf(mTrigger)) ;
}


void allJarsSync_next() {
    switch(appSM.screen()){
      case "SetPin":
      case "PinScreen":
        appSM.next();
        break;
      default: 
        appSM.next();
    }
}


void allJarsSync_prev() {
    switch(appSM.screen()){
      case "SetPin":
      case "PinScreen":
        appSM.prev();
        break;
      default: 
        appSM.prev();
    }

}

void loadDefaultFont()
{
 font = loadFont("SansSerif.plain-20.vlw");
 textFont(font);
}

void invokePay()
{
 appSM.touch(2,2); 
}

void pinOptions()
{
   fill(color(53,53,53));
                 textAlign(CENTER, CENTER);
                  rect(0, 220, 320, 60, 0, 0,0,0);
                  text("", 0 + (106 / 2), 220 + (30 / 2));
                  fill(fourPinBackground);
                  rect(0, 222, 106, 55, 10, 10,10,10);
                  //rect(107, 20, 106, 30, 0, 0,0,0);
                  fill(sixPinBackground);
                  rect(107, 222, 106, 55, 10, 10,10,10);
                  //rect(214, 20, 106, 30, 0, 0,0,0);
                  fill(zeroPinBackground);
                  rect(214, 222, 106, 55, 10, 10,10,10);
                  
                  fill(255);
                  textSize(18);
                  text("4 PIN", 0 + (106 / 2), 222 + (55 / 2));
                  text("6 PIN", 107 + (106 / 2), 222 + (55 / 2));
                  text("0 PIN", 214 + (106 / 2), 222 + (55 / 2));
                  textAlign(CENTER, CENTER);
}

void sixPins()
{
                fill(color(78,78,78));
                noStroke();
                rect(0, 90, 296, 55, 0, 0,0,0); 
                fill(color(255));
                rect(11, 90, 40, 40, 0, 0,0,0); 
                rect(62, 90, 40, 40, 0, 0,0,0); 
                rect(113, 90, 40, 40, 0, 0,0,0);
                rect(164, 90, 40, 40, 0, 0,0,0); 
                rect(215, 90, 40, 40, 0, 0,0,0); 
                rect(266, 90, 40, 40, 0, 0,0,0); 
                stroke(3);
                 
}


void update() {
  if ( overRect(0, 222, 106, 55) ) {
    overFour = true;
    overSix = false;
    overZero = false;
    fourPinBackground = color(85,85,85);
  } 
  else {
    overFour = false;
    fourPinBackground = pinBackground;
  }
  
  if ( overRect(107, 222, 106, 55) ) {
    overSix = true;
    overZero = false;
    overFour = false;
    sixPinBackground = color(85,85,85);
    
  } 
  else {
    overSix = false;
    sixPinBackground = pinBackground;
  }
  
  if ( overRect(214, 222, 106, 55) ) {
    overZero = true;
    overFour = false;
    overSix = false;
    zeroPinBackground = color(85,85,85);
  } 
  else {
    overZero = false;
    zeroPinBackground = pinBackground;
  }
}

boolean overRect(int x, int y, int width, int height)  {
  if (mouseX >= x && mouseX <= x+width && 
      mouseY >= y && mouseY <= y+height) {
    return true;
  } else {
    return false;
  }
}
