package bg.softuni.mobilele.repository;


import bg.softuni.mobilele.model.entities.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

}