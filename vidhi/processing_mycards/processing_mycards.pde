PImage MyCardMoreOptions;
PImage MyCardOptions;
PImage background;

int value =0;
void setup() {
  size(320,480);
  MyCardOptions = loadImage("MyCardsOptions.jpg");
  MyCardMoreOptions = loadImage("MyCardsMoreOptions.jpg");
  background = MyCardOptions;
}

void draw() {
  image(MyCardOptions, 0, 0);
  image(MyCardMoreOptions, 0, 0);
  
}

void mousePressed() {
 
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
