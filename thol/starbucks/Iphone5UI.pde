class Iphone5UI implements IAppUI {
 
  int kx,ky;
  private Point kpRowColumn = new Point(3,8);
  private Point buttonSize = new Point(106,55);
  private boolean isValid = false;
  Point kpLeftTop = new Point(0,headerHeight);  //260:keypad;40:touch
  
  public int getX() {
    return this.kx;
  }
  
  public int getY() {
    return this.ky;
  }
  
  public boolean isValidXY() { return this.isValid; }
  
  private boolean overMyCardsPayCircle() {
    float disX = 270 - mouseX;
    float disY = 225 - mouseY;
    int diameter = 60;
    if (sqrt(sq(disX) + sq(disY)) < diameter/2 ) {
      return true;
    } else {
      return false;
    }
  }
  
  public void setXY(String screenName) {
    //int kx,ky;  //keypress at (kx,ky):4x3
    kx=((int)(mouseX - kpLeftTop.x))/((int)buttonSize.x)+1;
    ky=((int)(mouseY - kpLeftTop.y))/((int)buttonSize.y)+1;
    
    
    //boolean kpzone= ( ky > 4 && ky<9 && kx<=3 && kx>=1 );
    //boolean tzone= (fullScreen || ky <= 4 );
    
     
    if (screenName.equals("MyCards")) {
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
  
    if((kx>=1 && kx<=kpRowColumn.x) && (ky>=1 && ky<=kpRowColumn.y)) {
      isValid = false;
    }
    else {
      isValid = true;
    }
  }
}
