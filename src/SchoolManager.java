
import java.util.*;

public class SchoolManager {

    private List<Student> students = new ArrayList<>();
    private List<Lesson> lessons = new ArrayList<>();
    private Map<Student, List<Lesson>> studentEnrollments = new HashMap<>();
    private List<Grade> grades = new ArrayList<>();

    public SchoolManager() {
        initializeTestData();
    }

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

        System.out.println("Available students: ");
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

        Lesson selectedLesson = null;
        System.out.println("Type the id of the lesson you want to enroll the student in: ");

        while (selectedLesson == null) {
            try {
                int selectedLessonId = Integer.parseInt(scanner.nextLine());
                Lesson foundLesson = findLessonById(selectedLessonId);

                if (foundLesson == null) {
                    System.out.println("Error. No lesson found with this ID. Try again.");
                } else if (!availableLessons.contains(foundLesson)) {
                    System.out.println("Error. This lesson is not available for the student or student is already enrolled.");
                } else {
                    selectedLesson = foundLesson;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type a valid ID. ");
            }
        }

        studentEnrollments.get(selectedStudent).add(selectedLesson);
        System.out.println("Student " + selectedStudent.getFirstName() + " " + selectedStudent.getLastName() + " succesfully enrolled in " + selectedLesson.getTitle());
    }

    public void assignGrade(Scanner scanner) {
        boolean hasEnrollments = false;

        for (Student student : studentEnrollments.keySet()) {
            if (!studentEnrollments.get(student).isEmpty()) {
                hasEnrollments = true;
                break;
            }
        }

        if (!hasEnrollments) {
            System.out.println("No students are enrolled in any lessons.");
            return;
        }

        System.out.println("Students enrolled in lessons: ");

        for (Student student : studentEnrollments.keySet()) {
            if (!studentEnrollments.get(student).isEmpty()) {
                System.out.println("ID: " + student.getStudentId() + " - " + student.getFirstName() + " " + student.getLastName() + " (" + studentEnrollments.get(student).size() + " lessons)");
            }
        }

        System.out.println("Enter student ID: ");

        Student selectedStudent = null;

        while (selectedStudent == null) {
            try {
                int studentId = Integer.parseInt(scanner.nextLine());
                selectedStudent = findStudentById(studentId);

                if (selectedStudent == null) {
                    System.out.println("Error. No student found with this ID. Try again.");
                } else if (studentEnrollments.get(selectedStudent).isEmpty()) {
                    System.out.println("Error. Selected student has no enrollments. Try again.");
                    selectedStudent = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type a valid ID:");
            }
        }

        System.out.println("Selected student: " + selectedStudent);

        List<Lesson> studentLessons = studentEnrollments.get(selectedStudent);
        System.out.println("Lessons enrolled in: ");
        for (Lesson lesson : studentLessons) {
            System.out.println(lesson);
        }

        System.out.println("Enter lesson ID:");

        Lesson selectedLesson = null;

        while (selectedLesson == null) {
            try {
                int lessonId = Integer.parseInt(scanner.nextLine());
                selectedLesson = findLessonById(lessonId);

                if (selectedLesson == null) {
                    System.out.println("Error. No lesson found with this ID. Try again.");
                } else if (!studentLessons.contains(selectedLesson)) {
                    System.out.println("Error. This student is not enrolled in this lesson.");
                    selectedLesson = null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please type a valid id:");
            }
        }

        System.out.println("Selected lesson: " + selectedLesson.getTitle());
        System.out.println("Enter grade (0.0 - 10.0): ");

        double gradeToAssign = -1;
        boolean validGrade = false;

        while (!validGrade) {
            try {
                double input = Double.parseDouble(scanner.nextLine());

                if (input >= 0.0 && input <= 10.0) {
                    gradeToAssign = input;
                    validGrade = true;
                } else {
                    System.out.println("Invalid input. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }

        Grade existingGrade = findGrade(selectedStudent, selectedLesson);

        if (existingGrade != null) {
            existingGrade.setGrade(gradeToAssign);
            System.out.println("Grade updated succesfully.");
        } else {
            Grade newGrade = new Grade(selectedStudent, selectedLesson, gradeToAssign);
            grades.add(newGrade);
            System.out.println("Grade assigned succesfully.");
        }
    }

    private Grade findGrade(Student student, Lesson lesson) {
        for (Grade grade : grades) {
            if (grade.getStudent() == student && grade.getLesson() == lesson) {
                return grade;
            }
        }
        return null;
    }

    private void initializeTestData() {
        Student panos = new Student("Panos", "Sotiriadis", "28/08/1995", Gender.MALE, "Lagyna");
        Student nikos = new Student("Nikos", "Papadopoulos", "01/01/2000", Gender.MALE, "Thessaloniki");
        Student nikoleta = new Student("Maria", "Papadopoulou", "13/09/1999", Gender.FEMALE, "Athens");

        students.add(panos);
        students.add(nikos);
        students.add(nikoleta);

        studentEnrollments.put(panos, new ArrayList<>());
        studentEnrollments.put(nikos, new ArrayList<>());
        studentEnrollments.put(nikoleta, new ArrayList<>());

        Lesson math = new Lesson("Mathematics");
        Lesson physics = new Lesson("Physics");
        Lesson history = new Lesson("History");

        lessons.add(math);
        lessons.add(physics);
        lessons.add(history);

        studentEnrollments.get(panos).add(math);
        studentEnrollments.get(panos).add(physics);
        studentEnrollments.get(nikos).add(history);
    }

    public void searchStudent(Scanner scanner) {
        System.out.println("Type the name of the student you want to search for: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Search cancelled - no input provided.");
            return;
        }

        List<Student> studentsFound = new ArrayList<>();
        String searchTerm = input.toLowerCase();

        for (Student student : students) {
            if (student.getFirstName().toLowerCase().contains(searchTerm) || student.getLastName().toLowerCase().contains(searchTerm)) {
                studentsFound.add(student);
            }
        }

        if (!studentsFound.isEmpty()) {
            System.out.println(studentsFound.size() + " students found.");
            System.out.println(studentsFound);
        } else {
            System.out.println("No students found.");
        }
    }

    public void showStudentLessonsAndGrades (Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.println("Available students: ");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("Type the ID of the student whose lessons and grades you want to see: ");

        try {
            int studentId = Integer.parseInt(scanner.nextLine());
            Student foundStudent = findStudentById(studentId);

            if (foundStudent == null) {
                System.out.println("No student found with this ID. Try again.");
            } else {

                System.out.println(foundStudent.getFirstName() + " " + foundStudent.getLastName() + " lessons and grade: ");

                boolean hasGrades = false;
                for (Grade grade : grades) {
                    if (grade.getStudent() == foundStudent) {
                        System.out.println(grade.getLesson() + ": " + grade.getGrade());
                        hasGrades = true;
                    }
                }
            }
        }catch (NumberFormatException e) {
            System.out.println("Invalid input. Please type a valid ID.");
            return;
        }
    }
}
