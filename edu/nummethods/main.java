package edu.nummethods;

import com.sun.org.apache.xpath.internal.operations.Mult;
import edu.HTMLNotary.HTMLNotary;
import edu.nummethods.Differentiation.Derivative;
import edu.nummethods.Equation.Nonlinear.Newton.Newton;
import edu.nummethods.Function.Multi;
import edu.nummethods.Integration.Simpson.Integral;
import edu.nummethods.Rod.RodTemperatureAlteration;

import java.io.IOException;
import java.util.ArrayList;

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
                double x = variable[0];
                return x*x+7-5*x;
            }
        };

        RodTemperatureAlteration rodTemperatureAlteration = new RodTemperatureAlteration( temperatureDistribution, courseWorkFunction,
                10, 0.1, 1);


        double x = 0;
        double t = 0;

        double[] xValues, yValues;
        xValues = new double[21];
        yValues = new double[21];
        int index = 0;

        System.out.printf("|    x    |    t    |    n    |    r    |\n");
        while(x <= 10){
            xValues[index] = x;
            yValues[index] = rodTemperatureAlteration.calculate(x, t, 50);
            System.out.printf("|%9.4f|%9.4f|%9d|%9.4f|\n", x, t, 50, yValues[index]);
            x += 0.5;
            index++;
        }

        try {
            HTMLNotary table = new HTMLNotary("/home/mykhalko/dev/Java/practice/Coursework/");
            table.start("GraphicalDependence");
            table.startTable("valuesTAble");
            table.startRow("xValues");
            table.addData("xValue", xValues);
            table.finishRow();
            table.startRow("yValues");
            table.addData("yValue", yValues);
            table.finishRow();
            table.finishTable();
            table.finish();
        }
        catch(IOException ex){
            System.out.println("Exception threw");
        }
        /*
        for(int i = 0; i < 20; i++) {
            rodTemperatureAlteration.updateNu(i);
            System.out.printf("Nu: %10.4f CnIntegral: ", rodTemperatureAlteration.nuFunction(i));
            System.out.print(i + ". " + rodTemperatureAlteration.cnIntegralResult());
            System.out.println(" PhiIntegral: " + rodTemperatureAlteration.phiIntegralResult());
        }
        */

        /*
        rodTemperatureAlteration.updateNu(101);
        System.out.printf("Nu: %10.4f CnIntegral: ", rodTemperatureAlteration.nuFunction(101));
        System.out.print(" . " + rodTemperatureAlteration.cnIntegralResult());
        System.out.println(" PhiIntegral: " + rodTemperatureAlteration.phiIntegralResult());

        */
        return;
    }

}
