public class StudentController {

    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void updateStudent(String name, String grade) {

        model.setName(name);
        model.setGrade(grade);

    }

    public void displayStudent() {

        view.displayStudentDetails(
                model.getName(),
                model.getId(),
                model.getGrade());

    }

}