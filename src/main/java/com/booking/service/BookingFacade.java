package com.booking.service;

import com.booking.dto.BookingResponse;
import com.booking.dto.BookingRequest;
import com.booking.exception.BookingNotCreatedException;
import com.booking.mapper.BookingMapper;
import com.booking.utils.BookingUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingFacade {

    @Autowired
    private BookingService bookingService;

    public BookingResponse createBooking(final BookingRequest bookingRequest) {
        final BookingUtils bookingUtils = BookingMapper.INSTANCE.mapBookingRequestDTOToBooking(bookingRequest);
        if (!bookingUtils.bookingCanBeCompleted()) {
            throw new BookingNotCreatedException();
        }
        return BookingMapper.INSTANCE.mapBookingToBookingResponseDTO(bookingService.createBooking(bookingUtils));
    }

    public BookingResponse checkBookingAvailability() {
        return BookingMapper.INSTANCE.mapBookingToBookingResponseDTO(bookingService.checkBookingAvailability());
    }

    public BookingResponse updateBooking(final BookingRequest bookingRequest) {
        final BookingUtils bookingUtils = BookingMapper.INSTANCE.mapBookingRequestDTOToBooking(bookingRequest);
        if (!bookingUtils.bookingCanBeCompleted()) {
            throw new BookingNotCreatedException();
        }
        return BookingMapper.INSTANCE.mapBookingToBookingResponseDTO(bookingService.updateBooking(bookingUtils));
    }

    public void deleteBookingById(final Long id) {
        bookingService.cancelBookingById(id);
    }
    
}