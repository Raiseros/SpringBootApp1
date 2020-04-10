package ru.holyav.springbootapp.crudloginform.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.holyav.springbootapp.crudloginform.entity.Student;
import ru.holyav.springbootapp.crudloginform.service.StudentService;

import java.sql.SQLException;
import java.util.List;


@Controller
public class HelloController {

    @Autowired
    private StudentService studentService;

   /* @Autowired
    private SecurityService securityService;*/


    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String getStudents(Model model) {
     List<Student> theStudents = studentService.getStudents();
     model.addAttribute("students", theStudents);
        return "list-students";
    }

    @RequestMapping(value ="/access-denied", method = RequestMethod.GET)
    public String showAccessDenied() {

        return "access-denied";
    }
    @RequestMapping(value ="/access-accepted", method = RequestMethod.GET)
    public String showAccessAccepted() {

        return "access-accepted";
    }

    @RequestMapping(value ="registration", method = RequestMethod.GET)
    public String registry(Model model) {
        Student theStudent = new Student();
        model.addAttribute("student", theStudent);
        return "registration";
    }

    @RequestMapping(value ="formForUpdate", method = RequestMethod.GET)
    public String update(@RequestParam("studentId") int theId, Model model) {
        Student theStudent = studentService.getStudent(theId);
        model.addAttribute("student", theStudent);
        return "registration";
    }


    @PostMapping("saveStudent")
    public String addStudent(@ModelAttribute("student") Student theStudent) throws SQLException {
        if(null != theStudent && theStudent.getId() > 0){
            studentService.updateStudent(theStudent);
        } else{


           studentService.saveStudent(theStudent);
            //securityService.autoLogin(theStudent.getFirstName(), theStudent.getConfirmPassword());
        }
        return "redirect:/";
    }


    @RequestMapping(value ="delete", method = RequestMethod.GET)
    public String delete(@RequestParam("studentId") int theId) {
        studentService.deleteStudent(theId);
        return "redirect:/";
    }
}

