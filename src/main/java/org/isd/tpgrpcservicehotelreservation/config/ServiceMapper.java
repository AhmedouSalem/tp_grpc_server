package org.isd.tpgrpcservicehotelreservation.config;

import org.isd.stubs.Consulteroffre;
import org.isd.stubs.Reserverchambre;
import org.isd.tpgrpcservicehotelreservation.entities.Offre;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class ServiceMapper {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd", Locale.ENGLISH);
    public Consulteroffre.OffreDiponible fromOffre(Offre offre) {
        Consulteroffre.OffreDiponible offreDiponibleStub = Consulteroffre.OffreDiponible.newBuilder()
                .setIdOffre(offre.getId())
                .setDateDisponibilite(formatter.format(offre.getDateDebut()))
                .setTypeChambre(offre.getChambre().getNbLit())
                .setPrix(offre.getTarifAgence() * 1.10)
                .setImageUrl(offre.getChambre().getImageUrl())
                .build();
        return offreDiponibleStub;
    }

    public Reserverchambre.ReservationResponse fromReservationResponseDto(ReservationResponseDTO responseDto) {
        Reserverchambre.ReservationResponse reservationResponseStub = Reserverchambre.ReservationResponse.newBuilder()
                .setSuccess(responseDto.isSuccess())
                .setMessage(responseDto.getMessage())
                .setReservationReference(responseDto.getReservationID())
                .build();
        return reservationResponseStub;
    }
}