package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //curl -X GET http://localhost:8080/api/v1/students
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    //curl -X GET http://localhost:8080/api/v1/students/1
    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        return studentService.getStudent(studentId);
    }

    //curl  -H 'Content-Type: application/json' --data '{"name":"Bilan","email":"bilan@gmail.com","dob":"1995-12-17","age":20}' http://localhost:8080/api/v1/students
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    //curl -X DELETE http://localhost:8080/api/v1/students/studentId
    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    //curl -X PUT -d 'name=SomeName&email=someEmail@gmail.com&dob=1987-02-04&age=35' http://localhost:8080/api/v1/students/1
    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String dob,
            @RequestParam(required = false) Integer age
            ) {
        studentService.updateStudent(studentId,name,email,dob,age);
    }

}
