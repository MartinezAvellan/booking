package com.booking.exception.handler;

import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booking.dto.ErrorMessage;
import com.booking.exception.BookingNotCreatedException;
import com.booking.exception.BookingNotFoundException;
import com.booking.exception.RoomNotAvailableException;

@ResponseBody
@ControllerAdvice
public class InteceptorExceptionHandler {

    @ExceptionHandler(BookingNotCreatedException.class)
    public ResponseEntity<?> bookingNotCreatedException(BookingNotCreatedException ex) {
        final ErrorMessage message = new ErrorMessage(UUID.randomUUID(), HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<?> bookingNotFoundException(BookingNotFoundException ex) {
        final ErrorMessage message = new ErrorMessage(UUID.randomUUID(), HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<?> roomNotAvailableException(RoomNotAvailableException ex) {
        final ErrorMessage message = new ErrorMessage(UUID.randomUUID(), HttpStatus.BAD_REQUEST.value(), new Date(), ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}