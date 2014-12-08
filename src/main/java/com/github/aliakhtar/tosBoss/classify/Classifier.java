package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.IO;
import com.github.aliakhtar.tosBoss.util.NLP;
import com.github.aliakhtar.tosBoss.util.Probability;

import java.util.ArrayList;
import java.util.List;

public class Classifier
{
    private Classifier() {}

    public static void train()
    {
        List<ClassDef> classes = new ArrayList<>();

        List<String> allPosTags = new ArrayList<>();

        List<String> allTrainingNodes = new ArrayList<>();


        for (Category cat : Category.values())
        {
            List<String> trainingNodes = IO.readTrainingFile(cat);
            allTrainingNodes.addAll( trainingNodes );
            ClassDef clazz = new ClassDef(cat, trainingNodes.size(),
                                          NLP.getPosTags(trainingNodes));

            classes.add( clazz );
        }

        for (ClassDef clazz : classes )
        {
            double prob = Probability.calc(clazz.getTrainingNodeCount(),
                                           allTrainingNodes.size() );
            System.out.println(clazz + " = " + prob );
        }
    }
}
