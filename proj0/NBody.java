public class NBody{

  public static double readRadius(String filename){
    In in = new In(filename);
    int planetnum = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String filename){
    In in = new In(filename);
    int planetnum = in.readInt();
    double radius = in.readDouble();
    Planet[] manyPlanets = new Planet[planetnum];
    for (int i =0; i < planetnum; i += 1) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      manyPlanets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
    }
    return manyPlanets;
  }

  public static void main(String[] args) {
    /**collecting all needed input*/
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] manyPlanets = readPlanets(filename);
    /**drawing the background*/
    String background = "images/starfield.jpg";
    StdDraw.setScale(-radius, radius);

    /* Clears the drawing window. */
    StdDraw.clear();

    /* Stamps three copies of advice.png in a triangular pattern. */
    //StdDraw.picture(0, 75, imageToDraw);
    //StdDraw.picture(-75, -75, imageToDraw);
    StdDraw.picture(0,0,background);

    for (int i = 0; i < manyPlanets.length; i += 1){
        manyPlanets[i].draw();
    }

    /* Shows the drawing to the screen, and waits 2000 milliseconds. */
    //StdDraw.show();
    //StdDraw.pause(2000);

    StdDraw.enableDoubleBuffering();

    for (double time = 0.0; time <= T; time = time + dt){
      double[] xForces = new double [manyPlanets.length];
      double[] yForces = new double [manyPlanets.length];
      for (int i = 0; i < manyPlanets.length; i += 1){
          xForces[i] = manyPlanets[i].calcNetForceExertedByX(manyPlanets);
          yForces[i] = manyPlanets[i].calcNetForceExertedByY(manyPlanets);
      }

      for (int i = 0; i < manyPlanets.length; i += 1){
          manyPlanets[i].update(dt,xForces[i],yForces[i]);
      }
    StdDraw.clear();
    StdDraw.picture(0,0,background);
    for (int i = 0; i < manyPlanets.length; i += 1){
        manyPlanets[i].draw();
    }

    StdDraw.show();
    StdDraw.pause(10);
    }
    /**Print the universe*/
    StdOut.printf("%d\n", manyPlanets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < manyPlanets.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  manyPlanets[i].xxPos, manyPlanets[i].yyPos, manyPlanets[i].xxVel,
                  manyPlanets[i].yyVel, manyPlanets[i].mass, manyPlanets[i].imgFileName);
    }
  }


}
