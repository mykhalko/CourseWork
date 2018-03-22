package edu.nummethods.Rod;

import edu.nummethods.Rod.Exceptions.UseAnotherReloadException;

public interface StartTemperatureDistribution {

    default double calculate(double x, double y) throws UseAnotherReloadException{
        throw new UseAnotherReloadException("Current function reload not granted");
    }

    default double calculate(double x) throws UseAnotherReloadException{
        throw new UseAnotherReloadException("Current function reload not granted");
    }

}
