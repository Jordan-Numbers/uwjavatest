package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn = "";
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int newAge){
    if(newAge < 0){
      throw new IllegalArgumentException();
    }else{
      age = newAge;
    }
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String newName){
    if(newName == null){
      throw new IllegalArgumentException();
    }else{
      name = newName;
    }
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double newSalary) {
    salary = newSalary;
  }
  
  public String getSSN() {
    return ssn;
  }
  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public boolean equals(Object obj) {
      if (obj == null) {
          return false;
      }
      if (getClass() != obj.getClass()) {
          return false;
      }
      Person other = (Person) obj;
      if (this.name.equals(other.name) && this.age == other.age) {
          return true;
      }
      return false;
  }


  public String toString() {
    return "[Person name:"+name+" age:"+Integer.toString(age)+" salary:"+Double.toString(salary)+"]";
  }

  public static List<Person> getNewardFamily(){
    List<Person> family = new ArrayList<Person>();
    family.add(new Person("Ted",41,250000.00));
    family.add(new Person("Charlotte",43,150000.00));
    family.add(new Person("Michael",22,10000.00));
    family.add(new Person("Matthew",15,0.00));
    return family;
  }

  public int compareTo(Person other) {
    if (other.salary > salary){
      return 1;
    }
    if (other.salary == salary){
      return 0;
    }
    return -1;
  }

  public static class AgeComparator implements Comparator<Person> {
    public int compare(Person a, Person b){
      if (b.age > a.age){
        return -1;
      }
      if (b.age == a.age){
        return 0;
      }
      return 1;
    }
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }
}
