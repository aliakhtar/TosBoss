package com.github.aliakhtar.tosBoss.util;

public class Probability
{

    public static double calc(double numOfEvents, double numOfOutcomes)
    {
        try
        {
            return numOfEvents / numOfOutcomes;
        }
        catch (ArithmeticException e)
        {
            return 0;
        }
    }

    public static double calcWithSmoothing(double numOfEvents, double numOfOutcomes)
    {
        try
        {
            return (numOfEvents + 1) / ( numOfOutcomes + 1);
        }
        catch (ArithmeticException e)
        {
            return 0;
        }
    }
}
