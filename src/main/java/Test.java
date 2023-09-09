import task1.*;
import task2.*;
import task3.*;

public class Test {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        System.out.println("Task1:\nCreate new user:");
        task1.addNewUser(11,
                "Glenna Procleimer",
                "Uwu",
                "Chaim_Uwu@dana.io",
                "Dinosaur Park",
                "Suite 4492",
                "Springfield",
                "76495-3110",
                "24.6464",
                "-168.8888",
                "(775)976-6799 x41206",
                "comrad.com",
                "Sons and Yons",
                "Switchable contextually-based projector",
                "aggregate no real-time technologies");
        System.out.println("Delete user:");
        task1.deleteUser(2);
        System.out.println("Update user data:");
        task1.updateElementOfUserData(1,"name", "uwuwuwuwuw");
        System.out.println("Print all users:");
        task1.printAllUsers();
        System.out.println("Print user by id:");
        task1.printUserDataById(8);
        System.out.println("Print user by username:");
        task1.printUserDataByUsername("Maxime_Nienow");
        System.out.println("Task2:\nPrint all comments on last user post and create a file with all this comments:");
        task2.allCommentsOnLastUserPost(1);
        System.out.println("Task3:\n Print all unfinished user tasks:");
        task3.allOpenTasksOfUserById(1);
    }
}

