class DrawObj {
  void draw() {
    fill(255, 255, 255);
    drawCylinder(10, 180, 200, 16); // Draw a mix between a cylinder and a cone
    //drawCylinder(70, 70, 120, 64); // Draw a cylinder
    //drawCylinder(0, 180, 200, 4); // Draw a pyramid
  }

  void drawCylinder(float topRadius, float bottomRadius, float tall, int sides) {
    float angle = 0;
    float angleIncrement = TWO_PI / sides;
    beginShape(QUAD_STRIP);
    for (int i = 0; i < sides + 1; ++i) {
      vertex(topRadius*cos(angle), 0, topRadius*sin(angle));
      vertex(bottomRadius*cos(angle), tall, bottomRadius*sin(angle));
      angle += angleIncrement;
    }
    endShape();

    // If it is not a cone, draw the circular top cap
    if (topRadius != 0) {
      angle = 0;
      beginShape(TRIANGLE_FAN);

      // Center point
      vertex(0, 0, 0);
      for (int i = 0; i < sides + 1; i++) {
        vertex(topRadius * cos(angle), 0, topRadius * sin(angle));
        angle += angleIncrement;
      }
      endShape();
    }

    // If it is not a cone, draw the circular bottom cap
    if (bottomRadius != 0) {
      angle = 0;
      beginShape(TRIANGLE_FAN);

      // Center point
      vertex(0, tall, 0);
      for (int i = 0; i < sides + 1; i++) {
        vertex(bottomRadius * cos(angle), tall, bottomRadius * sin(angle));
        angle += angleIncrement;
      }
      endShape();
    }
  }
}
