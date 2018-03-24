package edu.nummethods.Equation.Nonlinear.Newton;

import edu.nummethods.Differentiation.Derivative;
import edu.nummethods.Function.Multi;

public class Newton {

    private Multi<Double> function;
    private Derivative derivative;
    double equationAccuracy, derivativeAccretion;

    public Newton(Multi<Double> function, double equationAccuracy, double derivativeAccretion){
        this.function = function;
        this.equationAccuracy = equationAccuracy;
        this.derivativeAccretion = derivativeAccretion;
        derivative = new Derivative(this.function, this.derivativeAccretion);
    }

    public void setEquationAccuracy(double value){
        if(value <= 0){
            throw new ArithmeticException("Accuracy <= 0");
        }
        this.equationAccuracy = value;
    }

    public void setDerivativeAccretion(double value){
        if(value <= 0){
            throw new ArithmeticException("Accuracy <= 0");
        }
        derivativeAccretion = value;
        derivative.setAccretion(derivativeAccretion);
    }

    public double root(double startValue){
        double result, previousResult;
        result = startValue;

        do{
            previousResult = result;
            result -= function.calculate(previousResult) / derivative.calculate(previousResult);
        }while(Math.abs(result - previousResult) > equationAccuracy);

        return result;
    }

}
