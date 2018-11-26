import starbucks.*;
import java.awt.Point;
import java.util.Arrays;


boolean debug=true;
String[] screens = { "PinScreen", "MyCards", "AddCard", "MyCardsOptions","MyCardsMoreOptions", "MyCardsPay", "Rewards","Settings", "Store","Payments"};
PImage[] screenImages = new PImage[screens.length];

int kx,ky;
int headerHeight=60;


String pin = "1234";
String pinInput = "";
int w=320;
int h=headerHeight+55*8;//before:480; now: 500
IApp app = new AppAuthProxy() ;
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
            case "PinScreen":
                PinEntryMachine pm;
                pm=app.getPinEntryMachin();
                pinInput=pm.d1()+pm.d2()+pm.d3()+pm.d4();            
                fill(0, 102, 153, 204);
                for (int i =0; i < min(pinInput.length(),4); i++) {
                  text (pinInput.substring(i,i+1), 40+i*70, 90, 50, 50);
                }
              
              lines = app.screenContents().split("\n");
              if(lines[3].trim().equals("Invalid Pin")){
                      textSize(18);
                      textAlign(CENTER);
                      fill(255, 255, 255, 255);
                      text ("Invalid Pin", 20, 160, 270, 50);
              }
              break;
            case "MyCards":  
              lines = app.screenContents().split("\n");
              String balance=lines[7];
                      textAlign(CENTER);
                      fill(255, 255, 255, 255);
                      //text (balance, 20, 160, 270, 50);
                      text (balance, 60, 295, 200, 40);
              
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
     if (debug) {
       fill(0, 102, 153);
      textSize(25);
      textAlign(LEFT);
      fill(255, 255, 255, 255);
      //text (mouseX + " : " + mouseY, 0, 20, 270, 50);
      text ("-"+kx+"-"+ky+"-", 0, 20, 270, 50);
      
    }

}
void draw_myCardsPay() {
 

  textSize(32);
  String screen=app.screen();
  int sIndex=Arrays.asList(screens).indexOf(screen);
  background(screenImages[sIndex]);
  //System.out.print(screen);
  switch(screen) 
        { 
            case "PinScreen":
                PinEntryMachine pm;
                pm=app.getPinEntryMachin();
                pinInput=pm.d1()+pm.d2()+pm.d3()+pm.d4();            
                fill(0, 102, 153, 204);
                for (int i =0; i < min(pinInput.length(),4); i++) {
                  text (pinInput.substring(i,i+1), 40+i*70, 90, 50, 50);
                }
              
              lines = app.screenContents().split("\n");
              if(lines[3].trim().equals("Invalid Pin")){
                      textSize(18);
                      textAlign(CENTER);
                      fill(255, 255, 255, 255);
                      text ("Invalid Pin", 20, 160, 270, 50);
              }
              break;
            case "MyCards":  
              lines = app.screenContents().split("\n");
              String balance=lines[7];
                      textAlign(CENTER);
                      fill(255, 255, 255, 255);
                      //text (balance, 20, 160, 270, 50);
                      text (balance, 60, 295, 200, 40);
              
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
     if (debug) {
       fill(0, 102, 153);
      textSize(25);
      textAlign(LEFT);
      fill(255, 255, 255, 255);
      //text (mouseX + " : " + mouseY, 0, 20, 270, 50);
      text ("-"+kx+"-"+ky+"-", 0, 20, 270, 50);
      
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
    app.touch(kx,ky);
  }
  

  Point menuSize=new Point(w/5,55);
  boolean hasMenu=(!app.screen().equals("PinScreen") && !app.screen().equals("AddCard"));
  if(hasMenu && ky==8) {
       char mA='A'; //<>//
       int mIndex=((int)(mouseX - kpLeftTop.x))/((int)menuSize.x);
       char mTrigger=(char)((int)mA+mIndex);
       app.execute(String.valueOf(mTrigger)) ;
  }
  
  if(app.screen().equals("AddCard")) {
    if(kx==3 && ky==1) app.next();
    if(kx==1 && ky==1) app.prev();
  }
  
  if(app.screen().equals("MyCards")) {
    if(kx==3 && ky==1) app.next();
  }

}
