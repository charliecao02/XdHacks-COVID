class basicPerson extends People {
  float initPosX;
  float initPosY;
  basicPerson(float x, float y) {
    super(x, y);
    setContraction(15);
    setDim(20);
    setRadius(gridSize*3);
    //setMortality(1);
    initPosX = x;
    initPosY = y;
  }
  
  void act() {
    super.act();
  }
  
  void show() {
    if(isInfected()) fill(255,0,0);
    else fill(255);
    rect(getX(),getY(),getDim(),getDim());
  }
}
