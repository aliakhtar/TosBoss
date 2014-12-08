package com.github.aliakhtar.tosBoss.util;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class NLP
{
    private static final StanfordCoreNLP POS_CORE = buildPosCore();

    public static Collection<String>
            getPosTags(Collection<String>multipleLinesOfText)
    {
        List<String> tagsList = new ArrayList<>( multipleLinesOfText.size() );

        for (String line : multipleLinesOfText )
        {
            tagsList.addAll( getPosTags(line) );
        }

        return tagsList;
    }

    public static List<String> getPosTags(String text)
    {
        Annotation doc = new Annotation(text);
        POS_CORE.annotate(doc);

        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        List<String> result = new ArrayList<>();
        for (CoreMap s : sentences)
        {
            for (CoreLabel token : s.get(CoreAnnotations.TokensAnnotation.class))
            {
                result.add( token.get(CoreAnnotations.PartOfSpeechAnnotation.class) );
            }
        }

        return result;
    }


    private static StanfordCoreNLP buildPosCore()
    {
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos");

        return new StanfordCoreNLP(props);
    }
}
