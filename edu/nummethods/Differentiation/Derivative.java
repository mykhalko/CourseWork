package edu.nummethods.Differentiation;
import edu.nummethods.Function.Multi;

public class Derivative {

    Multi function;
    double accretion;

    public Derivative(Multi function, double accretion){
        this.function = function;
        this.accretion = accretion;
    }

    public double calculate(double value){
        double result;

        result = function.calculate(value + accretion) - function.calculate(value);
        result /= accretion;
        return result;
    }

}
