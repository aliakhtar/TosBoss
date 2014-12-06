package com.github.aliakhtar.tosBoss.nlp;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.junit.Test;

import java.util.List;
import java.util.Properties;
public class StanfordNlpTest
{

    @Test
    public void testParagraphProcessing()
    {
        String text = "These Terms of Service (“Terms”) refer exclusively to the usage of 3scale’s DEVELOPER, POWER, and PRO solutions.";

        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos");

        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation doc = new Annotation(text);
        pipeline.annotate(doc);

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

    }
}
