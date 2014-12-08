package com.github.aliakhtar.tosBoss.util;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class NLPTest
{

    @Test
    public void testGetSentences() throws Exception
    {
        String text = IO.readFile("example_tos/3Scale");

        List<String> result = NLP.getPosTags(text);
        assertNotNull(result);
        assertFalse( result.toString(), result.isEmpty() );
    }


    @Test
    public void testPosTagging()
    {
        String text = IO.readFile("PosTest1");

        List<String> result = NLP.getPosTags(text);
        assertNotNull(result);

        assertFalse( result.toString(), result.isEmpty() );
    }
}