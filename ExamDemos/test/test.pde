int cols, rows;
int scl = 20;
int w =10000;
int h = 10000;

float xo;
float yo;
float zoom = 1;
float angleForY = 0;
float angleForX = PI/5;
PImage image;

final int EDIT = 1;
final int RUN  = 2;
final int CAM  = 3;
final int OBJECT  = 4;
int state = CAM;

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

ArrayList<obj> objList = new ArrayList<obj>();


void setup() {
  image = loadImage("C:\\Users\\rasmu\\Desktop\\Advanced-Programming\\ExamDemos\\test\\blurred.jpg");
  image.resize(1920, 1071);
  size(1920, 1071, P3D);
  cols = w / scl;
  rows = h / scl;
  xo = width/2;
  yo = height/2;
}

void draw() {
  directionalLight(166, 166, 196, -60, -60, -60);
  ambientLight(105, 105, 130);
  background(image);
  //stroke(100);

  //noFill();

  //rotateing
  translate(xo, yo);
  scale(zoom);
  rotateY(angleForY);
  rotateX(angleForX);

  noiseSeed(int(random(10000000))); 
  float d0 = random(100, 200);   
  float d1 = random(25, 75);
  translate(-cols, -rows);
  loadPixels();
  // Begin loop for columns
  for ( int i = 0; i < cols; i++) {
    // Begin loop for rows
    for ( int j = 0; j < rows; j++) {
      int x = i*2 + 2/2; // x position
      int y = j*2 + 2/2; // y position
      float n0 = noise(x/d0, y/d0, 0); 
      float n1 = noise(x/d1, y/d1, 10); 
      float n = 1 - (n0*0.75 + n1*0.25); 
      int k = int(n*cp.length); 
      pushMatrix();
      translate(x, y);
      fill(cp[k]);
      noStroke();
      rectMode(CENTER);
      rect(0, 0, 2, 2);
      popMatrix();
    }
  }

  DrawObj obj1 = new DrawObj();
  obj obj2 = new obj(300, 300, 100, 0, 360, 5);
  obj obj3 = new obj(325, 325, 100, 0, 360, 5);
  obj1.draw();
  obj2.draw();
  obj3.draw();

  print("Size" + objList.size());

  for (int i=0; i < objList.size(); i++) {
    objList.get(i).draw();
  }
  noLoop();
}


void keyPressed() {
  loop();
  if (key == '1') {
    state = EDIT;
  } else if (key == '2') {
    state = RUN;
  } else if (key == '3') {
    state = CAM;
  } else if (key == '4') {
    state = OBJECT;
  }
  keySwitch();
}

void mouseDragged() {
  loop();
  mouseSwitch();
}


void keySwitch() {
  switch(state) {

  case EDIT: 
    stateEdit(); 
    break;

  case RUN:
    stateRun();
    break;

  case CAM:
    stateCam();
    break;

  case OBJECT:
    stateObject();
    break;
  }
}


void stateEdit() {
}

void stateRun() {
}

void stateCam() {
  if (key == CODED) {
    if (keyCode == RIGHT) {
      angleForY += .1;
    } else if (keyCode == LEFT) {
      angleForY -= .1;
    } else if (keyCode == UP) {
      angleForX += .1;
    } else if (keyCode == DOWN) {
      angleForX -= .1;
    }
  }
  if (key == '+') {
    zoom += .1;
  } else if (key == '-') {
    zoom -= .1;
  } else if (key == ' ') {
    angleForY = 0;
    angleForX = PI/5;
    zoom = 1;
    xo = width/2;
    yo = height/2;
  }
}

void stateObject() {
}


void mouseSwitch() {
  switch(state) {

  case CAM:
    mouseCam();
    break;

  case OBJECT:
    mouseStateObject();
    break;
  }
}

void mouseCam() {
  xo = xo + (mouseX - pmouseX);
  yo = yo + (mouseY - pmouseY);
}

void mouseStateObject() {
  obj obj = new obj(mouseX, mouseY, 30, 0, 50, 5);
  objList.add(obj);
}
