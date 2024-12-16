package org.isd.tpgrpcservicehotelreservation.repository;

import java.util.Date;
import java.util.List;

import org.isd.tpgrpcservicehotelreservation.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {
	@Query("SELECT o FROM Offre o WHERE o.agence.id = :agenceId AND o.isUsed = false AND o.dateDebut <= :dateFin " +
	           "AND o.dateFin >= :dateDebut AND o.chambre.nbLit >= :nombrePersonnes")
	    List<Offre> findOffresByAgenceIdAndDates(@Param("agenceId") Integer agenceId,
	                                             @Param("dateDebut") Date dateDebut,
	                                             @Param("dateFin") Date dateFin,
	                                             @Param("nombrePersonnes") int nombrePersonnes);

	List<Offre> findOffresByAgenceId(Integer agenceId);
	List<Offre> findByIsUsed(boolean used);
}
