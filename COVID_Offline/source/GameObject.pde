abstract class GameObject{
 
  private float x, y;
  
  GameObject(){
   x = 0;
   y = 0;
  }
  
  public float getX(){
    return x;
  }
  
  public float getY(){
    return y;
  }
  
  public void setPos(float x, float y){
   setX(x);
   setY(y);
  }
  
  public void setX(float x){
    this.x = x;
  }
  
  public void setY(float y){
    this.y = y;
  }
  
}
