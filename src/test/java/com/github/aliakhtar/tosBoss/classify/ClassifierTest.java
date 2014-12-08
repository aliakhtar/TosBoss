package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.IO;
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
            int hCount = 0;
            int mCount = 0;
            //String input = IO.readFile("example_tos/Sprint.ly");
            //List<String> sentences = NLP.getSentences( input );
            List<String> sentences = IO.readTrainingFile( cat );

            for (String sentence : sentences)
            {
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