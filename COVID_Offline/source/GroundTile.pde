class GroundTile extends Tile{
  
  GroundTile(int x, int y, String biome){
    super(x,y,biome);
  }
  
  void act(){
  }
  
  void show(){
    drawImage();
  }
  
  void drawImage(){
    if(getBiome() == "Park" || getBiome() == "Middle" || getBiome() == "Airport" || getBiome() == "City")image(grassPic,getX(),getY(),gridSize,gridSize);
    if(getBiome() == "Beach") image(sandPic,getX(),getY(),gridSize,gridSize);
  }
  
}
