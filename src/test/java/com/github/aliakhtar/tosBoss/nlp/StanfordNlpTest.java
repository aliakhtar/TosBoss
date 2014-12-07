package com.github.aliakhtar.tosBoss.nlp;

import com.github.aliakhtar.tosBoss.util.IO;
import com.github.aliakhtar.tosBoss.util.NLP;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class StanfordNlpTest
{

    @Test
    public void testPosTagging()
    {
        String text = IO.readFile("PosTest1");

        List<String> result = NLP.getPosTags(text);
        assertNotNull( result );
        System.out.println(result.toString() );

        assertFalse( result.toString(), result.isEmpty() );
    }
}
