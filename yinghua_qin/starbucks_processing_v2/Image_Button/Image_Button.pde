ImageButtons button;



void setup()

{

  size(200, 200);

  background(102, 102, 102);

  

  // Define and create image button

  PImage b = loadImage("One.jpg");

  PImage r = loadImage("OneRoll.png");

  PImage d = loadImage("OneDown.png");

  int x = width/2 - b.width/2;

  int y = height/2 - b.height/2; 

  int w = b.width;

  int h = b.height;

  button = new ImageButtons(x, y, w, h, b, r, d);

}



void draw()

{

  button.update();

  button.display();

}
