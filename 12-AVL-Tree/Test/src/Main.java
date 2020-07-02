import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String filename = "Test/pride-and-prejudice.txt";
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename,words)) {

            long startTime = System.nanoTime();

            AVLTree2<String,Integer> avl = new AVLTree2<>();
            for (String word : words){
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word,1);
            }

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");
        }
    }
}
