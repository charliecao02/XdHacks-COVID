PImage[] images = new PImage[20];
int i = 0;
float colour1=30, colour2=110, colour3=200;
int c1=1, c2=2, c3=3;


void intro() {
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

void mouseClicked() {
  if (mouseX >= 220 && mouseX <= 420 && mouseY >= 550 && mouseY <= 610 && gameType == intro) {
    gameType = playing;
  }
}
