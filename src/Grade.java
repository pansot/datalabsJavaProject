public class Grade {
    private Student student;
    private Lesson lesson;
    private double grade;

    public Grade() {
    }

    public Grade(Student student, Lesson lesson, double grade) {
        this.student = student;
        this.lesson = lesson;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String toString() {
        return "Student: " + this.student + " | Lesson: " + this.lesson + " | Grade: " + this.grade;
    }
}
