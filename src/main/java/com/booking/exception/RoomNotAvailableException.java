package com.booking.exception;

import com.booking.constants.BookingConstants;

public class RoomNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = -7982046078947578207L;

	public RoomNotAvailableException() {
        super(BookingConstants.BOOKING_NOT_AVALIABLE_EXCEPTION);
    }
	
}