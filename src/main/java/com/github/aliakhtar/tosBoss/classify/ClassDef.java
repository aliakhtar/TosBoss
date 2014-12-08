package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;

import java.util.Collection;

/**
 * Definition of an entity that will be classified by the classifier.
 */
public class ClassDef implements Comparable<ClassDef>
{
    private final Collection<String> posTags;

    private final int trainingNodeCount;

    private final Category cat;

    private double probability = 0;

    public ClassDef(Category cat, int trainingNodeCount, Collection<String> posTags)
    {
        this.posTags = posTags;
        this.trainingNodeCount = trainingNodeCount;
        this.cat = cat;
    }

    public int getTrainingNodeCount()
    {
        return trainingNodeCount;
    }

    public Category getCat()
    {
        return cat;
    }


    public void setProbability(double probability)
    {
        this.probability = probability;
    }

    /*public double getProbability(String sentence)
    {
        List
    }*/

    @Override
    public String toString()
    {
        return "ClassDef{ " + cat  + " = " + probability + " }";
    }

    @Override
    public int compareTo(ClassDef o)
    {
        //Show highest probs on top
        if ( probability < o.probability )
            return 1;

        if (probability > o.probability)
            return  -1;

        return 0;
    }
}
