void playing(){
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

void loadNewPeople(){
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

void deletePeople(){
 for(int i = 1; i < people.size(); i++){
   People p = people.get(i);
   p.die();
 }
}

void peopleAct(){
  for(int i = 0; i < people.size(); i++){
    People p = people.get(i);
    p.act();
    if(!p.getAlive()){
      people.remove(i);
      i--;
    }
  }
}

void peopleShow(){
  for(People p: people){
    p.show();
  }
}

void tilesAct(){
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

void tilesShow(){
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
