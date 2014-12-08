package com.github.aliakhtar.tosBoss.util;

import com.github.aliakhtar.tosBoss.shared.Category;
import edu.stanford.nlp.util.CoreMap;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public abstract class IO
{

    public static String readFile(String fileName)
    {
        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = IO.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try
        {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (Exception e) {
            throw  new RuntimeException( e.toString() );
        }

        return result.toString();
    }


    public static List<CoreMap> readTrainingFile( Category cat)
    {
        String fullText = readTrainingFile( cat.name() );


        return NLP.getCoreMap(fullText);
    }

    public static String readTrainingFile(String path)
    {
        return NLP.cleanUp(readFile("training/" + path));
    }
}
