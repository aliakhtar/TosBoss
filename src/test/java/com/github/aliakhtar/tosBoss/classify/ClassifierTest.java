package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.shared.Category;
import com.github.aliakhtar.tosBoss.util.IO;
import org.junit.Test;

import java.util.List;

public class ClassifierTest
{

    @Test
    public void testClassifySentence() throws Exception
    {
        Classifier c = Classifier.train();
        //String input = IO.readFile("example_tos/Sprint.ly");
        //List<String> sentences = NLP.getSentences( input );
        List<String> sentences = IO.readTrainingFile( Category.THEY_MUST );

        for (String sentence : sentences)
        {
            System.out.println(sentence);
            c.classifySentence(sentence);
            System.out.println("----------------------------------------------");
        }
    }
}