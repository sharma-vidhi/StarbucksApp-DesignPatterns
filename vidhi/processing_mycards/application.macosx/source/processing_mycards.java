import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import qinyinghua.*; 
import starbucks.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class processing_mycards extends PApplet {

PImage MyCardMoreOptions;
PImage MyCardOptions;
PImage background;

int value =0;
public void setup() {
  
  MyCardOptions = loadImage("MyCardsOptions.jpg");
  MyCardMoreOptions = loadImage("MyCardsMoreOptions.jpg");
  background = MyCardOptions;
}

public void draw() {
  image(MyCardOptions, 0, 0);
  image(MyCardMoreOptions, 0, 0);
  
}

public void mousePressed() {
 
  int a= mouseX;
  int b =mouseY;
  println (a,b);
  if (background ==MyCardOptions){
  if ((a>30 && a<289 ) && (b>351&&b<392)){
    background = MyCardMoreOptions;
      }
  else{
    if ((a>8 && a<64  ) && (b>30&&b<52)){
    background = MyCardMoreOptions;
       }
    }
  }
  
}
  public void settings() {  size(320,480); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "processing_mycards" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
