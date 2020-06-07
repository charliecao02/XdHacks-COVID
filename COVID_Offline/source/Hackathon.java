import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Hackathon extends PApplet {

final int gridSize = 32;
final int scaleSize = 640/gridSize/10;
final int screenNum = 5;

final int intro = 0;
final int playing = 1;
final int gameover = 2;

int gameType = intro;

 PFont font;

final int RED = 0xffFF0000;
final int GREEN = 0xff00FF00;
final int BLUE = 0xff0000FF;
final int BLACK = 0xff000000;
final int WHITE = 0xffFFFFFF;
final int YELLOW = 0xffFFFF00;
final int CYAN = 0xff00FFFF;
final int PURPLE = 0xffFF00FF;

PImage grassPic;
PImage waterPic;
PImage treePic;
PImage sandPic;

Player player = new Player(gridSize*5,gridSize*5);

final int population = 50;
int infectedNum = 1;

ArrayList <People> people = new ArrayList <People> ();
ArrayList <ArrayList <Tile>> tiles = new ArrayList <ArrayList <Tile>> ();

boolean akey, dkey, skey, wkey, enterkey;

public void setup(){
 
 
 surface.setTitle("COVID Offline");
 
 people.add(player);
 //people.add(new People(gridSize*8+3,gridSize*9+3));
 
 for (int i = 0; i < 20; i++){
      images[i] = loadImage("BigImg/frame_" + i + ".png");
      images[i].resize(160, 160);
  }

  font = createFont("lasercorps.ttf", 32);

grassPic = loadImage("grass.png");
waterPic = loadImage("water.png");
treePic = loadImage("tree.png");
sandPic = loadImage("sand.png");
 for(int i = 0; i < screenNum; i++){
   PImage img = loadImage("map" + i + ".png");
   ArrayList <Tile> t = new ArrayList <Tile> ();
   for(int row = 0; row < img.width; row++){
     for(int col = 0; col < img.height; col++){
       int c = img.get(col, row);
       String biome = "Park";
       if(i == 0) biome = "Park";
       else if(i == 1) biome = "Beach";
       else if(i == 2) biome = "Middle";
       else if(i == 3) biome = "City";
       else if(i == 4) biome = "Airport";
       
       if(c == GREEN){
         t.add(new GroundTile(col,row,biome));
       }
       if(c == RED){
        t.add(new WallTile(col,row,biome)); 
       }
       if(c == BLUE){
        t.add(new WaterTile(col,row,biome)); 
       }
     }
   }
   tiles.add(t);
 }
 
}

public void draw(){
  background(200);
   scale(scaleSize);
   if(gameType == intro){
     intro();
   } else if(gameType == playing){
     playing();
   } else if(gameType == gameover){
     gameover();
   }
  //for(int i = 0; i < 640; i+= 32){
  // rect(i,0,gridSize,gridSize); 
  //}
}
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
public void gameover(){
  fill (255,0,0);
  text("EVERYONE INFECTED",gridSize *2,gridSize * 5);
}
class GroundTile extends Tile{
  
  GroundTile(int x, int y, String biome){
    super(x,y,biome);
  }
  
  public void act(){
  }
  
  public void show(){
    drawImage();
  }
  
  public void drawImage(){
    if(getBiome() == "Park" || getBiome() == "Middle" || getBiome() == "Airport" || getBiome() == "City")image(grassPic,getX(),getY(),gridSize,gridSize);
    if(getBiome() == "Beach") image(sandPic,getX(),getY(),gridSize,gridSize);
  }
  
}
PImage[] images = new PImage[20];
int i = 0;
float colour1=30, colour2=110, colour3=200;
int c1=1, c2=2, c3=3;


public void intro() {
  background(colour1, colour2, colour3);
  colour1=colour1+c1;
  if (colour1>=255) {
    colour1=254;
    c1=-c1;
  }
  if (colour1<=0) {
    colour1=1;
    c1=-c1;
  }
  colour2=colour2+c2;
  if (colour2>=255) {
    colour2=254;
    c2=-c2;
  }
  if (colour2<=0) {
    colour2=1;
    c2=-c2;
  }
  colour3=colour3+c3;
  if (colour3>=255) {
    colour3=254;
    c3=-c3;
  }
  if (colour3<=0) {
    colour3=1;
    c3=-c3;
  }
  image(images[i/5], 80, 120);
  i++;
  if (i >= 100) {
    i = 0;
  }

  textFont(font);
  textSize(50);
  textAlign(CENTER);
  rectMode(CENTER);

  fill(255-colour1, 255-colour2, 255-colour3);
  text("COVID", 160, 60);
  text("OFFLINE", 160, 120);

  fill(255);
  rect(160, 290, 100, 30);

  fill(0);
  textSize(20);
  text("START", 160, 295);
  
  textAlign(LEFT);
  rectMode(CORNER);

}

public void mouseClicked() {
  if (mouseX >= 220 && mouseX <= 420 && mouseY >= 550 && mouseY <= 610 && gameType == intro) {
    gameType = playing;
  }
}
public void keyPressed() {
  if (key == 'a') akey = true;
  if (key == 'd') dkey = true;
  if (key == 's') skey = true;
  if (key == 'w') wkey = true;
  if (keyCode == ENTER) enterkey = true;
}

public void keyReleased() {
  if (key == 'a') akey= false;
  if (key == 'd') dkey = false;
  if (key == 's') skey = false;
  if (key == 'w') wkey = false;
  if (keyCode == ENTER) enterkey = true;
}
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
  
  public void act() {
    
    setX(getX() + getVx());
    setY(getY() + getVy());
    
    for (People p : people) {
      if (this.isInRadius(p)) {
        //if()
       processCollision(p);
      }
    }
    
  }
  
  public void show() {
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
  public void setVy(float vy) {
    this.vy = vy;
  }
  public void setVx(float vx) {
    this.vx = vx;
  }
  public void die() {
    this.isAlive = false;
  }
  public void heal() {
    this.hasCorona = false;
  }
  public void getInfected() {
    if(!hasCorona){
      this.hasCorona = true;
      infectedNum++;
  }
  }
  
  public boolean isInfected(){
   return hasCorona;
  }
  public void washHands() {
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
  
  public void processCollision(People p) {
    if (p.hasCorona) {
      if (coronaChance(this.contraction)) this.getInfected();
    }
  }
  public void processColllision(Tile t) {
   //if (t instanceof hospital && this.hasCorona) {
   //  if (deathChance(this.mortality)) {
   //    this.die();
   //  } else {
   //   this.heal(); 
   //  }
   //}
  }
  public boolean coronaChance(float c) {
    if (c > random(0, 100)) return true;
    return false;
  }
  public boolean deathChance(float c) {
    if (c > random(0, 100)) return true;
    return false;
  }
}
class Player extends People {
  int currentScreen = 2;
  Player(float x, float y) {
    super(x, y);
    setContraction(1);
    setDim(20);
    heal();
    //mortality = 1;
  }
  
  public void act() {
    
    super.act();
    
    if(akey) setVx(getVx()-0.1f);
    else if(dkey) setVx(getVx()+0.1f);
    else setVx(getVx()*0.05f);
    
    if(wkey) setVy(getVy()-0.1f);
    else if(skey) setVy(getVy()+0.1f);
    else setVy(getVy()*0.05f);
    
    if(getVx() > 1.5f) setVx(1.5f);
    if(getVx() < -1.5f) setVx(-1.5f);
    if(getVy() > 1.5f) setVy(1.5f);
    if(getVy() < -1.5f) setVy(-1.5f);
    
    processSwitching();
  }
  
  public void show() {
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
public void playing(){
  //player.act();
  peopleAct();
  tilesAct();
  tilesShow();
  peopleShow();
  //player.show();
  println(infectedNum);
  fill(50);
  text(infectedNum+"/"+ population,3,gridSize);
  if(infectedNum >= population) gameType = gameover;
}

public void loadNewPeople(){
  for(int row = gridSize; row < gridSize*10; row+= gridSize){
    for(int col = gridSize; col < gridSize*10; col+= gridSize){
      if(random(0,100) < 2){
        basicPerson bp = new basicPerson(col, row);
        float ratio = infectedNum/(float)population;
        if(random(0,100) < 100*ratio*2) bp.getInfected();
        people.add(bp);
      }
    }
  }
}

public void deletePeople(){
 for(int i = 1; i < people.size(); i++){
   People p = people.get(i);
   p.die();
 }
}

public void peopleAct(){
  for(int i = 0; i < people.size(); i++){
    People p = people.get(i);
    p.act();
    if(!p.getAlive()){
      people.remove(i);
      i--;
    }
  }
}

public void peopleShow(){
  for(People p: people){
    p.show();
  }
}

public void tilesAct(){
    ArrayList <Tile> screent = tiles.get(player.getCurrentScreen());
    for(int j = 0; j < screent.size(); j++){
      Tile t = screent.get(j);
    if(t instanceof GroundTile){
      GroundTile gt = (GroundTile) t;
    gt.act();
    }
    if(t instanceof WallTile){
      WallTile wt = (WallTile) t;
      wt.act();
    }
    if(t instanceof WaterTile){
      WaterTile wt = (WaterTile) t;
      wt.act();
    }
  }
}

public void tilesShow(){
    ArrayList <Tile> screent = tiles.get(player.getCurrentScreen());
    for(int j = 0; j < screent.size(); j++){
      Tile t = screent.get(j);
    if(t instanceof GroundTile){
      GroundTile gt = (GroundTile) t;
    gt.show();
    }
    if(t instanceof WallTile){
      WallTile wt = (WallTile) t;
      wt.show();
    }
    if(t instanceof WaterTile){
      WaterTile wt = (WaterTile) t;
      wt.show();
    }
  }
}
class Tile extends GameObject{
  
  private PImage image;
  private String biome;
  
  Tile(int x, int y, String biome){
    this.setX(x * gridSize);
    this.setY(y * gridSize);
    this.biome = biome;
  }
  
  public String getBiome(){
    return biome;
  }
  
}
class WallTile extends Tile{
  WallTile(int x, int y, String biome){
    super(x,y,biome);
  }
  
  public void show(){
    drawImage();
  }
  
  public void act(){
    
    for(People p: people){
      if(p.isColliding(this)){
    processCollision(p);
      }
    }
    
  }
  
  public void drawImage(){
    if(getBiome() == "Park" || getBiome() == "Middle" || getBiome() == "Airport" || getBiome() == "City" || getBiome() == "Beach") image(treePic,getX(),getY(),gridSize,gridSize);
  }
  
  public void processCollision(People p){
    if(p.getVx() > 0){
      if(p.getX() + p.getDim() - p.getVx() <= getX() +gridSize  && p.getX() + p.getDim() > getX() && p.getY() + p.getDim() - p.getVy() >getY() && p.getY() - p.getVy() < getY() + gridSize){
     p.setX(this.getX()-p.getDim());
     p.setVx(0);
      }
    } else if(p.getVx() < 0){
      if(p.getX() - p.getVx() >= getX() && p.getX() < getX() + gridSize && p.getY() + p.getDim() - p.getVy() >getY() && p.getY() - p.getVy()< getY() + gridSize){
     p.setX(this.getX()+gridSize);
     p.setVx(0);
      }
    }
    
    if(p.getVy() > 0){
      if(p.getY() + p.getDim() - p.getVy() <= getY() +gridSize  && p.getY() + p.getDim() > getY() && p.getX() + p.getDim() - p.getVx() >getX() && p.getX() - p.getVx()< getX() + gridSize){
     p.setY(this.getY()-p.getDim());
     p.setVy(0);
      }
    } else if(p.getVy() < 0){
      if(p.getY() - p.getVy() >= getY() && p.getY() < getY() + gridSize && p.getX() + p.getDim() - p.getVx() >getX() && p.getX() - p.getVx()< getX() + gridSize){
     p.setY(this.getY()+gridSize);
     p.setVy(0);
      }
    }
  }
}
class WaterTile extends Tile{
  WaterTile(int x, int y, String biome){
    super(x,y,biome);
  }
  
  public void show(){
    drawImage();
  }
  
  public void act(){
    
    for(People p: people){
      if(p.isColliding(this)){
    processCollision(p);
      }
    }
    
  }
  
  public void drawImage(){
    if(getBiome() == "Park" || getBiome() == "Middle" || getBiome() == "Airport" || getBiome() == "City") image(waterPic,getX(),getY(),gridSize,gridSize);
    else if(getBiome() == "Beach") image(waterPic,getX(),getY(),gridSize,gridSize);
  }
  
  public void processCollision(People p){
    if(p.getVx() > 0){
      if(p.getX() + p.getDim() - p.getVx() <= getX() +gridSize  && p.getX() + p.getDim() > getX() && p.getY() + p.getDim() - p.getVy() >getY() && p.getY() - p.getVy() < getY() + gridSize){
     p.setX(this.getX()-p.getDim());
     p.setVx(0);
      }
    } else if(p.getVx() < 0){
      if(p.getX() - p.getVx() >= getX() && p.getX() < getX() + gridSize && p.getY() + p.getDim() - p.getVy() >getY() && p.getY() - p.getVy()< getY() + gridSize){
     p.setX(this.getX()+gridSize);
     p.setVx(0);
      }
    }
    
    if(p.getVy() > 0){
      if(p.getY() + p.getDim() - p.getVy() <= getY() +gridSize  && p.getY() + p.getDim() > getY() && p.getX() + p.getDim() - p.getVx() >getX() && p.getX() - p.getVx()< getX() + gridSize){
     p.setY(this.getY()-p.getDim());
     p.setVy(0);
      }
    } else if(p.getVy() < 0){
      if(p.getY() - p.getVy() >= getY() && p.getY() < getY() + gridSize && p.getX() + p.getDim() - p.getVx() >getX() && p.getX() - p.getVx()< getX() + gridSize){
     p.setY(this.getY()+gridSize);
     p.setVy(0);
      }
    }
  }
}
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
  
  public void act() {
    super.act();
  }
  
  public void show() {
    if(isInfected()) fill(255,0,0);
    else fill(255);
    rect(getX(),getY(),getDim(),getDim());
  }
}
  public void settings() {  size(640,640);  noSmooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Hackathon" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
