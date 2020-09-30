package com.foxminded.university.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name="classrooms")
public class Classroom {
    
    @Id
    private int id;
    @Size(min = 2, max = 40)
    private String name;
    @Min(10) 
    @Max(80)
    private int size;
    
    public Classroom() {
    }

    public Classroom(int id, String name, int size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }
    
    public boolean isFull(int studentsAmount) {
        return size == studentsAmount;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public int getSize() {
        return size;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Classroom other = (Classroom) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (size != other.size)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Classroom [id=" + id + ", name=" + name + ", size=" + size + "]";
    }

}
