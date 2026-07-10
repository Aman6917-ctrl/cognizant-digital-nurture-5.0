public class Main {

    public static void main(String[] args) {

        Student student =
                new Student("Aman", 101, "A");

        StudentView view = new StudentView();

        StudentController controller =
                new StudentController(student, view);

        controller.displayStudent();

        System.out.println();

        controller.updateStudent("Aman Verma", "A+");

        controller.displayStudent();

    }

}