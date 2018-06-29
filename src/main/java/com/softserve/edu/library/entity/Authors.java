package com.softserve.edu.library.entity;

import java.util.Objects;

public class Authors {

  private long id;
  private String firstName;
  private String lastName;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Authors authors = (Authors) o;
    return id == authors.id &&
            Objects.equals(firstName, authors.firstName) &&
            Objects.equals(lastName, authors.lastName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, firstName, lastName);
  }

  @Override
  public String toString() {
    return "Authors{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
}
