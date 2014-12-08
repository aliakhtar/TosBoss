package com.github.aliakhtar.tosBoss.classify;

import com.github.aliakhtar.tosBoss.util.NLP;
import com.github.aliakhtar.tosBoss.util.Probability;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;

import java.util.HashMap;
import java.util.Map;

public class Feature
{
    public static enum Type
    {
        GOVERNER,
        DEPENDENT,
        SOURCE,
        TARGET;
    }

    private final Map<Type, String> posTags;

    public Feature(SemanticGraphEdge graphEdge)
    {
        posTags = map(graphEdge);
    }

    public double getScore(SemanticGraphEdge edge )
    {
        double score = 0;
        Map<Type, String> tags = map(edge);
        for ( Type t : tags.keySet() )
        {
            if ( ! safeEquals( tags.get(t), posTags.get(t) ) )
                continue;
            score += Probability.calc( 1, posTags.size() );
            posTags.put(t, "==REMOVED==");
        }

        return score;
    }

    private boolean safeEquals( String s1, String s2 )
    {
        if (s1 == null && s2 == null)
            return true;

        if (s1 == null || s2 == null)
            return  false;

        return  s1.equals(s2);
    }

    private Map<Type, String> map(SemanticGraphEdge edge)
    {
        Map<Type, String> posTags = new HashMap<>( Type.values().length );
        posTags.put( Type.GOVERNER, NLP.getPos(edge.getGovernor()) );
        posTags.put( Type.DEPENDENT, NLP.getPos(edge.getDependent()) );
        posTags.put( Type.SOURCE, NLP.getPos(edge.getSource()) );
        posTags.put( Type.TARGET, NLP.getPos( edge.getTarget() ) );

        return posTags;
    }

}
