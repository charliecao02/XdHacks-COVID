void keyPressed() {
  if (key == 'a') akey = true;
  if (key == 'd') dkey = true;
  if (key == 's') skey = true;
  if (key == 'w') wkey = true;
  if (keyCode == ENTER) enterkey = true;
}

void keyReleased() {
  if (key == 'a') akey= false;
  if (key == 'd') dkey = false;
  if (key == 's') skey = false;
  if (key == 'w') wkey = false;
  if (keyCode == ENTER) enterkey = true;
}
