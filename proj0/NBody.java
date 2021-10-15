public class NBody {
    public static double readRadius (String name){
        double r = 0;
        In inFile = new In(name);
        inFile.readInt();
        r = inFile.readDouble();
        inFile.close();
        return r;
    }
    
    public static Body[] readBodies (String name){
        In inFile = new In(name);
        int n = inFile.readInt();
        Body[] Bodies = new Body[n];
        inFile.readDouble();
        for (int i=0;i<n;i++){
            Bodies[i] = new Body();
            Bodies[i].xxPos = inFile.readDouble();
            Bodies[i].yyPos = inFile.readDouble();
            Bodies[i].xxVel = inFile.readDouble();
            Bodies[i].yyVel = inFile.readDouble();
            Bodies[i].mass = inFile.readDouble();
            Bodies[i].imgFileName = inFile.readString();
        }
        inFile.close();
        return Bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] Bodies = readBodies(filename);
        double r = readRadius(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(-2.50e+11, -2.50e+11, "images/starfield.jpg");

        int n = Bodies.length;
        while(true){
            
            double[] xForces = new double[n];
            double[] yForces = new double[n];

            for (int i=0;i<n;i++){
                xForces[i] = Bodies[i].calcNetForceExertedByX(Bodies);
                yForces[i] = Bodies[i].calcNetForceExertedByY(Bodies);
            }
            StdDraw.enableDoubleBuffering();
            StdDraw.setScale(-2.50e+11, 2.50e+11);
		    StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");
            
            for (int i=0;i<n;i++){
                Bodies[i].update(dt, xForces[i], yForces[i]);
            }
            for (int i=0;i<n;i++){
                Bodies[i].draw();
            }
            StdDraw.show();
        }
        
    }

}
