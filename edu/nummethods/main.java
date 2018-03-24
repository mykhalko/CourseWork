package edu.nummethods;

import com.sun.org.apache.xpath.internal.operations.Mult;
import edu.nummethods.Differentiation.Derivative;
import edu.nummethods.Function.Multi;
import edu.nummethods.Integration.Simpson.Integral;
import edu.nummethods.Integration.Simpson.IntegralExpression;
import edu.nummethods.Rod.Exceptions.UseAnotherReloadException;
import edu.nummethods.Rod.RodTemperatureAlteration;
import edu.nummethods.Rod.StartTemperatureDistribution;

public class main {


    public static void main(String[] args){

        Multi<Double> function = new Multi<Double>() {
            @Override
            public Double calculate(Double... variable) {
                double value = variable[0].doubleValue();
                return value * value * 2;
            }
        };

        Derivative derivative = new Derivative(function, 0.00000001);

        System.out.printf("function(x0 = 4): %f derivative(x0 = 4): %f",
                function.calculate(4.0), derivative.calculate(4));

        return;
    }

}
