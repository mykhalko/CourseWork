package edu.nummethods.Differentiation;
import edu.nummethods.Function.Multi;

public class Derivative {

    Multi<Double> function;
    double accretion;

    public Derivative(Multi<Double> function, double accretion){
        this.function = function;
        this.accretion = accretion;
    }

    public double calculate(double value){
        double result;

        result = function.calculate(value + accretion);
        result -= function.calculate(value);
        result /= accretion;
        return result;
    }

    public void setAccretion(double value){
        accretion = value;
    }

}
