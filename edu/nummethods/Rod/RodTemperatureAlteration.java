package edu.nummethods.Rod;

import edu.nummethods.Integration.Simpson.Integral;
import edu.nummethods.Integration.Simpson.IntegralExpression;

public class RodTemperatureAlteration {

    class DynamicIntegralExpression implements IntegralExpression{

        private double nunExpression;
        private double convectionLeft;
        private double length;

        public void setNunExpression(double value){
            this.nunExpression = value;
        }

        public void setConvectionLeft(double value){
            this.convectionLeft = value;
        }

        public DynamicIntegralExpression(double length){
            this.nunExpression = 1;
            this.convectionLeft = 1;
            this.length = length;
        }


        @Override
        public double calculate(double x){
            double result = nunExpression / length * Math.cos(nunExpression / length * x);
            result += convectionLeft * Math.sin(nunExpression / length * x);
            return result;
        }

    }

    private double rangeLimitStart, rangeLimitEnd, step;
    private StartTemperatureDistribution temperatureLocation;
    private double convectionLeft, convectionRight;
    private double length;
    private DynamicIntegralExpression dynamicIntegralExpression;
    private Integral phiIntegral;

    private static double SQUARE_PI = 9.86960440109;


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
        dynamicIntegralExpression = new DynamicIntegralExpression(length);
        phiIntegral = new Integral(dynamicIntegralExpression, 0.001);
    }


    //TODO rename function(now formulas title)
    private double modifiedSquaredPhinx(int n, double x){

        return 0;
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
