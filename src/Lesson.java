public class Lesson {
    private String title;
    private static int nextId = 1;
    private int lessonId;

    public Lesson() {
    }

    public Lesson(String title) {
        this.title = title;
        this.lessonId = nextId++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLessonId() {
        return lessonId;
    }

    public String toString() {
        return "ID: " + this.lessonId + " - " + this.title;
    }
}

