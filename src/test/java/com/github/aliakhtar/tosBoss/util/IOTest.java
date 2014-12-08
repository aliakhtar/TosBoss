package com.github.aliakhtar.tosBoss.util;

import com.github.aliakhtar.tosBoss.shared.Category;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class IOTest
{

    @Test
    public void testReadTrainingFile() throws Exception
    {
        List<String> result = IO.readTrainingFile(Category.THEY_MUST );
        assertNotNull(result);
        assertFalse(result.toString() , result.isEmpty());
    }
}