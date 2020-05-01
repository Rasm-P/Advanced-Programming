int cols, rows;
int scl = 20;
int w = 600;
int h = 600;

float[][] terrain; 

float xo;
float yo;
float zoom = 1;
float angleForY = 0;
float angleForX = PI/5;
PImage image;

final int EDIT = 0;
final int RUN  = 1;
final int CAM  = 2;
final int OBJECT  = 3;
int state = CAM; 

void setup() {
  image = loadImage("C:\\Users\\rasmu\\Desktop\\Advanced-Programming\\ExamDemos\\test\\blurred.jpg");
  image.resize(1280, 720);
  size(1280, 720, P3D);
  cols = w / scl;
  rows = h / scl;
  xo = width/2;
  yo = height/2;
  //terrain = new float[cols][rows];
  //for(int y = 0; y < rows; y++) {
  //  beginShape(TRIANGLE_STRIP);
  //  for(int x = 0; x < cols; x++) {
  //    terrain[x][y] = random(-10,10);
  // }
  //}
}

void draw() {
  directionalLight(166, 166, 196, -60, -60, -60);
  ambientLight(105, 105, 130);
  background(image);
  //stroke(100);
  fill(121, 255, 77);
  //noFill();

  //rotateing
  translate(xo, yo);
  scale(zoom);
  rotateY(angleForY);
  rotateX(angleForX);

  translate(-w/2, -h/2);
  for (int y = 0; y < rows-1; y++) {
    beginShape(TRIANGLE_STRIP);
    for (int x = 0; x < cols; x++) {
      vertex(x*scl, y*scl);
      vertex(x*scl, (y+1)*scl);
    }
    endShape();
  }
  DrawObj obj1 = new DrawObj();
  obj obj2 = new obj(300, 300, 100, 0, 360, 5);
  obj obj3 = new obj(325, 325, 100, 0, 360, 5);
  obj1.draw();
  obj2.draw();
  obj3.draw();
}


void keyPressed() {
  if (key == '0') {
    state = EDIT;
  } else if (key == '1') {
    state = RUN;
  } else if (key == '2') {
    state = CAM;
  } else if (key == '3') {
    state = OBJECT;
  }
  keySwitch();
}

void mouseDragged() {
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
    angleForX = PI/3;
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
  }
}

void mouseCam() {
  xo = xo + (mouseX - pmouseX);
  yo = yo + (mouseY - pmouseY);
}
