package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.util.IO;
import com.github.aliakhtar.tosBoss.util.NLP;
import org.junit.Test;

import java.util.List;

public class ClassifierTest
{

    @Test
    public void testClassifySentence() throws Exception
    {
        Classifier c = Classifier.train();
        String input = IO.readFile("example_tos/Sprint.ly");
        List<String> sentences = NLP.getSentences( input );

        for (String sentence : sentences)
        {
            System.out.println(sentence);
            c.classifySentence(sentence);
            System.out.println("----------------------------------------------");
        }
    }
}