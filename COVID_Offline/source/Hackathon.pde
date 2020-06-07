final int gridSize = 32;
final int scaleSize = 640/gridSize/10;
final int screenNum = 5;

final int intro = 0;
final int playing = 1;
final int gameover = 2;

int gameType = intro;

 PFont font;

final color RED = #FF0000;
final color GREEN = #00FF00;
final color BLUE = #0000FF;
final color BLACK = #000000;
final color WHITE = #FFFFFF;
final color YELLOW = #FFFF00;
final color CYAN = #00FFFF;
final color PURPLE = #FF00FF;

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

void setup(){
 size(640,640);
 
 surface.setTitle("COVID Offline");
 noSmooth();
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
       color c = img.get(col, row);
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

void draw(){
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
