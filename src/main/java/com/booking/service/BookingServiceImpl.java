package com.booking.service;

import com.booking.entity.BookingEntity;
import com.booking.exception.BookingNotFoundException;
import com.booking.exception.RoomNotAvailableException;
import com.booking.mapper.BookingMapper;
import com.booking.repository.BookingRepository;
import com.booking.utils.BookingUtils;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public BookingUtils createBooking(final BookingUtils bookingUtils) {
        if (isThereAnyBooking()) {
            throw new RoomNotAvailableException();
        }
        final BookingEntity bookingEntity = Mappers.getMapper(BookingMapper.class).mapBookingToBookingEntity(bookingUtils);
        return BookingMapper.INSTANCE.mapBookingEntityToBooking(bookingRepository.save(bookingEntity));
    }

    @Override
    public BookingUtils checkBookingAvailability() {
        if (!isThereAnyBooking()) {
            throw new BookingNotFoundException();
        }
        return BookingMapper.INSTANCE.mapBookingEntityToBooking(bookingRepository.findAll().get(0));
    }

    @Override
    public BookingUtils updateBooking(final BookingUtils bookingUtils) {
        if (!isThereAnyBooking()) {
            throw new BookingNotFoundException();
        }
        final BookingEntity bookingEntity = Mappers.getMapper(BookingMapper.class).mapBookingToBookingEntity(bookingUtils);
        return BookingMapper.INSTANCE.mapBookingEntityToBooking(bookingRepository.save(bookingEntity));
    }

    @Override
    public void cancelBookingById(final Long id) {
        if (!isThereAnyBooking()) {
            throw new BookingNotFoundException();
        }
        bookingRepository.deleteById(id);
    }

    private boolean isThereAnyBooking() {
        return bookingRepository.count() > 0;
    }
    
}