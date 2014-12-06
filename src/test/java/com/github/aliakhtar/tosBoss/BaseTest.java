package com.github.aliakhtar.tosBoss;

import java.io.File;
import java.util.Scanner;

public abstract class BaseTest
{

    public static String readFile(String fileName)
    {
        StringBuilder result = new StringBuilder("");

        //Get file from resources folder
        ClassLoader classLoader = BaseTest.class.getClassLoader();
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

}
