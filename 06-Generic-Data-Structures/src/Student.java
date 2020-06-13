public class Student {

    // 属性
    private String name;
    private int score;

    // 构造函数
    public Student(String studentName, int studentScore) {
        name = studentName;
        score = studentScore;
    }

    // 输出
    @Override
    public String toString() {
        return String.format("Student(name: %s, score: %d)",name,score);
    }

    public static void main(String[] args) {
        Array<Student> arr = new Array<>();
        arr.addLast(new Student("A",10));
        arr.addLast(new Student("B",23));
        arr.addLast(new Student("C",89));
        System.out.println(arr);
    }
}
