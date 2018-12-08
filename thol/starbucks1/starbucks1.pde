import starbucks.*;

import java.util.Arrays;

interface AppStrategy {
  String getImageDirectory();
  int getCoordinateX();
  int getCoordinateY();
}

class Ui5Strategy implements AppStrategy {
  
  private String imageDirectory;
  private int kx, ky;
  private int headerHeight;
  private int leftTopX;
  private int leftTopY;
  private int buttonX = 106;
  private int buttonY = 55;

  
  public Ui5Strategy() {
    this.imageDirectory = "";
    this.headerHeight = 60;
    this.leftTopX = 0;
    this.leftTopY = this.headerHeight;
    this.buttonX = 106;
    this.buttonY = 55;
  }
  
  String getImageDirectory() {
    return this.imageDirectory;
  }
  
  int getCoordinateX() {
    //int kx,ky;  //keypress at (kx,ky) :4x3
    kx=(((int)(mouseX - this.leftTopX))/this.buttonX) + 1;
    return this.kx;
  }
  
  int getCoordinateY() {
     ky=(((int)(mouseY - this.leftTopY))/this.buttonY) + 1;
    return this.ky;
  }
}

class Ui10Strategy implements AppStrategy {
  
  private String imageDirectory;
  private int kx, ky;
  private int headerHeight=108;
  private int leftTopX = 0;
  private int leftTopY = headerHeight;
  private int buttonX = 150;
  private int buttonY = 100;

  public Ui10Strategy() {
    this.imageDirectory = "/Users/thol/Downloads/cmpe202/fa18-202-mystic/thol/starbucks/images/v10_images/";
  }
  public Ui10Strategy(String dirName) {
    this.imageDirectory = dirName;
  }
  
  String getImageDirectory() {
    return this.imageDirectory;
  }
  int getCoordinateX() {
    //int kx,ky;  //keypress at (kx,ky) :4x3
    kx=(((int)(mouseX - this.leftTopX))/this.buttonX) + 1;
    return this.kx;
  }
  
  int getCoordinateY() {
     ky=(((int)(mouseY - this.leftTopY))/this.buttonY) + 1;
    return this.ky;
  }
}

AppStrategy currStrategy;

boolean debug=true;
String[] screens = { "PinScreen", "MyCards", "AddCard", "MyCardsOptions","MyCardsMoreOptions", "MyCardsPay", "Rewards","Settings", "Store","Payments"};
PImage[] screenImages = new PImage[screens.length];

int kx,ky;
int appX, appY;


String pin = "1234";
String pinInput = "";
int headerHeight = 60;
int skch_w = 320;
int skch_h = headerHeight+(55*8);//before:480; now: 500
IApp app = new AppAuthProxy() ;
String[] lines ;

void setup() {

  currStrategy = new Ui5Strategy();
  //currStrategy = new Ui10Strategy();
  String imDir = currStrategy.getImageDirectory();
  
  size(320,500);  //480
  // Make a new instance of a PImage by loading an image file
  for (int i=0; i < screens.length; i++) {
    screenImages[i] = loadImage(imDir + screens[i]+".jpg");
    screenImages[i].resize(skch_w, skch_h);
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
      text (mouseX + " : " + mouseY, 0, 20, 270, 50);
      text ("+"+kx+"-"+ky+"-", 0, 30, 270, 70);
      
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
 
  //int headerHeight = currStrategy.getHeaderHeight();
  int leftTopX = 0;
  int leftTopY = headerHeight;

  //Point buttonSize=new Point(106,55);

  //int kx,ky;  //keypress at (kx,ky):4x3
  //kx=((int)(mouseX - kpLeftTop.x))/((int)buttonSize.x)+1;
  //ky=((int)(mouseY - kpLeftTop.y))/((int)buttonSize.y)+1;
  
  kx = currStrategy.getCoordinateX();
  ky = currStrategy.getCoordinateY();
  
  
  //boolean kpzone= ( ky > 4 && ky<9 && kx<=3 && kx>=1 );
  //boolean tzone= (fullScreen || ky <= 4 );
  
   
  if (app.screen().equals("MyCards")) {
    if ((kx == 2 && ky == 4) || (kx == 3 && ky == 3)) {
      kx = 1;  // set to some random keys
      ky = 4;  
    }
    if ( mouseX >= 60 && mouseX <= 260 &&
      mouseY >= 295 && mouseY <= 370) { 
      kx = 2;
      ky = 4;
    }
    
    if (overMyCardsPayCircle()){
      kx = 3;
      ky = 3;
    }    
  }

  if((kx>=1 && kx<= appX) && (ky>=1 && ky<= appY)) {
    app.touch(kx,ky);
  }
  

  int menuX = skch_w/5;
  int menuY = 55;
  boolean hasMenu=(!app.screen().equals("PinScreen") && !app.screen().equals("AddCard"));
  if(hasMenu && ky==8) {
       char mA='A'; //<>//
       int mIndex=((int)(mouseX - leftTopX))/menuX;
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
