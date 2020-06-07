class Player extends People {
  int currentScreen = 2;
  Player(float x, float y) {
    super(x, y);
    setContraction(1);
    setDim(20);
    heal();
    //mortality = 1;
  }
  
  void act() {
    
    super.act();
    
    if(akey) setVx(getVx()-0.1);
    else if(dkey) setVx(getVx()+0.1);
    else setVx(getVx()*0.05);
    
    if(wkey) setVy(getVy()-0.1);
    else if(skey) setVy(getVy()+0.1);
    else setVy(getVy()*0.05);
    
    if(getVx() > 1.5) setVx(1.5);
    if(getVx() < -1.5) setVx(-1.5);
    if(getVy() > 1.5) setVy(1.5);
    if(getVy() < -1.5) setVy(-1.5);
    
    processSwitching();
  }
  
  void show() {
    if(isInfected()) fill(255,0,0);
    else fill(255);
    rect(getX(),getY(),getDim(),getDim());
  }
  
  public void processSwitching(){
   if(currentScreen == 2){
     if(getY() <= -getDim()/2){
       setCurrentScreen(0);
       setY(gridSize*9);
       deletePeople();
       loadNewPeople();
     } else if(getX() <= - getDim()/2) {
       setCurrentScreen(1);
       setX(gridSize*9);
       deletePeople();
       loadNewPeople();
     } else if (getX() >=gridSize*10-(getDim()/2)) {
      setCurrentScreen(3); 
      setX(gridSize);
      deletePeople();
       loadNewPeople();
     } else if(getY() >= gridSize*10 - (getDim()/2)){
      setCurrentScreen(4); 
      setY(gridSize);
      deletePeople();
       loadNewPeople();
     }
   }else if(currentScreen == 0){
     if(getY() >= gridSize*10 - (getDim()/2)){
      setCurrentScreen(2); 
      setY(gridSize);
      deletePeople();
       loadNewPeople();
     } else if(getY() <= -getDim()/2){
       setY(-getDim()/2);
     } else if(getX() <= - getDim()/2) {
       setX(-getDim()/2);
     } else if (getX() >=gridSize*10-(getDim()/2)) {
       setX(gridSize*10-(getDim()/2));
     } 
   } else if(currentScreen == 1){
     if(getY() >= gridSize*10 - (getDim()/2)){
      setY(gridSize*10 - (getDim()/2));
     } else if(getY() <= -getDim()/2){
       setY(-getDim()/2);
     } else if (getX() >=gridSize*10-(getDim()/2)) {
      setCurrentScreen(2); 
      setX(gridSize);
      deletePeople();
       loadNewPeople();
     }else if(getX() <= - getDim()/2) {
       setX(-getDim()/2);
     }
   } else if(currentScreen == 3){
     if(getY() >= gridSize*10 - (getDim()/2)){
      setY(gridSize*10 - (getDim()/2));
     } else if(getY() <= -getDim()/2){
       setY(-getDim()/2);
     } else if (getX() >=gridSize*10-(getDim()/2)) {
      setX(gridSize*10-(getDim()/2));
     } else if(getX() <= - getDim()/2) {
       setCurrentScreen(2);
       setX(gridSize*9);
       deletePeople();
       loadNewPeople();
     }
   } else if(currentScreen == 4){
     if(getY() >= gridSize*10 - (getDim()/2)){
      setY(gridSize*10 - (getDim()/2));
     } else if(getY() <= -getDim()/2){
       setCurrentScreen(2);
       setY(gridSize*9);
       deletePeople();
       loadNewPeople();
     }else if(getX() <= - getDim()/2) {
       setX(-getDim()/2);
     } else if (getX() >=gridSize*10-(getDim()/2)) {
       setX(gridSize*10-(getDim()/2));
     } 
   }
  }
  
  public int getCurrentScreen(){
   return currentScreen; 
  }
  
  public void setCurrentScreen(int cScreen){
   this.currentScreen = cScreen ;
  }
}
