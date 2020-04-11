int cols, rows;
int scl = 20;
int w = 600;
int h = 600;

float[][] terrain; 

float xo;
float yo;
float zoom = 1;
float angleForY = 0;
float angleForX = PI/3;
PImage image;

void setup() {
  image = loadImage("C:\\Users\\rasmu\\Desktop\\test\\blurred.jpg");
  image.resize(600,600);
  size(600,600, P3D);
  cols = w / scl;
  rows = h / scl;
  xo = width/2;
  yo = height/2;
  terrain = new float[cols][rows];
  for(int y = 0; y < rows; y++) {
    beginShape(TRIANGLE_STRIP);
    for(int x = 0; x < cols; x++) {
      terrain[x][y] = random(-10,10);
    }
  }
}

void draw() {
  background(image);
  stroke(100);
  fill(121, 255, 77);
  //noFill();
  
  //rotateing
  translate(xo,yo);
  scale(zoom);
  rotateY(angleForY);
  rotateX(angleForX);
  
  translate(-w/2,-h/2);
  for(int y = 0; y < rows-1; y++) {
    beginShape(TRIANGLE_STRIP);
    for(int x = 0; x < cols; x++) {
      vertex(x*scl,y*scl,terrain[x][y]);
      vertex(x*scl,(y+1)*scl,terrain[x][y+1]);
    }
    endShape();
  }
}

void keyPressed() {
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
  if(key == '+') {
      zoom += .1;
    } else if (key == '-') {
      zoom -= .1;
    }
  if (key == ' ') {
    angleForY = 0;
    angleForX = PI/3;
    zoom = 1;
    xo = width/2;
    yo = height/2;
  }
}

void mouseDragged() {
    xo = xo + (mouseX - pmouseX);
    yo = yo + (mouseY - pmouseY);
}
