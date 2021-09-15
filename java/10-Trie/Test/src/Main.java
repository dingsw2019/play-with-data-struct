import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String filename = "Test/pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename,words)) {

            long startTime,endTime;
            double time;

            System.out.println();

            startTime = System.nanoTime();
            Trie4 trie = new Trie4();
            for (String word : words)
                trie.add(word);

            for (String word : words)
                trie.contains(word);

            endTime = System.nanoTime();
            time = (endTime-startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie Time: " + time + " s");
        }
    }
}
