package org.isd.tpgrpcservicehotelreservation.repository;

import java.util.Optional;

import org.isd.tpgrpcservicehotelreservation.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<Login, Integer>{
    Optional<Login> findByIdentifiantAndPassword(String identifiant, String password);
}
