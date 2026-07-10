public class Main {

    public static void main(String[] args) {

        TaskManagement manager = new TaskManagement();

        manager.addTask(101, "Complete Java Assignment", "Pending");

        manager.addTask(102, "Prepare Interview", "In Progress");

        manager.addTask(103, "Submit Report", "Completed");

        System.out.println("\nTask List");

        manager.traverseTasks();

        manager.searchTask(102);

        manager.deleteTask(102);

        System.out.println("\nAfter Deletion");

        manager.traverseTasks();

    }

}