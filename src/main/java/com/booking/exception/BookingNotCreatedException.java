package com.booking.exception;

import com.booking.constants.BookingConstants;

public class BookingNotCreatedException extends RuntimeException {

	private static final long serialVersionUID = 1297594329761108115L;

	public BookingNotCreatedException() {
        super(BookingConstants.BOOKING_NOT_CREATED_EXCEPTION);
    }
	
}