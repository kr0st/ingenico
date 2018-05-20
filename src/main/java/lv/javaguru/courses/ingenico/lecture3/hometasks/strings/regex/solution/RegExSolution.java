package lv.javaguru.courses.ingenico.lecture3.hometasks.strings.regex.solution;

import lv.javaguru.courses.ingenico.lecture3.hometasks.strings.regex.SentencesCounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RegExSolution
{
    public static class SentencesCounterImpl extends SentencesCounter
    {
        @Override
        public int count(String filename)
        {
            try (FileReader fileReader = new FileReader(filename);
                 BufferedReader bufferedReader = new BufferedReader(fileReader))
            {
                String line = null;
                String lines = new String();

                while ((line = bufferedReader.readLine()) != null)
                    lines = lines.concat(line);

                String[] strings = lines.split("([^.!?\\s][^.!?]*)");
                return strings.length;
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static void sentenceCounter()
    {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current absolute path is: " + s);

        String filename = new String(s.concat("/src/main/java/lv/javaguru/courses/ingenico/lecture3/hometasks/strings/regex/text.txt"));

        System.out.print("Sentences in file \"");
        System.out.print(filename);
        System.out.print("\" = ");

        SentencesCounterImpl counter = new SentencesCounterImpl();

        int sentences = counter.count(filename);
        System.out.println(sentences);
    }

    public static void main(String[] args)
    {
        sentenceCounter();
    }
}
