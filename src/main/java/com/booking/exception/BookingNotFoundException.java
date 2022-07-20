package com.booking.exception;

import com.booking.constants.BookingConstants;

public class BookingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6789589891764940897L;

	public BookingNotFoundException() {
        super(BookingConstants.BOOKING_NOT_FOUND_EXCEPTION);
    }
	
}