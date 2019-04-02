public class Planet{

  double xxPos; //its current x position
  double yyPos;//             y position
  double xxVel;//             velocity in the x direction
  double yyVel;//                             y direction
  double mass;//its mass
  String imgFileName;//The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif)

  public Planet(double xP, double yP, double xV,double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet b){
    this.xxPos = b.xxPos;
    this.yyPos = b.yyPos;
    this.xxVel = b.xxVel;
    this.yyVel = b.yyVel;
    this.mass = b.mass;
    this.imgFileName = b.imgFileName;
  }

  public double calcDistance(Planet a){
    double distance;
    distance = Math.sqrt((this.xxPos - a.xxPos)*(this.xxPos-a.xxPos) + (this.yyPos - a.yyPos)*(this.yyPos-a.yyPos));
    return distance;
  }

  //public static void main(String[] args) {
    //double dd;

    //System.out.println(Math.sqrt(4.0));
  //}
  static final double G = 6.67e-11;

  public double calcForceExertedBy(Planet a){
    double ForceExertedBy;
    double distance;
    distance = this.calcDistance(a);
    ForceExertedBy = (G * this.mass * a.mass)/(Math.pow(distance,2));
    return ForceExertedBy;
  }

  public double calcForceExertedByX(Planet a){
    double ForceExertedByX;
    ForceExertedByX = this.calcForceExertedBy(a)*(a.xxPos - this.xxPos)/this.calcDistance(a);
    return ForceExertedByX;
  }

  public double calcForceExertedByY(Planet a){
    double ForceExertedByY;
    ForceExertedByY = this.calcForceExertedBy(a)*(a.yyPos - this.yyPos)/this.calcDistance(a);
    return ForceExertedByY;
  }

  public double calcNetForceExertedByX(Planet[] manyPlanets){
    double NetForceExertedByX = 0;
    for(int i = 0; i < manyPlanets.length; i += 1){
      if(this.equals(manyPlanets[i])){
        continue;
      }NetForceExertedByX = NetForceExertedByX + this.calcForceExertedByX(manyPlanets[i]);
    }
    return NetForceExertedByX;
  }

  public double calcNetForceExertedByY(Planet[] manyPlanets){
    double NetForceExertedByY = 0;
    for(int i = 0; i < manyPlanets.length; i += 1){
      if(this.equals(manyPlanets[i])){
        continue;
      }NetForceExertedByY = NetForceExertedByY + this.calcForceExertedByY(manyPlanets[i]);
    }
    return NetForceExertedByY;
  }

  public Planet update(double dt, double fX, double fY){
    double accX;
    double accY;
    accX = fX/this.mass;
    accY = fY/this.mass;
    this.xxVel = this.xxVel + dt * accX;
    this.yyVel = this.yyVel + dt * accY;
    this.xxPos = this.xxPos + dt * this.xxVel;
    this.yyPos = this.yyPos + dt * this.yyVel;
    return this;
  }

  public void draw(){
    String filename = "images/" + this.imgFileName;
    StdDraw.picture(this.xxPos, this.yyPos, filename);
  }

}
