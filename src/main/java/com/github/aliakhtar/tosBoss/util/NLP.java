package com.github.aliakhtar.tosBoss.util;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class NLP
{
    public static final StanfordCoreNLP POS_CORE = buildPosCore();

    private static final Pattern KILL_NUMERIC_START
            = Pattern.compile("\\d\\.[\\d.]*");

    private final static Pattern KILL_PARANTHESIS_STUFF
            = Pattern.compile("\\([\\S]*\\s[^)]*\\)");


    public enum Relationship
    {
        GOVERNER,
        DEPENDENT,
    }


    public static String cleanUp(String input)
    {
        input = input.trim();

        input = input.replaceAll( KILL_NUMERIC_START.pattern(), "" );
        input  = input.trim();

        input = input.replace("  ", " ");
        input = input.replaceAll(KILL_PARANTHESIS_STUFF.pattern(), "");

        input = input.replace("  ", " ");
        return input.trim();
    }



    public static Collection<String>
            getPosTags(Collection<String>multipleLinesOfText)
    {
        List<String> tagsList = new ArrayList<>( multipleLinesOfText.size() );

        for (String line : multipleLinesOfText )
        {
            tagsList.addAll(getPosTags(line));
        }

        return tagsList;
    }

    public static List<String> getSentences(String text)
    {
        Annotation doc = new Annotation(text);
        POS_CORE.annotate(doc);
        List<CoreMap> sentences = doc.get(CoreAnnotations.SentencesAnnotation.class);
        List<String> result = new ArrayList<>();

        for (CoreMap s : sentences)
        {
            result.add( s.get(CoreAnnotations.TextAnnotation.class) );
        }

        return result;
    }

    public static List<CoreMap> getCoreMap(String text)
    {
        text = cleanUp(text);
        Annotation a = new Annotation( text.trim() );
        POS_CORE.annotate(a);

        return a.get(CoreAnnotations.SentencesAnnotation.class);
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


    public static SemanticGraph getDependencies(CoreMap sentence)
    {
        return sentence.get(SemanticGraphCoreAnnotations.CollapsedDependenciesAnnotation.class);
    }

    public static String getPos(IndexedWord word)
    {
        if (word == null)
            return  null;
        return word.get(CoreAnnotations.PartOfSpeechAnnotation.class);
    }


    private static StanfordCoreNLP buildPosCore()
    {
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, depparse");

        return new StanfordCoreNLP(props);
    }
}
