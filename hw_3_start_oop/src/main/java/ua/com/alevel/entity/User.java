package ua.com.alevel.entity;

public class User {
    private String id;
    private String name;
    private String lastName;
    private int age;
    private String email;

    public User(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%-36s",id) + " | " + String.format("%-26s", name) + " | " +
                String.format("%-27s", lastName) + " | " + String.format("%-7s", age) + " | " +
                String.format("%-30s", email);
    }
}