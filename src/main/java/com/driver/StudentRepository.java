package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepository {

                // <StudentName,Student>
    private Map<String,Student> studentMap;
    private Map<String,Teacher> teacherMap;
    private Map<String, List<String>> studentTeacherMap;
                 //<Teacher, Students>

    public StudentRepository(){
        this.studentMap=new HashMap<>();
        this.teacherMap=new HashMap<>();
        this.studentTeacherMap=new HashMap<>();
    }

    public void addStudent(Student student){
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(),teacher);
    }

    public void saveTeacherStudentPair(String student,String teacher){

//        if(teacherMap.containsKey(teacher) && studentMap.containsKey(student)){
//            List<String> studentList=new ArrayList<>();
//
//            studentList=studentTeacherMap.get(teacher);
//            studentList.add(student);
//
//            studentTeacherMap.put(teacher,studentList);
//        }

        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            List<String> currStudentsList = new ArrayList<>();

            if(studentTeacherMap.containsKey(teacher))
                currStudentsList = studentTeacherMap.get(teacher);
            currStudentsList.add(student);
            studentTeacherMap.put(teacher, currStudentsList);
        }

    }

    public Student findStudentByName(String studentName){
        return studentMap.get(studentName);
    }
    public Teacher findTeacherByName(String teacherName){
        return teacherMap.get(teacherName);
    }

    public List<String> findStudentsFromTeacher(String teacherName){
//        List<String> studentList=new ArrayList<String>();
//
//        if(studentTeacherMap.containsKey(teacherName)){
//            studentList=studentTeacherMap.get(teacherName);
//        }
//        return studentList;

        if(studentTeacherMap.containsKey(teacherName))
            return studentTeacherMap.get(teacherName);

        return new ArrayList<>();
    }

    public List<String> findAllStudents(){
        return new ArrayList<>(studentMap.keySet());
    }

    public void deleteTeacher(String teacherName){
//        List<String> studentListOfTeacher=new ArrayList<>();
//
//        if(studentTeacherMap.containsKey(teacherName)){
//            studentListOfTeacher=studentTeacherMap.get(teacherName);
//
//            for(String student : studentListOfTeacher){
//                if(studentMap.containsKey(student)){            // deleting all students from a that teachers list
//                    studentMap.remove(student);
//                }
//            }
//            teacherMap.remove(teacherName);
//            studentTeacherMap.remove(teacherName);
//        }
        List<String> studentOfTeacher = new ArrayList<>();
        if(studentTeacherMap.containsKey(teacherName)){
            studentOfTeacher = studentTeacherMap.get(teacherName);
            for (String student : studentOfTeacher){
                if (studentMap.containsKey(student)){
                    studentMap.remove(student);
                }
            }
            studentTeacherMap.remove(teacherName);
        }
        if(teacherMap.containsKey(teacherName))
            teacherMap.remove(teacherName);
    }

    public void deleteAllTeacher(){
//        HashSet<String> studentSet=new HashSet<String>();
//
//        for(String teacher : studentTeacherMap.keySet()){
//            for(String student : studentTeacherMap.get(teacher)){
//                studentSet.add(student);
//            }
//        }
//
//        for(String student : studentSet){
//            if(studentMap.containsKey(student)){
//                studentMap.remove(student);
//            }
//        }

        Set<String> studentSet = new HashSet<>();
        teacherMap = new HashMap<>();

        for(String teacher : studentTeacherMap.keySet())
            for (String student : studentTeacherMap.get(teacher))
                studentSet.add(student);

        for (String student : studentSet)
            if (studentMap.containsKey(student))
                studentMap.remove(student);

        studentTeacherMap = new HashMap<>();
    }

}
