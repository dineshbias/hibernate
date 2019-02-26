package com.entity;

import com.constant.Qualification;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dinesh.joshi
 *
 */
@Entity
@Table(name = "Teachers")
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TEACHER_ID_SEQ")
  private int id;

  @Column(nullable = false)
  private String name;
  
  @Enumerated
  @Column(columnDefinition = "smallint")
  private Qualification qualification;
  
  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Qualification getQualification() {
    return qualification;
  }

  public void setQualification(Qualification qualification) {
    this.qualification = qualification;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public String toString() {
    return "Teacher [id=" + id + ", name=" + name + ", qualification=" + qualification + ", dateOfBirth=" + dateOfBirth
        + "]";
  }

}
