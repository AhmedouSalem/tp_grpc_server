package org.isd.tpgrpcservicehotelreservation.repository;

import java.util.Optional;

import org.isd.tpgrpcservicehotelreservation.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Integer> {

	Optional<Client> findByEmail(String email);

}
