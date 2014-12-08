package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;

import java.util.Collection;

/**
 * Definition of an entity that will be classified by the classifier.
 */
public class ClassDef
{
    private final Collection<String> posTags;

    private final int trainingNodeCount;

    private final Category cat;

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

    @Override
    public String toString()
    {
        return "ClassDef{ " + cat  + "}";
    }
}
