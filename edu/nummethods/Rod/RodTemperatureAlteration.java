package edu.nummethods.Rod;

public class RodTemperatureAlteration {

    private double rangeLimitStart, rangeLimitEnd, step;
    private StartTemperatureDistribution temperatureLocation;
    private double convectionLeft, convectionRight;
    private static double SQUARE_PI = 9.86960440109;
    private double length;


    public RodTemperatureAlteration(double rangeLimitStart, double rangeLimitEnd,
                                    double convectionLeft, double convectionRight, double step,
                                    StartTemperatureDistribution temperatureLocation){

        this.rangeLimitStart = rangeLimitStart;
        this.rangeLimitEnd = rangeLimitEnd;
        length = rangeLimitEnd - rangeLimitStart;
        this.convectionLeft = convectionLeft;
        this.convectionRight = convectionRight;
        this.step = step;
        this.temperatureLocation = temperatureLocation;

    }


    //TODO rename function (now formulas title)
    private double phinx(int n, double x){
        double usableVariable = nun(n) / length;

        double result = usableVariable * Math.cos(usableVariable*x);
        result += convectionLeft * Math.sin(usableVariable*x);
        return result;
    }

    
    //TODO rename function (now formulas title)
    private double nun(int n){
        //return Math.sqrt(SQUARE_PI * n * n);
        return Math.sqrt(SQUARE_PI * n * n / (length * length)) * length;
    }


}
