package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
@Component
public class StudentRepository {

                // <StudentName,Student>
    private HashMap<String,Student> studentMap=new HashMap<>();
    private HashMap<String,Teacher> teacherMap=new HashMap<>();
    private HashMap<String, List<String>> studentTeacherMap=new HashMap<>();
                 //<Teacher, Students>

    public void addStudent(Student student){
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }

    public void saveTeacherStudentPair(String teacher,String student){

        if(teacherMap.containsKey(teacher) && studentMap.containsKey(student)){
            List<String> studentList=new ArrayList<>();

            studentList=studentTeacherMap.get(teacher);
            studentList.add(student);

            studentTeacherMap.put(teacher,studentList);
        }
    }

    public Student findStudentByName(String studentName){
        return studentMap.get(studentName);
    }
    public Teacher findTeacherByName(String teacherName){
        return teacherMap.get(teacherName);
    }

    public List<String> findStudentsFromTeacher(String teacherName){
        List<String> studentList=new ArrayList<String>();

        if(studentTeacherMap.containsKey(teacherName)){
            studentList=studentTeacherMap.get(teacherName);
        }
        return studentList;
    }

    public List<String> findAllStudents(){
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacherName){
        List<String> studentListOfTeacher=new ArrayList<>();

        if(studentTeacherMap.containsKey(teacherName)){
            studentListOfTeacher=studentTeacherMap.get(teacherName);

            for(String student : studentListOfTeacher){
                if(studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }
            studentTeacherMap.remove(teacherName);
        }
    }

    public void deleteAllTeacher(){
        HashSet<String> studentSet=new HashSet<String>();

        for(String teacher : studentTeacherMap.keySet()){
            for(String student : studentTeacherMap.keySet()){
                studentSet.add(student);
            }
        }

        for(String student : studentSet){
            if(studentMap.containsKey(student)){
                studentMap.remove(student);
            }
        }
    }

}
