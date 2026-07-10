public class Main {

    public static void main(String[] args) {

        EmployeeManagement management = new EmployeeManagement();

        management.addEmployee(new Employee(101, "Aman", "Software Engineer", 65000));

        management.addEmployee(new Employee(102, "Rahul", "Data Analyst", 55000));

        management.addEmployee(new Employee(103, "Priya", "HR", 45000));

        System.out.println("\nEmployee List");

        management.traverseEmployees();

        management.searchEmployee(102);

        management.deleteEmployee(102);

        System.out.println("\nAfter Deletion");

        management.traverseEmployees();

    }

}