public class Body{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    double G = 6.67e-11;
        
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(){
        
    }

    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b){
        double dis = 0;
        double dx = b.xxPos - xxPos;
        double dy = b.yyPos - yyPos;
        dis = Math.sqrt(dx*dx + dy*dy);
        return dis;
    }

    public double calcForceExertedBy(Body b){
        double f = G*mass*b.mass/(calcDistance(b)*calcDistance(b));
        return f;
    }

    public double calcForceExertedByX(Body b){
        double fx = calcForceExertedBy(b) * (b.xxPos - xxPos)/calcDistance(b);
        return fx;
    }
    public double calcForceExertedByY(Body b){
        double fy = calcForceExertedBy(b) * (b.yyPos - yyPos)/calcDistance(b);
        return fy;
    }

    public double calcNetForceExertedByX(Body[] bodies){
        double fNetX = 0;
        for (int i=0;i<bodies.length;i++){
            if (bodies[i].equals(this))
                continue;
            fNetX += this.calcForceExertedByX(bodies[i]);
        }
        return fNetX;
    }

    public double calcNetForceExertedByY(Body[] bodies){
        double fNetY = 0;
        for (int i=0;i<bodies.length;i++){
            if (bodies[i].equals(this))
                continue;
            fNetY += this.calcForceExertedByY(bodies[i]);
        }
        return fNetY;
    }

    public void update(double dt, double fx, double fy){
        double aNetX = fx/mass;
        double aNetY = fy/mass;
        xxVel = xxVel + dt*aNetX ;
        yyVel = yyVel + dt*aNetY;

        xxPos = xxPos + dt*xxVel;
        yyPos = yyPos + dt*yyVel;
    }

    public void draw(){
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-2.50e+11, 2.50e+11);
		//StdDraw.clear();
        //StdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        //StdDraw.show();
		StdDraw.pause(3);
    }

}