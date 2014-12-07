package com.github.aliakhtar.tosBoss.nlp;

import com.github.aliakhtar.tosBoss.util.IO;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.junit.Test;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class StanfordNlpTest
{

    @Test
    public void testParagraphProcessing()
    {
        String text = IO.readFile("PosTest1");

        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        System.out.println("Starting");
        long start = System.nanoTime();
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);
        long elapsed = System.nanoTime() - start;

        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap s : sentences)
        {
            for (CoreLabel token : s.get(CoreAnnotations.TokensAnnotation.class))
            {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);

                System.out.println(word + " , " + pos);
            }
        }

        System.out.println("Elapsed: " + TimeUnit.NANOSECONDS.toSeconds(elapsed));
    }
}
