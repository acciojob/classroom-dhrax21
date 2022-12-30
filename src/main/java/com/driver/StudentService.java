package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void saveStudents(Student student){
        studentRepository.addStudent(student);
    }

    public void saveTeacher(Teacher teacher){
        studentRepository.addTeacher(teacher);
    }

    public void createTeacherStudentMap(String student,String teacher){
        studentRepository.saveTeacherStudentPair(student,teacher);
    }
    public Student findStudent(String student){
       return studentRepository.findStudentByName(student);
    }

    public Teacher findTeacher(String teacher){
        return studentRepository.findTeacherByName(teacher);
    }
    public List<String> findStudentsByTeacherName(String teacherName){
        return studentRepository.findStudentsFromTeacher(teacherName);
    }
    public List<String> findAllStudents(){
        return studentRepository.findAllStudents();
    }

    public void deleteTeacher(String teacherName){
        studentRepository.deleteTeacher(teacherName);
    }

    public void deleteAll(){
        studentRepository.deleteAllTeacher();
    }

}
