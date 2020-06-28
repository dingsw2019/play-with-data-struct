import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String filename = "02-Trie-Basics/pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename,words)) {

            long startTime = System.nanoTime();
            BSTSet<String> set = new BSTSet<>();
            for (String word : words)
                set.add(word);

            for (String word : words)
                set.contains(word);

            long endTime = System.nanoTime();
            double time = (endTime-startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("Set Time: " + time + " s");

            System.out.println();

            startTime = System.nanoTime();
            Trie trie = new Trie();
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
