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

int[] terrain;
int[] biome;


void setup() {
  size(1920, 1080);
  noiseSeed(int(random(10000000))); 
  loadPixels();
  terrain = new int[height*width];
  for (int j = 0; j < height; j++) {
    for (int i = 0; i < width; i++) {
      pixels[j*width + i] = cp[3];
      terrain[j*width + i] = 3;
    }
  } 
  updatePixels();
}


void draw() {
  noLoop();
}

void randomMap() {
  noiseSeed(int(random(10000000))); 
  loadPixels(); 
  float d0 = random(100, 200);   
  float d1 = random(25, 75);
  terrain = new int[height*width];
  for (int j = 0; j < height; j++) {
    for (int i = 0; i < width; i++) {
      float n0 = noise(i/d0, j/d0, 0); 
      float n1 = noise(i/d1, j/d1, 10); 
      float n = 1 - (n0*0.75 + n1*0.25); 
      int k = int(n*cp.length); 
      pixels[j*width + i] = cp[k];
      terrain[j*width + i] = k;
    }
  } 
  updatePixels();
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
  if (key == 'r') {
    randomMap();
  }
}

void stateRun() {
}

void stateCam() {
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
}

void mouseStateObject() {
}
