PImage img;  // Declare a variable of type PImage

int w=450;
int h=908;
void setup() {
  size(450,908);
  // Make a new instance of a PImage by loading an image file
  img = loadImage("pin_screen.jpg");
}

void draw() {
  background(0);
  // Draw the image to the screen at coordinate (0,0)
  //image(img,0,0);
  img.resize(w,h);
  background(img);
     stroke(255);
     //line(150, 25, 270, 350);
}

//size(400, 400);
//     background(192, 64, 0);
//     stroke(255);
//     line(150, 25, 270, 350);
