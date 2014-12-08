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
}
