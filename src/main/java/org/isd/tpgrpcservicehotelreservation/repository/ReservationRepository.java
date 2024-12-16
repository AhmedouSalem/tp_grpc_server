package org.isd.tpgrpcservicehotelreservation.repository;

import org.isd.tpgrpcservicehotelreservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

}
