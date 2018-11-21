package sketch.processing;
import processing.core.*;

public class Ball {
	
	private PApplet sketch;
	
	private float x;
	private float y;
	private float xSpeed;
	private float ySpeed;
	private float size;
	
	public Ball(PApplet sketch, float x, float y) {
		this.sketch = sketch;
		this.x = x;
		this.y = y;
		
		this.size = sketch.random(10,100);
		this.xSpeed = sketch.random(-10,10);
		this.ySpeed = sketch.random(-10,10);

	}
	
	public void step() {
		x += xSpeed;
		if(x < 0 || x > sketch.width){
			xSpeed *= -1;
		}
		
		y += ySpeed;
		if(y < 0 || y > sketch.height){
			ySpeed *= -1;
		}
	}
		
	public void render(){
		sketch.ellipse(x, y, size, size);
	}

}
