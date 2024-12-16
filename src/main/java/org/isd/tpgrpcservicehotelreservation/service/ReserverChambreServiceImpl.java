package org.isd.tpgrpcservicehotelreservation.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.isd.stubs.HotelServiceReserverGrpc;
import org.isd.stubs.Reserverchambre;
import org.isd.tpgrpcservicehotelreservation.config.ReservationResponseDTO;
import org.isd.tpgrpcservicehotelreservation.config.ServiceMapper;
import org.isd.tpgrpcservicehotelreservation.entities.*;
import org.isd.tpgrpcservicehotelreservation.repository.ChambreRepository;
import org.isd.tpgrpcservicehotelreservation.repository.ClientRepository;
import org.isd.tpgrpcservicehotelreservation.repository.OffreRepository;
import org.isd.tpgrpcservicehotelreservation.repository.ReservationRepository;
import org.isd.tpgrpcservicehotelreservation.utils.AuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@GrpcService
public class ReserverChambreServiceImpl extends HotelServiceReserverGrpc.HotelServiceReserverImplBase {
    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private AuthorizationUtil authorizationUtil;
    @Autowired
    private ServiceMapper serviceMapper;

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public void effectuerReservation(Reserverchambre.ReserverRequest request, StreamObserver<Reserverchambre.ReserverResponse> responseObserver) {
        // Valider les informations d'identification
        ReservationResponseDTO responseDTO = new ReservationResponseDTO();
        // Récupérer l'objet Login depuis le contexte
        Login login = authorizationUtil.LOGIN_CONTEXT_KEY.get();
        Agence agence = login.getAgence();

        Optional<Offre> offreOpt = offreRepository.findById(request.getIdOffre());
        if (offreOpt.isEmpty()) {
            responseDTO.setReservationID("");
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Offre introuvable");
        } else {
            Offre offre = offreOpt.get();
            if (offre.isUsed()) {
                responseDTO.setReservationID("");
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Cette offre est expiré");
            } else {
                Client client = clientRepository.findByEmail(request.getEmail()).orElseGet(() -> {
                    Client newClient = new Client(request.getPrenom(), offre.getChambre().getId().toString(), request.getPrenom());
                    clientRepository.save(newClient);
                    return newClient;
                });

                Chambre chambre = entityManager.merge(offre.getChambre());

                Reservation reservation = new Reservation(client, agence, chambre, new Date(), offre.getDateFin());
                reservationRepository.save(reservation);


                client.getReservations().add(reservation);
                clientRepository.save(client);

                // Marquer l'offre comme utilisée
                offre.setUsed(true);
                offreRepository.save(offre);

                if (reservation != null) {
                    responseDTO.setReservationID(reservation.getId().toString());
                    responseDTO.setSuccess(true);
                    responseDTO.setMessage("Réservation confirmée");
                }
            }
        }
        Reserverchambre.ReservationResponse reservationResponse = serviceMapper.fromReservationResponseDto(responseDTO);
        Reserverchambre.ReserverResponse response = Reserverchambre.ReserverResponse.newBuilder()
                .setReservationReservation(reservationResponse)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

