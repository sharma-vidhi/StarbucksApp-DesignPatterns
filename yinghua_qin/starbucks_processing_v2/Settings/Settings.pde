import controlP5.*;

ControlP5 cp5;

PImage settings;  // Declare a variable of type PImage
PImage addCard;  // Declare a variable of type PImage
PImage img;

int rectX, rectY, rectWidth, rectHeight;
int w=320;
int h=480;

boolean changeScreen = false;
boolean rectOver = false;

PFont font;
color buttontHighlight;

void setup() {
  
  // Make a new instance of a PImage by loading an image file
  settings = loadImage("Settings.PNG");
  addCard = loadImage("AddCard.png");
  img = loadImage("Arrow.PNG");
  size(320,480);
  
  rectX = 10;
  rectY = 86;
  rectWidth = 300;
  rectHeight = 46;
  buttontHighlight = color(180);
  settings.resize(w,h);
  addCard.resize(w,h);

  background(settings);
  
  font = loadFont("Calibri-Bold-18.vlw");

  frameRate(12);
}


void draw() {
   update(mouseX, mouseY);
   println(rectOver);
   if (rectOver) {
    fill(buttontHighlight);
  } else {
    fill(color(#D6D6D6));
  }
  //stroke(141);
  rect(rectX, rectY, rectWidth, rectHeight, 10, 10,0,0);
  textAlign(CENTER, CENTER);
  fill(0);
  textFont(font, 18);
  //image(img,rectX+rectWidth-img.width-6,rectY+4);
  //textSize(13);
  text("Add Starbucks Cards", rectX + (rectWidth / 3.35), rectY + (rectHeight / 2));


  if (changeScreen) {
    background(addCard);
  }

}

void update(int x, int y) {
  if ( overRect(rectX, rectY, rectWidth, rectHeight) ) {
    rectOver = true;
  } 
  else {
    rectOver = false;
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

void mousePressed() {
  if (rectOver) {
    changeScreen = true;
  }
 
}
