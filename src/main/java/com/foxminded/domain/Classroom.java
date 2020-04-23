package com.foxminded.domain;

public class Classroom {

    private String name;
    private int size;
    
    public Classroom() {
    }

    public Classroom(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public boolean isFull(int studentsAmount) {
        return size == studentsAmount;
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
        return "Classroom [name=" + name + ", size=" + size + "]";
    } 
    
}
