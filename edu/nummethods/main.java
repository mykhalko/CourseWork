package edu.nummethods;

import edu.nummethods.Integration.Simpson.Integral;
import edu.nummethods.Integration.Simpson.IntegralExpression;
import edu.nummethods.Rod.Exceptions.UseAnotherReloadException;
import edu.nummethods.Rod.RodTemperatureAlteration;
import edu.nummethods.Rod.StartTemperatureDistribution;

public class main {


    public static void main(String[] args){

        StartTemperatureDistribution startTemperatureDistribution = new StartTemperatureDistribution() {
            @Override
            public double calculate(double x) throws UseAnotherReloadException {

                double result;
                double divider = 2*x*x - x + 2;
                if(divider <= 0){
                    throw new ArithmeticException("Disturbed area of admissible values");
                }

                return (2*x - 0.5) / Math.sqrt(divider);
            }
        };

        RodTemperatureAlteration rodTemperatureAlteration = new RodTemperatureAlteration(
                0, 10, 0.1,
                1, 0.1, startTemperatureDistribution);

        return;
    }

}
