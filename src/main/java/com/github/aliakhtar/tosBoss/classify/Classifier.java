package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.IO;
import com.github.aliakhtar.tosBoss.util.Probability;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class Classifier
{
    private final Collection<ClassDef> classes;

    private Classifier(Collection<ClassDef> classes)
    {
        this.classes = classes;
    }

    public Map<Double, ClassDef> classifySentence(CoreMap sentence)
    {
        Map<Double, ClassDef> probs = new HashMap<>( classes.size() );
        for (ClassDef clazz : classes)
        {
            probs.put(clazz.getProbability(sentence), clazz );
        }

        Map<Double, ClassDef> sortedMap = new TreeMap<>( Collections.reverseOrder() );
        for (Double p : probs.keySet() )
        {
            sortedMap.put(p, probs.get(p));
        }

        //return sortedMap.get(0).getCat();
        return sortedMap;
    }

    public static Classifier train()
    {
        List<ClassDef> classes = new ArrayList<>();

        List<String> allPosTags = new ArrayList<>();

        List<String> allTrainingNodes = new ArrayList<>();

        long totalNodes = 0;

        for (Category cat : Category.values())
        {
            List<CoreMap> trainingNodes = IO.readTrainingFile(cat);
            totalNodes+= trainingNodes.size();

            ClassDef clazz = new ClassDef(cat, trainingNodes.size(),
                                          trainingNodes);

            System.out.println( cat + " , nodes: " + trainingNodes.size() );
            System.out.println("All nodes: " + allTrainingNodes.size() );
            classes.add( clazz );
        }

        for (ClassDef clazz : classes )
        {
            double prob = Probability.calc(clazz.getTrainingNodeCount(),
                                           allTrainingNodes.size() );

            clazz.setProbability( prob );
            //clazz.setProbability( 1 );
        }

        Collections.sort( classes );
        System.out.println(classes);
        return new Classifier( classes );
    }
}
