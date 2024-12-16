package org.isd.tpgrpcservicehotelreservation.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Data
public class ReservationResponseDTO {
    private boolean success;
    private String message;
    private String reservationID;
    public ReservationResponseDTO(boolean success, String message, String reservationID) {
        this.success = success;
        this.message = message;
        this.reservationID = reservationID;
    }
    public ReservationResponseDTO() {}
}
