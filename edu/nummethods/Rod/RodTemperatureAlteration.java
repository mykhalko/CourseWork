package edu.nummethods.Rod;

import edu.nummethods.Equation.Nonlinear.Newton.Newton;
import edu.nummethods.Function.Multi;
import edu.nummethods.Integration.Simpson.Integral;

public class RodTemperatureAlteration {

    class IntegralValues{
        private double nu;

        public IntegralValues(double nu){
            this.nu = nu;
        }

        public void setNu(double nu) {
            this.nu = nu;
        }

        public double getNu() {
            return nu;
        }

    }

    private double convectionLeft, convectionRight;
    private double length;
    private Newton equation;
    private Multi<Double> startTemperatureDistribution;
    private IntegralValues values;
    Integral phiIntegral, cnIntegral;

    public RodTemperatureAlteration(Multi<Double> startTemperatureDistribution,
                                    Multi<Double> equationExpression,
                                    double length, double convectionLeft, double convectionRight){

        Multi<Double> phiIntegralExpression;
        Multi<Double> cnIntegralExpression;

        values = new IntegralValues(0);
        this.equation = new Newton(equationExpression, 0.0000001,0.000000001);
        this.startTemperatureDistribution = startTemperatureDistribution;
        this.length = length;
        this.convectionLeft = convectionLeft;
        this.convectionRight = convectionRight;

        phiIntegralExpression = new Multi<Double>() {

            IntegralValues values;

            public Multi<Double >setValues(IntegralValues values){
                this.values = values;
                return this;
            }

            @Override
            public Double calculate(Double... variable) {
                double left, right, div, ksi;

                div = values.getNu() / length;
                ksi = variable[0];

                left = div * Math.cos(div * ksi);
                right = convectionLeft * Math.sin(div * ksi);

                return (left + right) * (left + right);
            }
        }.setValues(values);

        cnIntegralExpression = new Multi<Double> () {

            IntegralValues values;
            Multi<Double> ksiFunction;

            public Multi<Double> setValues(IntegralValues values, Multi<Double> ksiFunction){
                this.values = values;
                this.ksiFunction = ksiFunction;
                return this;
            }

            @Override
            public Double calculate(Double ... variable) {
                double left, right, div, ksi;

                ksi = variable[0];
                div = values.getNu() / length;

                left = div * Math.cos(div * ksi);
                right = convectionLeft * Math.sin(div * ksi);

                return ksiFunction.calculate(ksi) * (left + right);
            }
        }.setValues(values, this.startTemperatureDistribution);

        phiIntegral = new Integral(phiIntegralExpression, 0.0001);
        cnIntegral = new Integral(cnIntegralExpression, 0.0001);

    }

    public double calculate(double x, double t, int n){
        final double squaredA = 0.139;
        double result = 0;

        for(int i = 0; i < n; i++){
            values.setNu(nuFunction(i));
            double expression;
            expression = cnFunction(values.getNu());
            expression *= Math.exp(-squaredA * lambdaFunction(values.getNu() * t));
            expression *= phiFunction(x);
            result += expression;
        }
        return result;
    }

    public double nuFunction(int n){
        return equation.root(Math.PI * n + 0.0001);
    }

    public double lambdaFunction(double nuValue){
        return (nuValue * nuValue) / (length * length);
    }

    public double squaredModuloPhiFunction(){
        return phiIntegral.calculate(0, length);
    }

    public double phiFunction(double x){
        double left, right;
        double div = values.getNu() / length;
        left = (div * Math.cos(div * x));
        right = convectionLeft * Math.sin(div * x);
        return left + right;
    }

    public double cnFunction(double nuValue){
        return (1 / squaredModuloPhiFunction()) * cnIntegral.calculate(0, length);
    }


    // TEMPORARY METHODS 
    public void updateNu(int n){
        values.setNu(nuFunction(n));
    }

    public double cnIntegralResult(){
        return cnIntegral.calculate(0, 10);
    };

}
