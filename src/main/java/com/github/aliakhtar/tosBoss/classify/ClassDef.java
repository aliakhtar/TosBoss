package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.NLP;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.Collection;

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

    public double getProbability(CoreMap sentence)
    {
        double featureProbabilities = 0;
        for (SemanticGraphEdge edge : NLP.getDependencies(sentence).edgeIterable())
        {
            double featureScore = 0;
            for (Feature f : features)
            {
                featureScore+= f.getScore(edge);
            }
            //featureProbabilities += Probability.calc(featureScore, this.posTags.size() );
            featureProbabilities += featureScore;
        }

        try
        {
            //return ( featureProbabilities * probability);
            return featureProbabilities;
        }
        catch (ArithmeticException e)
        {
            e.printStackTrace();
            return 0;
        }
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
