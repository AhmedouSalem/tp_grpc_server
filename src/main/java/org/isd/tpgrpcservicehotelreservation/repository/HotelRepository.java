package org.isd.tpgrpcservicehotelreservation.repository;

import org.isd.tpgrpcservicehotelreservation.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HotelRepository extends JpaRepository<Hotel, Integer>{

}
