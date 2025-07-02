package ru.mirea.khasanovart.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Employee {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String power;
    public int level;

    public Employee() {}

    public Employee(String name, String power, int level) {
        this.name = name;
        this.power = power;
        this.level = level;
    }

    @Override
    public String toString() {
        return name + " (" + power + "), уровень: " + level;
    }
}