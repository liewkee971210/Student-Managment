package com.origin.template.service;

import com.origin.template.model.Student;
import com.origin.template.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Map<String, Object>> listStudent(Integer count, Integer offset) {
    String sql = "SELECT name, icno, age, gender "
        + "FROM student "
        + "ORDER BY name "
        + "OFFSET :offset ROWS FETCH NEXT :count ROWS ONLY";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("offset", offset);
    params.addValue("count", count);
    return namedJdbcTemplate.queryForList(sql, params);
  }

    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public int updateStudent(Long id, Student studentDetails) {
        String sql = "UPDATE student "
            + "SET name = :name,icno = :icno,age = :age,gender = :gender "
            + "WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("name", studentDetails.getName());
        params.addValue("icno", studentDetails.getIcno());
        params.addValue("age", studentDetails.getAge());
        params.addValue("gender", studentDetails.getGender());
        return namedJdbcTemplate.update(sql, params);
    }

    @Transactional
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> getStudent(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable).getContent();
    }

}