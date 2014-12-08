package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.NLP;
import com.github.aliakhtar.tosBoss.util.Probability;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Definition of an entity that will be classified by the classifier.
 */
public class ClassDef implements Comparable<ClassDef>
{
    private final Collection<CoreMap> sentences;

    private final Collection<Feature> features;

    private final int trainingNodeCount;

    private final Category cat;

    private double probability = 0;

    public ClassDef(Category cat, int trainingNodeCount, Collection<CoreMap> sentences)
    {
        this.sentences = sentences;
        this.trainingNodeCount = trainingNodeCount;

        features = new ArrayList<>();
        for (CoreMap s : sentences)
        {
            for (SemanticGraphEdge edge : NLP.getDependencies(s).edgeIterable() )
            {
                features.add( new Feature(edge) );
            }
        }

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

    public double getProbability(List<String> posTags)
    {
        double featureProbabilities = 0;
        for (String pos : posTags)
        {
            int tagCount = getPosCount(pos);
            featureProbabilities *= Probability.calc(tagCount, this.posTags.size() );
        }

        try
        {
            return ( featureProbabilities * probability);
        }
        catch (ArithmeticException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    private int getPosCount(String inputPos)
    {
        int count = 0;

        for (String pos : posTags)
        {
            if (pos.equals(inputPos))
                count = count + 1;
        }

        return count;
    }

    @Override
    public String toString()
    {
        //return "ClassDef{ " + cat  + " = " + probability + " }";
        return "ClassDef{ " + cat  + " }";
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
