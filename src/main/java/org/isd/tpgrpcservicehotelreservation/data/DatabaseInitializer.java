package org.isd.tpgrpcservicehotelreservation.data;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.isd.tpgrpcservicehotelreservation.entities.*;
import org.isd.tpgrpcservicehotelreservation.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class DatabaseInitializer {

    private final Logger logger = LoggerFactory.getLogger(DatabaseInitializer.class);

    @Bean
    @Transactional
    public CommandLineRunner initDatabase(
        AdresseRepository adresseRepository,
        HotelRepository hotelRepository,
        ChambreRepository chambreRepository,
        AgenceRepository agenceRepository,
        LoginRepository loginRepository,
        ClientRepository clientRepository,
        ReservationRepository reservationRepository,
        PaiementRepository paiementRepository,
        OffreRepository offreRepository) {

        return args -> {
            // Adresse
            logger.info("Preloading Adresse");
            Adresse adresse1 = adresseRepository.save(new Adresse("France", "Paris", "Rue de Rivoli", "1", null, 75001, 48, 2));
            Adresse adresse2 = adresseRepository.save(new Adresse("France", "Lyon", "Rue Victor Hugo", "12", null, 69002, 45, 4));

            // Hotel
            logger.info("Preloading Hotel");
            Hotel hotel1 = hotelRepository.save(new Hotel("Blue Lagoon", 5, adresse1, new ArrayList<Chambre>(), new ArrayList<Agence>()));
            Hotel hotel2 = hotelRepository.save(new Hotel("Sunny Beach", 4, adresse2, new ArrayList<Chambre>(), new ArrayList<Agence>()));

            // Chambre
            logger.info("Preloading Chambre");
         // Hôtel persisté avant d'ajouter des chambres
            hotel1 = hotelRepository.save(hotel1);
            hotel2 = hotelRepository.save(hotel2);

         // Chambre associée après la persistance de l'hôtel
            Chambre chambre1 = new Chambre(1, 100.0, "https://static.cotemaison.fr/medias_10948/w_640,c_fill,g_north/une-tete-de-lit-continue_5605567.jpg");
            chambre1.setHotel(hotel1);

            Chambre chambre2 = new Chambre(2, 150.0, "https://cache.marieclaire.fr/data/photo/w1000_ci/1jv/bonnes-idees-a-copier-chambres-hotel.jpg");
            chambre2.setHotel(hotel1);
            
            Chambre chambre3 = new Chambre(1, 95.0, "https://www.createursdinterieur.com/static/88713184b6f1e828432cca7683d74d4e/ddced/decoration-interieur-chambre-hotel-3-etoiles.jpg");
            chambre3.setHotel(hotel2);
            chambreRepository.save(chambre3);

            Chambre chambre4 = new Chambre(2, 100.0, "https://resize-elle.ladmedia.fr/rcrop/638,,forcex/img/var/plain_site/storage/images/loisirs/sorties/hotels/plus-belle-chambre-d-hotel-du-monde/85547342-1-fre-FR/Ces-chambres-d-hotels-vont-vous-laisser-bouche-bee.jpg");
            chambre4.setHotel(hotel2);
            chambreRepository.save(chambre4);
        
            
            // Agence
            logger.info("Preloading Agence");
            Agence agence1 = agenceRepository.save(new Agence("AG001", "Sunny Travel", adresse1, null));
            Agence agence2 = agenceRepository.save(new Agence("AG002", "Dream Getaways", adresse2, null));
            
         // Ajout des agences aux hôtels
            hotel1.getAgences().add(agence1);
            hotel2.getAgences().add(agence2);

            // Ajout des hôtels aux agences
            agence1.getHotels().add(hotel1);
            agence2.getHotels().add(hotel2);

            // Sauvegarder les entités
            hotelRepository.save(hotel1);
            hotelRepository.save(hotel2);


            // Login
            logger.info("Preloading Login");
            loginRepository.save(new Login("sunny123", "password123", agence1));
            loginRepository.save(new Login("dream123", "password234", agence2));

            // Client
            logger.info("Preloading Client");
            Client client1 = clientRepository.save(new Client("Alice", "Smith", "alice.smith@example.com"));
            Client client2 = clientRepository.save(new Client("Bob", "Johnson", "bob.johnson@example.com"));
            
         // Créer une instance de Calendar
            Calendar calendar = Calendar.getInstance();

            // Définir une date spécifique
            calendar.set(Calendar.YEAR, 2024);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 18);
            calendar.set(Calendar.HOUR_OF_DAY, 14); // Format 24 heures
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            // Convertir en Date
            Date dateDebut = calendar.getTime();
            
         // Définir une date spécifique
            calendar.set(Calendar.YEAR, 2024);
            calendar.set(Calendar.MONTH, Calendar.DECEMBER);
            calendar.set(Calendar.DAY_OF_MONTH, 30);
            calendar.set(Calendar.HOUR_OF_DAY, 14); // Format 24 heures
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            
         // Convertir en Date
            Date dateFin = calendar.getTime();


            // Reservation
            logger.info("Preloading Reservation");
            Reservation reservation1 = reservationRepository.save(new Reservation(client1, agence1, chambre1, dateDebut, dateFin));
            Reservation reservation2 = reservationRepository.save(new Reservation(client2, agence2, chambre2, dateDebut, dateFin));

            // Paiement
            logger.info("Preloading Paiement");
            paiementRepository.save(new Paiement(500.0, "Carte", new Date(), reservation1));
            paiementRepository.save(new Paiement(750.0, "Espèces", new Date(), reservation2));

            // Offre
            logger.info("Preloading Offre");
            Offre offre1 = offreRepository.save(new Offre(90.0, dateDebut, dateFin, hotel1, agence1, chambre1));
            Offre offre2 = offreRepository.save(new Offre(140.0, dateDebut, dateFin, hotel1, agence2, chambre2));
            offreRepository.save(new Offre(90.0, dateDebut, dateFin, hotel2, agence1, chambre3));
            offreRepository.save(new Offre(140.0, dateDebut, dateFin, hotel2, agence2, chambre4));
            offre1.setUsed(true);
            offre2.setUsed(true);
            offreRepository.save(offre1);
            offreRepository.save(offre2);
        };
    }
}
