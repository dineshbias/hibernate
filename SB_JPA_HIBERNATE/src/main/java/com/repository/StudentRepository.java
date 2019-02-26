package com.repository;

import com.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dinesh.joshi
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
