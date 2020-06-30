import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int size = 100000;
        int m = 100000;

        UnionFind uf = new UnionFind(m);
        Random random = new Random();

        double startTime = System.nanoTime();

        for (int i = 0; i < m; i++){
            int r1 = random.nextInt(size);
            int r2 = random.nextInt(size);
            uf.unionElements(r1,r2);
        }

        for (int i = 0; i < m; i++){
            int r1 = random.nextInt(size);
            int r2 = random.nextInt(size);
            uf.isConnected(r1,r2);
        }

        double endTime = System.nanoTime();

        System.out.println("time: " + (endTime-startTime)/1000000000.0 + " s");
    }
}
