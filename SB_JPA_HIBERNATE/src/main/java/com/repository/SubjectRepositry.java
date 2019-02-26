package com.repository;

import com.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dinesh.joshi
 *
 */
public interface SubjectRepositry extends JpaRepository<Subject, Long> {

}
