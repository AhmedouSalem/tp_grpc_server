package org.isd.tpgrpcservicehotelreservation.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.isd.stubs.Consulteroffre;
import org.isd.stubs.HotelServiceConsulterGrpc;
import org.isd.tpgrpcservicehotelreservation.config.ServiceMapper;
import org.isd.tpgrpcservicehotelreservation.entities.Agence;
import org.isd.tpgrpcservicehotelreservation.entities.Login;
import org.isd.tpgrpcservicehotelreservation.entities.Offre;
import org.isd.tpgrpcservicehotelreservation.config.ServiceMapper;
import org.isd.tpgrpcservicehotelreservation.repository.OffreRepository;
import org.isd.tpgrpcservicehotelreservation.utils.AuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@GrpcService
public class ConsulterOffreServiceImpl extends HotelServiceConsulterGrpc.HotelServiceConsulterImplBase {
    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    ServiceMapper serviceMapper;

    @Autowired
    AuthorizationUtil authorizationUtil;

    @Override
    public void consulterDisponibilites(Consulteroffre.ConsultRequest request, StreamObserver<Consulteroffre.ConsultResponse> responseObserver) {
        // Récupérer l'objet Login depuis le contexte
        Login login = authorizationUtil.LOGIN_CONTEXT_KEY.get();

        System.out.println("Agence authentifiée : " + login.getIdentifiant());

        if (!login.getIdentifiant().isEmpty()) {
            Agence agence = login.getAgence();
            // Convertir string en Date
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);

            String dateDebutString = request.getDateDebut();
            String dateFinString = request.getDateFin();
            Date dateDebut;
            Date dateFin;
            try {
                dateDebut = formatter.parse(dateDebutString);
                dateFin = formatter.parse(dateFinString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            // Récupérer les offres pour cette agence, selon les critères
            List<Offre> offres = offreRepository.findOffresByAgenceIdAndDates(agence.getId(), dateDebut, dateFin,
                    request.getNbPersonne());
            List<Consulteroffre.OffreDiponible> offreDiponibleList = offres.stream().map(offre -> serviceMapper.fromOffre(offre)).collect(Collectors.toList());
            Consulteroffre.ConsultResponse listConsultResponse = Consulteroffre.ConsultResponse.newBuilder().addAllOffres(offreDiponibleList).build();
            responseObserver.onNext(listConsultResponse);
            responseObserver.onCompleted();
        }
    }
}
