package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.IO;
import edu.stanford.nlp.util.CoreMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassifierTest
{

    @Test
    public void testClassifySentence() throws Exception
    {
        Map<Category, Integer> hits = new HashMap<>();
        Map<Category, Integer> misses = new HashMap<>();
        Classifier c = Classifier.train();

        for (Category cat : Category.values())
        {
            System.out.println("In cat: " + cat);
            int hCount = 0;
            int mCount = 0;
            //String input = IO.readFile("example_tos/Sprint.ly");
            List<CoreMap> sentences = IO.readTrainingFile( cat );

            System.out.println("Got " + sentences.size() + " sentences");
            int i = 0;
            for (CoreMap sentence : sentences)
            {
                i++;
                System.out.println("Sentence # " + i + " / " + sentences.size());
                Map<Double, ClassDef> result = c.classifySentence(sentence);
                double first = result.entrySet().iterator().next().getKey();
                if (result.get(first).getCat() == cat)
                    hCount++;
                else
                    mCount++;
            }
            hits.put(cat, hCount);
            misses.put(cat, mCount);
        }

        System.out.println("Hits: "  + hits);
        System.out.println("Misses: " + misses);
    }
}