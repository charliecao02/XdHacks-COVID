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
