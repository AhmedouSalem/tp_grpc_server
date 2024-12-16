package org.isd.tpgrpcservicehotelreservation.repository;

import org.isd.tpgrpcservicehotelreservation.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaiementRepository extends JpaRepository<Paiement, Integer> {

}
