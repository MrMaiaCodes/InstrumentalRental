package br.com.instrumental_rental.handlers;

import br.com.instrumental_rental.controller.dto.responses.errors.ErrorResponseDTO;
import br.com.instrumental_rental.controller.dto.responses.errors.ErrorSpecificationDTO;
import br.com.instrumental_rental.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> customerNotFoundExceptionHandler(
            CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage(exception.getMessage()
                                ).build()
                        ).build()
                );
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> personNotFoundExceptionHandler(
            PersonNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage(exception.getMessage()
                                ).build()
                        ).build()
                );
    }


    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> contactNotFoundExceptionHandler(
            ContactNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage(exception.getMessage()
                                ).build()

                        ).build());
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> storeNotFoundExceptionHandler(
            StoreNotFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage(exception.getMessage()
                        ).build()
                ).build());
    }

    @ExceptionHandler(TheAddressNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> TheAddressNotFoundExceptionHandler(
            TheAddressNotFoundException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage(exception.getMessage()
                                ).build()
                        ).build()
                );
    }

    @ExceptionHandler(AttendantNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> attendantNotFoundExceptionHandler(
            AttendantNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage(exception.getMessage()
                                ).build()
                        ).build()
                );
    }

    @ExceptionHandler(InstrumentNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> instrumentNotFoundExceptionHandler(
            InstrumentNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage(exception.getMessage()
                                ).build()
                        ).build()
                );
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> rentalNotFoundExceptionHandler(
            RentalNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage(exception.getMessage()
                                ).build()
                        ).build()
                );
    }

    @ExceptionHandler(WithdrawalGreaterThanBalanceException.class)
    public ResponseEntity<ErrorResponseDTO> WithdrawalGreaterThanBalanceExceptionHandler(
            WithdrawalGreaterThanBalanceException exception) {
        log.info("withdrawal amount greater than balance available");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage("Withdrawal amount greater than balance available")
                                .build()
                        ).build()
                );
    }

    @ExceptionHandler(EndDateNotAfterStartDateException.class)
    public ResponseEntity<ErrorResponseDTO> EndDateNotAfterStartDateExceptionHandler(
            EndDateNotAfterStartDateException exception) {
        log.info("the end date is not after the start date");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.builder()
                        .data(ErrorSpecificationDTO.builder()
                                .errorCode("400")
                                .errorMessage("The end date is not after the start date")
                                .build())
                        .build()
                );
    }
}
