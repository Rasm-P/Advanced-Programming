class obj {
  float x, y;
  float radius;
  float ang;
  int pts;
  float depth;
  obj(float xk, float yk, float r, float a1, int p, float d) {
    x=xk;
    y=yk;
    radius = r;
    ang = a1;
    pts = p;
    depth = d;
  }

  void draw() {
    fill(150);
    PShape can = Cylender( radius, pts, depth);
    shape(can);
    PShape circle1 = Circle( radius, pts, depth);
    shape(circle1);
    PShape circle2 = Circle( radius, pts, -depth);
    shape(circle2);
  }

  PShape Cylender(float radius, int pts, float depth) {
    textureMode(NORMAL);
    PShape sh = createShape();
    sh.beginShape(QUAD_STRIP);
    for (int i=0; i<=pts; i++) {
      float  px = cos(radians(ang))*radius;
      float  py = sin(radians(ang))*radius;
      sh.vertex(x+px, y+py, depth);
      sh.vertex(x+px, y+py, -depth);
      ang+=360/pts;
    }
    sh.noStroke();
    sh.endShape();
    return sh;
  }

  PShape Circle(float radius, int pts, float depth) {
    textureMode(NORMAL);
    PShape sh = createShape();
    sh.beginShape(POLYGON);
    for (int i=0; i<=pts; i++) {
      float  px = cos(radians(ang))*radius;
      float  py = sin(radians(ang))*radius;
      sh.vertex(x+px, y+py, depth);
      ang+=360/pts;
    }
    sh.noStroke();
    sh.endShape();
    return sh;
  }
}
