package edu.nummethods.Rod;

import edu.nummethods.Integration.Simpson.Integral;
import edu.nummethods.Integration.Simpson.IntegralExpression;

public class RodTemperatureAlteration {


    private double rangeLimitStart, rangeLimitEnd, step;
    private StartTemperatureDistribution temperatureLocation;
    private double convectiveLeft, convectiveRight;
    private double length;
    private double currentNun;
    private Integral phiIntegral, CnIntegral;

    private static double SQUARE_PI = 9.86960440109;


    public RodTemperatureAlteration(double rangeLimitStart, double rangeLimitEnd,
                                    double convectionLeft, double convectionRight, double step,
                                    StartTemperatureDistribution temperatureLocation) {

        this.rangeLimitStart = rangeLimitStart;
        this.rangeLimitEnd = rangeLimitEnd;
        length = this.rangeLimitEnd - this.rangeLimitStart;
        this.convectiveLeft = convectionLeft;
        this.convectiveRight = convectionRight;
        this.step = step;
        this.temperatureLocation = temperatureLocation;

        IntegralExpression phiIntegralExpression = new IntegralExpression() {
            @Override
            public double calculate(double x) {
                double result;
                result = currentNun / length * Math.cos(currentNun / length * x);
                result += convectiveLeft * Math.sin(currentNun / length * x);
                return result * result;
            }
        };

        phiIntegral = new Integral(phiIntegralExpression, 0.001);

        IntegralExpression CnIntegralExpression = new IntegralExpression() {
            @Override
            public double calculate(double x) {
                double result;
                result = currentNun / length * Math.cos(currentNun / length * x);
                result += convectiveLeft * Math.sin(currentNun / length * x);
                result *= temperatureLocation.calculate(x);
                return result;
            }
        };

        CnIntegral = new Integral(CnIntegralExpression, 0.001);
    }


    //TODO rename function (now formulas title)
    private double cn(int n, double x){
        return 1 / modifiedPhi() * CnIntegral.calculate(0, length);
    }

    //TODO rename function (now formulas title)
    private double modifiedPhi(){
        return phiIntegral.calculate(0, length);
    }

    //TODO rename function (now formulas title)
    private double phinx(int n, double x){
        double usableVariable = nun(n) / length;

        double result = usableVariable * Math.cos(usableVariable*x);
        result += convectiveLeft * Math.sin(usableVariable*x);
        return result;
    }


    //TODO rename function (now formulas title)
    private double nun(int n){
        //return Math.sqrt(SQUARE_PI * n * n);
        return Math.sqrt(SQUARE_PI * n * n / (length * length)) * length;
    }


}
