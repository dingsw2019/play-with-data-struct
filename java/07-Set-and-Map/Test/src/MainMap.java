import java.util.ArrayList;

public class MainMap {

    private static double testMap(Map<String,Integer> map,String filename){

        double startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename,words)){
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word))
                    map.add(word,map.get(word)+1);
                else
                    map.add(word,1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        double endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        String dir = "Test/";
        String filename = "pride-and-prejudice.txt";

        BSTMap<String,Integer> bstmap = new BSTMap<>();
        double time1 = testMap(bstmap,dir+filename);
        System.out.println("bst time: " + time1);

        System.out.println();

        LinkedListMap<String,Integer> listMap = new LinkedListMap<>();
        double time2 = testMap(listMap,dir+filename);
        System.out.println("linkedlist time: " + time2);
    }
}
