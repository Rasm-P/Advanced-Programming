ArrayList<PGraphics> layers = new ArrayList();

color[] cp = {
  color(0, 126, 192), 
  color(24, 154, 208), 
  color(58, 168, 214), 
  color(94, 186, 220), 
  color(128, 199, 228), 
  color(163, 216, 235), 
  color(197, 229, 243), 
  color(232, 244, 250), 
  color(135, 209, 63), 
  color(203, 227, 107), 
  color(255, 227, 153), 
  color(255, 205, 127), 
  color(234, 136, 70), 
  color(209, 104, 47), 
  color(187, 76, 15), 
  color(148, 56, 0)
}; 

final int layer1 = 1;
final int layer2  = 2;
final int layer3  = 3;
final int layer4  = 4;
final int layer5  = 5;
int state = layer1;


void setup() {
  size(1920, 1080);
  PGraphics pg = createGraphics(width, height);
  pg.beginDraw();
  pg.background(cp[0]);
  pg.endDraw();
  layers.add(pg);

  for (int i = 0; i < 5; i++) {
    layers.add(createGraphics(width, height));
  }
}


void draw() {
  background(255);
  for (int i = 0; i < layers.size(); i++) {
    image(layers.get(i), 0, 0);
  }
  noLoop();
}


void mouseDragged() {
  loop();
  editDraw(state);
}


void editDraw(int i) {
  PGraphics pg = layers.get(i);
  if (mousePressed) {
    pg.beginDraw();
    pg.stroke(cp[i]);
    pg.strokeWeight(100);
    pg.line(pmouseX, pmouseY, mouseX, mouseY);
    pg.endDraw();
  }
  noStroke();
  circle(mouseX, mouseY, 100);
}


void keyPressed() {
  loop();
  if (key == '1') {
    state = layer1;
  } else if (key == '2') {
    state = layer2;
  } else if (key == '3') {
    state = layer3;
  } else if (key == '4') {
    state = layer4;
  } else if (key == '5') {
    state = layer5;
  }
}
