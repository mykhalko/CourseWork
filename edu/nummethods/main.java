package edu.nummethods;

import com.sun.org.apache.xpath.internal.operations.Mult;
import edu.nummethods.Differentiation.Derivative;
import edu.nummethods.Equation.Nonlinear.Newton.Newton;
import edu.nummethods.Function.Multi;
import edu.nummethods.Integration.Simpson.Integral;
import edu.nummethods.Rod.RodTemperatureAlteration;

public class main {


    public static void main(String[] args){

        /*
        Multi<Double> function = new Multi<Double>() {
            @Override
            public Double calculate(Double... variable) {
                double value = variable[0].doubleValue();
                return 2 * value * value - 6;
            }
        };

        */
        Multi<Double> courseWorkFunction = new Multi<Double>() {
            @Override
            public Double calculate(Double... variable) {
                double left, right;
                double x = variable[0].doubleValue();

                left = 1 / Math.tan(x);
                right = x - 0.1 * 1 * 100 / x;
                right *= 1 / (10 * (0.1 + 1));

                return left - right;
            }
        };

        /*
        Newton courseWorkEquation = new Newton(courseWorkFunction, 0.00000001, 0.00000001);

        for(int i = 0; i < 10; i++) {
            System.out.println(courseWorkEquation.root(i * Math.PI + 0.0001));
        }

        */
//
        Multi<Double> temperatureDistribution = new Multi<Double>() {
            @Override
            public Double calculate(Double... variable) {
                return variable[0];
            }
        };

        RodTemperatureAlteration rodTemperatureAlteration = new RodTemperatureAlteration( temperatureDistribution, courseWorkFunction,
                10, 0.1, 1);

        double x = 0.001;
        double t = 10;
        System.out.printf("|    x    |    t    |    n    |    r    |\n");
        while(x < 10){
            System.out.printf("|%9.4f|%9.4f|%9d|%9.4f|\n", x, t, 100, rodTemperatureAlteration.calculate(x, t, 100));
            x += 1;
        }





        return;
    }

}
