package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dinesh.joshi
 *
 */
@Entity
@Table(name = "Subjects")
public class Subject {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SUBJECT_ID_SEQ")
  private Long id;

  @Column(name = "sub_name", nullable = false)
  private String subName;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSubName() {
    return subName;
  }

  public void setSubName(String subName) {
    this.subName = subName;
  }

  @Override
  public String toString() {
    return "Subject [id=" + id + ", subName=" + subName + "]";
  }

}
