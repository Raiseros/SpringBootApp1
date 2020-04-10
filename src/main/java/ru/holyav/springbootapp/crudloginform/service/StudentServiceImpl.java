package ru.holyav.springbootapp.crudloginform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.holyav.springbootapp.crudloginform.entity.Role;
import ru.holyav.springbootapp.crudloginform.entity.Student;
import ru.holyav.springbootapp.crudloginform.repository.RoleRepository;
import ru.holyav.springbootapp.crudloginform.repository.StudentRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class StudentServiceImpl implements StudentService {


   @Autowired
    private RoleRepository roleRepository;



    @Autowired
    private StudentRepository studentRepository;




    @Override
    public List<Student> getStudents() {
      return studentRepository.findAll();
}

    @Override
    public void saveStudent(Student theStudent) throws SQLException {
       Set<Role> roles = new HashSet<>();
        Role role = new Role();
        String str = theStudent.getFirstName();


        if (str.equalsIgnoreCase("ADMIN")) {
            role.setRoleName("ADMIN");
            roles.add(role);
            theStudent.setRoles(roles);
        } else {
            role.setRoleName("STUDENT");
            roles.add(role);
            theStudent.setRoles(roles);
        }
        List<Role> dbRoles = roleRepository.findByRoleName(role.getRoleName());
        if (dbRoles.isEmpty()) {
            Role savedRole = roleRepository.save(role);
            role.setId(savedRole.getId());
        } else {
            Role role1 = dbRoles.get(0);
            role.setId(role1.getId());
        }

        List<Role> checkRoles = roleRepository.findByRoleName(role.getRoleName());
        if (checkRoles.size() == 0) {

            roleRepository.save(role);
        }


        studentRepository.save(theStudent);

    }

    @Override
    public void updateStudent(Student theStudent) {
    //  studentRepository.save(theStudent);
        studentRepository.updateStudent(theStudent.getFirstName(), theStudent.getLastName(), theStudent.getAge(),
                theStudent.getPassword(), theStudent.getId());

    }

    @Override
    public Student getStudent(long theId) {
       Optional<Student> result = studentRepository.findById(theId);

        Student theStudent = null;

        if(result.isPresent()){
            theStudent = result.get();

        } else{
            throw new RuntimeException("Did not find student id - " + theId);
        }

       return theStudent;


    }

    @Override
    public void deleteStudent(long theId) {

      studentRepository.deleteById(theId);
    }

    @Override
    public Student findByUserName(String firstName) {

        return studentRepository.findStudentByFirstName(firstName);
    }


    @Override
    public String findByUserRole(String firstName) {
        Student student = studentRepository.findStudentByFirstName(firstName);
        Role role = roleRepository.findRoleByStudents(student);
        return  role.getRoleName();
    }
}
