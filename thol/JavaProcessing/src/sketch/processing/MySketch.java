package sketch.processing;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.*;

public class MySketch extends PApplet {
	
	private ArrayList<Ball> balls = new ArrayList<>();
	private PImage pinScreen;
	private PImage myCards;
	private boolean changeScreen;
	
	private int w = 450;
	private int h = 908;
	
	public void settings() {
		size(450,908);
		pinScreen = loadImage("pin_screen.jpg");
		myCards = loadImage("my_cards.png");
	}
	
	public void draw(){
		pinScreen.resize(w,h);
		myCards.resize(w,h);
		
		background(pinScreen);
		textSize(32);
		textAlign(CENTER);
		text("****", 225, 180); 

		frameRate(12);
		println(mouseX + " : " + mouseY);
		noFill();
		rect(90,487,270,224);

		rect(90,487,50,50);  // 1
		rect(197,487,50,50); // 2  
		rect(310,487,50,50); // 3  
		     
		rect(90,545,50,50);  // 4
		rect(197,545,50,50); // 5  
		rect(310,545,50,50); // 6  
		   
		rect(90,603,50,50);  // 7
		rect(197,603,50,50); // 8  
		rect(310,603,50,50); // 9  
		     
		rect(90,661,50,50);  // 
		rect(197,661,50,50); // 0  
		rect(310,661,50,50); // x  

		if (mousePressed == true) {
		  if (mouseButton == LEFT) {
		    changeScreen = true;
		  }
		}

		if (changeScreen) {
		  background(myCards);
		}
		  
	}
	
	public void mouseDragged(){
		balls.add(new Ball(this, mouseX, mouseY));
	}
	

	public static void main(String[] args) {
		
		String[] processingArgs = {"MySketch"};
		MySketch sketch = new MySketch();
		PApplet.runSketch(processingArgs, sketch);

	}

}
