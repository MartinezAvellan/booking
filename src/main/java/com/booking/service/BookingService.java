package com.booking.service;

import org.springframework.stereotype.Service;

import com.booking.utils.BookingUtils;

@Service
public interface BookingService {
	void cancelBookingById(final Long id);
	BookingUtils checkBookingAvailability();
    BookingUtils createBooking(final BookingUtils bookingUtils);
    BookingUtils updateBooking(final BookingUtils bookingUtils);
}