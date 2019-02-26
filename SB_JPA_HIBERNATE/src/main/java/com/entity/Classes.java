/**
 * 
 */
package com.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author dinesh.joshi
 * create table classes (
       name varchar(255) not null,
        section varchar(255) not null,
        class_teacher_id bigint not null,
        primary key (class_teacher_id)
 */
@Entity
@Table(name = "Classes")
public class Classes {

  @Id
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "section", nullable = false)
  private String section;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  private Teacher classTeacher;

  @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Student> students = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSection() {
    return section;
  }

  public void setSection(String section) {
    this.section = section;
  }

  public Teacher getClassTeacher() {
    return classTeacher;
  }

  public void setClassTeacher(Teacher classTeacher) {
    this.classTeacher = classTeacher;
  }

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  @Override
  public String toString() {
    return "Classes [id=" + id + ", name=" + name + ", section=" + section + ", classTeacher=" + classTeacher
        + ", Students=" + students + "]";
  }
  
  
}
