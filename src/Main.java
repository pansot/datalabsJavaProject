import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SchoolManager manager = new SchoolManager();
        int convertedInput;



        while (true) {
            System.out.println("Choose one of the options below. Type 1-6 according to your choice, or 0 to exit");
            System.out.println("1. Add student");
            System.out.println("2. Add lesson");
            System.out.println("3. Enroll a student in a lesson");
            System.out.println("4. Assign grade to a student for a lesson");
            System.out.println("5. Search for a student");
            System.out.println("6. Show all lessons and grades of a student");

            String userInput = scanner.nextLine();

            try {
                convertedInput = Integer.parseInt(userInput);

                if (convertedInput == 0) {
                    System.out.println("Exiting program. Thank you.");
                    break;
                }

                if (convertedInput >= 1 && convertedInput <= 6) {
                    switch (convertedInput) {
                        case 1:
                            manager.addStudent(scanner);
                            break;
                        case 2:
                            manager.addLesson(scanner);
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
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
