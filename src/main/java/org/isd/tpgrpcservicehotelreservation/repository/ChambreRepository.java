package org.isd.tpgrpcservicehotelreservation.repository;

import org.isd.tpgrpcservicehotelreservation.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChambreRepository extends JpaRepository<Chambre, Integer> {

}
