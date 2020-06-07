class People extends GameObject {
  private int dim, radius;
  private float vx, vy, contraction, mortality;
  private PImage image;
  private boolean hasCorona, isAlive, male;

  People(float x, float y) {
    setPos(x,y);
    this.isAlive = true;
    this.dim = 20;
    this.contraction = 0;
    this.mortality = 0;
    this.hasCorona = false;
    this.male = false;
    this.radius = gridSize*3;
    this.vx = 0;
    this.vy = 0;
  }
  
  void act() {
    
    setX(getX() + getVx());
    setY(getY() + getVy());
    
    for (People p : people) {
      if (this.isInRadius(p)) {
        //if()
       processCollision(p);
      }
    }
    
  }
  
  void show() {
    if(hasCorona) fill(255,0,0);
    else fill(255);
    rect(getX(),getY(),getDim(),getDim());
  }
  
  public int getDim() {
    return this.dim;
  }
  public void setDim(int dim){
    this.dim = dim;
  }
  
  public void setRadius(int r){
     this.radius = r;
  }
  
  public float getContraction(){
   return this.contraction; 
  }
  
  public void setContraction(float contraction){
   this.contraction = contraction; 
  }
  public boolean getAlive(){
   return this.isAlive; 
  }
  
  public float getVx() {
    return this.vx;
  }
  public float getVy() {
    return this.vy;
  }
  void setVy(float vy) {
    this.vy = vy;
  }
  void setVx(float vx) {
    this.vx = vx;
  }
  void die() {
    this.isAlive = false;
  }
  void heal() {
    this.hasCorona = false;
  }
  void getInfected() {
    if(!hasCorona){
      this.hasCorona = true;
      infectedNum++;
  }
  }
  
  public boolean isInfected(){
   return hasCorona;
  }
  void washHands() {
    this.contraction/=2;
  }
  
  public boolean isInRadius(People p){
     if(p.getX() == getX() && p.getY() == getY()) return false;
     
     float thisCenterX = getX() + getDim() /2;
     float thisCenterY = getY() + getDim() /2;
     
     float pCenterX = p.getX() + p.getDim() /2;
     float pCenterY = p.getY() + p.getDim() /2;
     
     if(dist(thisCenterX, thisCenterY, pCenterX, pCenterY) < this.radius) return true;
     return false;
  }

  public boolean isColliding(Tile t) {
    float tileLeft = t.getX();
    float tileRight = t.getX() + gridSize;
    float tileTop = t.getY();
    float tileBottom = t.getY() + gridSize;

    float pLeft = getX();
    float pRight = getX() + getDim();
    float pTop = getY();
    float pBottom = getY() + getDim();

    return pLeft <= tileRight && pRight >= tileLeft && 
      pTop <= tileBottom && pBottom >= tileTop;
  }
  public boolean isColliding(People p) {
     if( p.getX() == getX() && p.getY() == getY()) return false;
    
    float thisLeft = getX();
    float thisRight = getX() + getDim();
    float thisTop = getY();
    float thisBottom = getY() + getDim();

    float pLeft = p.getX();
    float pRight = p.getX() + p.getDim();
    float pTop = p.getY();
    float pBottom = p.getY() + p.getDim();

    return pLeft <= thisRight && pRight >= thisLeft && 
      pTop <= thisBottom && pBottom >= thisTop;
  }
  
  void processCollision(People p) {
    if (p.hasCorona) {
      if (coronaChance(this.contraction)) this.getInfected();
    }
  }
  void processColllision(Tile t) {
   //if (t instanceof hospital && this.hasCorona) {
   //  if (deathChance(this.mortality)) {
   //    this.die();
   //  } else {
   //   this.heal(); 
   //  }
   //}
  }
  boolean coronaChance(float c) {
    if (c > random(0, 100)) return true;
    return false;
  }
  boolean deathChance(float c) {
    if (c > random(0, 100)) return true;
    return false;
  }
}
