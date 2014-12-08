package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.IO;
import com.github.aliakhtar.tosBoss.util.NLP;
import com.github.aliakhtar.tosBoss.util.Probability;

import java.util.*;

public class Classifier
{
    private final Collection<ClassDef> classes;

    private Classifier(Collection<ClassDef> classes)
    {
        this.classes = classes;
    }

    public void classifySentence(String sentence)
    {
        Map<Double, ClassDef> probs = new HashMap<>( classes.size() );
        List<String> posTags = NLP.getPosTags(sentence);
        for (ClassDef clazz : classes)
        {
            probs.put(clazz.getProbability(posTags), clazz );
        }

        Map<Double, ClassDef> sortedMap = new TreeMap<>(  );
        List<Double> probKeys = new ArrayList<>( sortedMap.size() );
        probKeys.addAll(sortedMap.keySet());
        Collections.sort( probKeys );

        for (Double p : probKeys)
        {
            sortedMap.put(p, probs.get(p));
        }

        System.out.println(sortedMap);
    }

    public static Classifier train()
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
            clazz.setProbability( prob );
        }

        Collections.sort( classes );
        return new Classifier( classes );
    }
}
