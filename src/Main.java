import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SchoolManager manager = new SchoolManager();
        int convertedInput;



        while (true) {
            System.out.println("Choose one of the options below. Type 1-9 according to your choice, or 0 to exit");
            System.out.println("1. Add student");
            System.out.println("2. Add lesson");
            System.out.println("3. Enroll a student in a lesson");
            System.out.println("4. Assign grade to a student for a lesson");
            System.out.println("5. Search for a student");
            System.out.println("6. Show all lessons and grades of a student");
            System.out.println("7. Delete student");
            System.out.println("8. Delete lesson");
            System.out.println("9. Delete grade");

            String userInput = scanner.nextLine();

            try {
                convertedInput = Integer.parseInt(userInput);

                if (convertedInput == 0) {
                    System.out.println("Exiting program. Thank you.");
                    break;
                }

                if (convertedInput >= 1 && convertedInput <= 9) {
                    switch (convertedInput) {
                        case 1:
                            manager.addStudent(scanner);
                            break;
                        case 2:
                            manager.addLesson(scanner);
                            break;
                        case 3:
                            manager.enrollStudentInLesson(scanner);
                            break;
                        case 4:
                            manager.assignGrade(scanner);
                            break;
                        case 5:
                            manager.searchStudent(scanner);
                            break;
                        case 6:
                            manager.showStudentLessonsAndGrades(scanner);
                            break;
                        case 7:
                            manager.deleteStudent(scanner);
                            break;
                        case 8:
                            manager.deleteLesson(scanner);
                            break;
                        case 9:
                            manager.deleteGrade(scanner);
                            break;
                    }
                } else {
                    System.out.println("Invalid number. Please type a number between 1-6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type a number.");
            }
        }
    }
}
