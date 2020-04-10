package ru.holyav.springbootapp.crudloginform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.holyav.springbootapp.crudloginform.entity.Role;
import ru.holyav.springbootapp.crudloginform.entity.Student;
import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByRoleName(String roleName);

    Role findRoleByStudents(Student student);
}

