package cedeladela.hookabook.repository;


import cedeladela.hookabook.entity.HbUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HbUserRepository extends CrudRepository<HbUser, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    HbUser findByUsernameAndPassword(String username, String password);

}
