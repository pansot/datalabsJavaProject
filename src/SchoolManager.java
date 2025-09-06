
import java.util.*;

public class SchoolManager {

    private List<Student> students = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private Map<Student, List<Lesson>> studentEnrollments = new HashMap<>();

    public void addStudent(Scanner scanner) {

        System.out.println("Registering new student. Type info as requested.");
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine().trim();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine().trim();
        System.out.println("Enter date of birth: ");
        String dateOfBirth = scanner.nextLine().trim();

        Gender gender;
        boolean isValidGender = false;

        do {
            System.out.println("Enter gender (Male/Female): ");
            try {
                gender = Gender.valueOf(scanner.nextLine().toUpperCase().trim());
                isValidGender = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid gender. Please type \"male\" or \"female\"");
                isValidGender = false;
                gender = null;
            }
        } while (!isValidGender);

        System.out.println("Enter your address: ");
        String address = scanner.nextLine().trim();

        Student newStudent = new Student(firstName, lastName, dateOfBirth, gender, address);
        this.students.add(newStudent);
        studentEnrollments.put(newStudent, new ArrayList<>());

        System.out.println("Student succesfully added.");
    }

    public void addLesson(Scanner scanner) {
        System.out.println("Enter lesson title:");
        String lessonTitle = scanner.nextLine().trim();

        Lesson newLesson = new Lesson(lessonTitle);
        this.lessons.add(newLesson);
        System.out.println("Lesson succesfully added.");
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getStudentId() == id) {
                return student;
            }
        }
        return null;
    }

    private Lesson findLessonById(int id) {
        for (Lesson lesson : lessons) {
            if (lesson.getLessonId() == id) {
                return lesson;
            }
        }
        return null;
    }

//    Helper μέθοδος για να μπορέσουμε να προβάλουμε μόνο τα μαθήματα στα οποία ο μαθητής δεν έχει ήδη εγγραφεί
    private List<Lesson> getAvailableLessonsForStudent(Student student) {
        List<Lesson> availableLessons = new ArrayList<>();
        List<Lesson> enrolledLessons = studentEnrollments.get(student);

        for (Lesson lesson : lessons) {
            if (!enrolledLessons.contains(lesson)) {
                availableLessons.add(lesson);
            }
        }

        return availableLessons;
    }

    public void enrollStudentInLesson(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        if (lessons.isEmpty()) {
            System.out.println("No lessons available.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }

        Student selectedStudent = null;
        System.out.println("Type the id of the student you want to enroll:");
        
        while (selectedStudent == null) {
            try {
                int studentId = Integer.parseInt(scanner.nextLine());
                selectedStudent = findStudentById(studentId);

                if (selectedStudent == null) {
                    System.out.println("Error. No student found with this id. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type a valid id:");
            }
        }

        System.out.println("Selected student: " + selectedStudent);

        List<Lesson> availableLessons = getAvailableLessonsForStudent(selectedStudent);

        if (availableLessons.isEmpty()) {
            System.out.println("This student is already enrolled in all available lessons");
            return;
        }

        System.out.println("Available lessons for " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + ":");
        for (Lesson lesson : availableLessons) {
            System.out.println(lesson);
        }
    }


}
