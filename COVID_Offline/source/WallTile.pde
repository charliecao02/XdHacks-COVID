class WallTile extends Tile{
  WallTile(int x, int y, String biome){
    super(x,y,biome);
  }
  
  void show(){
    drawImage();
  }
  
  void act(){
    
    for(People p: people){
      if(p.isColliding(this)){
    processCollision(p);
      }
    }
    
  }
  
  void drawImage(){
    if(getBiome() == "Park" || getBiome() == "Middle" || getBiome() == "Airport" || getBiome() == "City" || getBiome() == "Beach") image(treePic,getX(),getY(),gridSize,gridSize);
  }
  
  void processCollision(People p){
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
