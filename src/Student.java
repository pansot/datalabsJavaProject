import java.util.HashMap;

public class Student {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Gender gender;
    private String address;
    private static int nextId = 1;
    private int studentId;

    public Student() {
    }

    public Student(String firstName, String surname, String dateOfBirth, Gender gender, String address) {
        this.firstName = firstName;
        this.lastName = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.studentId = nextId++;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStudentId() {
        return studentId;
    }

    public String toString() {
        return "ID: " + this.studentId + " - " + this.firstName + " " + this.lastName;
    }
}
