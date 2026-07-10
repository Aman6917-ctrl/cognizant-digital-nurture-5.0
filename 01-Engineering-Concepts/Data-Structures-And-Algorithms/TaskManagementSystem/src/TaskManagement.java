public class TaskManagement {

    Task head = null;

    public void addTask(int id, String name, String status) {

        Task newTask = new Task(id, name, status);

        if (head == null) {
            head = newTask;
        } else {

            Task temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newTask;
        }

        System.out.println("Task Added Successfully.");
    }

    public void searchTask(int id) {

        Task temp = head;

        while (temp != null) {

            if (temp.taskId == id) {
                System.out.println("\nTask Found:");
                temp.display();
                return;
            }

            temp = temp.next;
        }

        System.out.println("Task Not Found.");
    }

    public void traverseTasks() {

        System.out.println();

        Task temp = head;

        while (temp != null) {

            temp.display();
            temp = temp.next;

        }

    }

    public void deleteTask(int id) {

        if (head == null) {
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            System.out.println("\nTask Deleted Successfully.");
            return;
        }

        Task temp = head;

        while (temp.next != null && temp.next.taskId != id) {
            temp = temp.next;
        }

        if (temp.next != null) {

            temp.next = temp.next.next;
            System.out.println("\nTask Deleted Successfully.");

        } else {

            System.out.println("Task Not Found.");

        }

    }

}