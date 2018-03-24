package edu.nummethods.Integration.Simpson;

import edu.nummethods.Function.Multi;

public class Integral {


    Multi<Double> function;
    double accuracy;

    public Integral(Multi<Double> function, double accuracy){
        this.accuracy = accuracy;
        this.function = function;
    }

    public double calculate(double start, double end){
        if(end <= start){
            throw new ArithmeticException("start value exceeds or equals end value");
        }
        double result, step;
        double tempVar, tempVar1, tempVar2, tempVar3;
        double x;

        step = end - start;
        tempVar2 = 1;
        tempVar = function.calculate(start) + function.calculate(end);

        do{
            tempVar3 = tempVar2;
            step = step / 2;
            tempVar1 = 0;
            x = start + step;

            do{
                tempVar1 = tempVar1 + 2 * function.calculate(x);
                x = x + 2 * step;
            }while(x < end);

            tempVar = tempVar + tempVar1;
            tempVar2 = (tempVar + tempVar1) * step / 3;
            x = Math.abs(tempVar3 - tempVar2) / 15;
        }while(x > accuracy);
        result = tempVar2;

        return result;
    }

}
