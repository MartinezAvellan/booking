package com.booking.controller;

import com.booking.dto.BookingResponse;
import com.booking.constants.BookingConstants;
import com.booking.dto.BookingRequest;
import com.booking.service.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping(BookingConstants.BASE_URL)
public class BookingController {

    @Autowired
    private BookingFacade bookingFacade;

    @PostMapping("/create")
    public ResponseEntity<BookingResponse> createBooking(final @RequestBody @Valid BookingRequest bookingRequest) {
        return new ResponseEntity<>(bookingFacade.createBooking(bookingRequest), HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<BookingResponse> checkBookingAvailability() {
        return new ResponseEntity<>(bookingFacade.checkBookingAvailability(), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<BookingResponse> updateBooking(final @RequestBody @Valid BookingRequest bookingRequest) {
        return new ResponseEntity<>(bookingFacade.updateBooking(bookingRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> cancelBooking(final @PathVariable("id") @Min(value = 1, message = BookingConstants.INVALID_ID) Long id) {
        bookingFacade.deleteBookingById(id);
        return new ResponseEntity<>(BookingConstants.CANCEL_MESSAGE, HttpStatus.OK);
    }
    
}