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
int col = 1;

final int layer1 = 1;
final int layer2  = 2;
final int layer3  = 3;
final int layer4  = 4;
final int layer5  = 5;
int state = layer1;

final int edit = 1;
final int delete = 2;
int mode = edit;

ArrayList<PVector> points;
PShape path;


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

  points = new ArrayList<PVector>();
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
  if (mode == edit) {
    if (state == layer1) {
      editDraw(state, col);
    }
  } else if (mode == delete) {
    deleteDraw(state);
  }
}

void mousePressed() {
  loop();
  if (mode == edit) {
    if (state == layer2) {
      editPath(state);
    }
  } else if (mode == delete) {
    if (state == layer2) {
      deletePath(state);
    }
  }
}


void editDraw(int i, int c) {
  PGraphics pg = layers.get(i);
  if (mousePressed) {
    pg.beginDraw();
    pg.stroke(cp[c]);
    pg.strokeWeight(100);
    pg.line(pmouseX, pmouseY, mouseX, mouseY);
    pg.endDraw();
  }
  noStroke();
  circle(mouseX, mouseY, 100);
}


void deleteDraw(int i) {
  PGraphics pg = layers.get(i);
  if (mousePressed) {
    pg.beginDraw();
    pg.loadPixels();
    for (int x=0; x<pg.width; x++) {
      for (int y=0; y<pg.height; y++ ) {
        float distance = dist(x, y, mouseX, mouseY);
        if (distance <= 25) {
          int loc = x + y*pg.width;
          pg.pixels[loc] = 0x0;
        }
      }
    }
    pg.updatePixels();
    pg.endDraw();
  }
  noStroke();
  circle(mouseX, mouseY, 50);
}


void editPath(int i) {
  points.add(new PVector(mouseX, mouseY));
  PGraphics pg = layers.get(i);
  pg.beginDraw();
  pg.noFill();
  pg.beginShape();
  if (points.size()>0) {
    pg.curveVertex(points.get(0).x, points.get(0).y);
    for (PVector p : points) {
      pg.curveVertex(p.x, p.y);
      pg.ellipse(p.x, p.y, 3, 3);
    }
  }
  pg.endShape();
  pg.endDraw();
}


void deletePath(int i) {
  if (points.size()>2) {
    points.remove(points.size()-1);
  }
  PGraphics pg = layers.get(i);
  pg.beginDraw(); 
  pg.clear();
  pg.endDraw();
  pg.beginDraw();
  pg.noFill();
  pg.beginShape();
  if (points.size()>0) {
    pg.curveVertex(points.get(0).x, points.get(0).y);
    for (PVector p : points) {
      pg.curveVertex(p.x, p.y);
      pg.ellipse(p.x, p.y, 3, 3);
    }
  }
  pg.endShape();
  pg.endDraw();
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

  if (key == 'q') {
    if (col == cp.length-1) {
      col = 1;
    } else {
      col = col+1;
    }
  }

  if (key == 'e') {
    mode = edit;
  } else if (key == 'd') {
    mode = delete;
  }
}
