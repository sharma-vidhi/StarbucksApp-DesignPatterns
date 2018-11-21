

import controlP5.*;

ControlP5 cp5;

PImage pinScreen;  // Declare a variable of type PImage
PImage myCards;  // Declare a variable of type PImage

String pin = "1234";
String pinInput = "";

int w=320;
int h=480;
boolean changeScreen = false;
void setup() {
  size(320,480);
  // Make a new instance of a PImage by loading an image file
  pinScreen = loadImage("pinScreen.png");
  myCards = loadImage("myCard.png");

}

void draw() {
  pinScreen.resize(w,h);
  myCards.resize(w,h);

  background(pinScreen);
  
  textSize(32);
  textAlign(LEFT);
  fill(0, 102, 153, 204);

  frameRate(12);
//  println(mouseX + " : " + mouseY);
  
  
  for (int i =0; i < min(pinInput.length(),4); i++) {
    text (pinInput.substring(i,i+1), 40+i*70, 90, 50, 50);
  }
  
 if (pinInput.length() == 4) {
      if (verifyPin()) {
        changeScreen = true; 
      } else {
        textSize(18);
        textAlign(CENTER);
        fill(255, 255, 255, 255);
        text ("Invalid Pin", 20, 160, 270, 50);
//        pinInput = "";
//        background(pinScreen);
      }
 }

  if (changeScreen) {
    background(myCards);
  }

}

void mousePressed() {
  int x = 0;
  int y = 260;
  int button_x = 106;
  int button_y = 55;
  
  int key = 0; 
  
  key = (3*((mouseY - y) / button_y)) + (mouseX - x) / button_x + 1 ;

 if (key == 11) { key = 0; }
 
 if (key == 12  && pinInput.length() > 0) { pinInput = pinInput.substring(0,pinInput.length() - 1); } //remove a key from pinInput
 else if (key < 10 && pinInput.length() <4 ) { pinInput = pinInput + key; } // add the key to pinInput 
 
}

boolean verifyPin() {
  if (pinInput.equals(pin)) {
    return true; 
  }
  return false;
}
