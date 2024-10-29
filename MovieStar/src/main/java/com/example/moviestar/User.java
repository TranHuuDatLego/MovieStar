package com.example.moviestar;

import java.util.Objects;

public class User {
    private Integer u_id;
    private String name;
    private Integer age;

    public User(Integer age, Integer u_id, String name) {
        this.age = age;
        this.name = name;
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
