package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.IO;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ClassifierTest
{

    @Test
    public void testClassifySentence() throws Exception
    {
        for (Category cat : Category.values())
        {
            System.out.println(cat);
            Classifier c = Classifier.train();
            //String input = IO.readFile("example_tos/Sprint.ly");
            //List<String> sentences = NLP.getSentences( input );
            List<String> sentences = IO.readTrainingFile( cat );
            System.out.println( sentences.size() );
            for (String sentence : sentences)
            {
                Map<Double, ClassDef> result = c.classifySentence(sentence);
                double first = result.entrySet().iterator().next().getKey();
                if (result.get(first).getCat() == cat)
                {
                    System.out.println("YESSS " + result + " , " + sentence);
                }
                else
                    System.out.println("Was " + result.get(first).getCat() + ", wanted : " + cat);
            }
        }
    }
}