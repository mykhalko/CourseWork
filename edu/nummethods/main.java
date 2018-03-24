package edu.nummethods;

import edu.nummethods.Differentiation.Derivative;
import edu.nummethods.Function.Multi;
import edu.nummethods.Integration.Simpson.Integral;

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


        Integral integral = new Integral(function, 0.00000001);

        System.out.printf("function(x0 = 4): %f derivative(x0 = 4): %f integral[0 ; 4]: %f",
                function.calculate(4.0), derivative.calculate(4), integral.calculate(0, 4));

        return;
    }

}
