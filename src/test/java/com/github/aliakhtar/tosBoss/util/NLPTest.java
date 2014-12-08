package com.github.aliakhtar.tosBoss.util;

import edu.stanford.nlp.util.CoreMap;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void testCleanUp() throws Exception
    {
        String input = "this one is ok";
        String output = NLP.cleanUp(input);
        assertEquals(input, output);

        output = NLP.cleanUp("1.1 lol");
        assertEquals("lol", output);

        assertEquals("lol", NLP.cleanUp("lol (some bullshit)")  );
        assertEquals("lol", NLP.cleanUp("lol (some bullshit) (\"some more bullshit\")"));
    }


    @Test
    public void testDepParse() throws Exception
    {
        List<CoreMap> sentences = NLP.getCoreMap("My name is Bob. I like to eat.");

        System.out.println(sentences);
        assertFalse( sentences.isEmpty() );
    }
}