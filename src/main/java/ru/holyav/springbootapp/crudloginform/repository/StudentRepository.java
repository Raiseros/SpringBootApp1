package ru.holyav.springbootapp.crudloginform.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.holyav.springbootapp.crudloginform.entity.Student;




@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Student SET first_name = :firstName , last_name = :lastName , age = :age ," +
            " password = :password WHERE id = :id")
    void updateStudent(String firstName, String lastName, String age, String password, long id);

    Student findStudentByFirstName(String firstName);

}
