package com.origin.template.repository;

import com.origin.template.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
// public interface StudentRepository extends CrudRepository<Student, Long> {
}
