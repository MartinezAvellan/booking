package com.booking.constants;

public class BookingConstants {
	
	public static final String BASE_URL = "/booking/v1";
	public static final String CANCEL_MESSAGE = "Booking deleted!";
	public static final String ID_NOT_NULL = "Id cannot be null!";
	public static final String INVALID_ID = "Invalid booking id!";
	public static final String START_DATE_NOT_NULL = "Start date cannot be null!";
	public static final String END_DATE_NOT_NULL = "End date cannot be null!";
	public static final String BOOKING_NOT_CREATED_EXCEPTION = "Your booking can’t be longer more than 3 days and can’t be reserved a room more than 30 days!";
	public static final String BOOKING_NOT_FOUND_EXCEPTION = "Your booking could not be found!";
	public static final String BOOKING_NOT_AVALIABLE_EXCEPTION = "This room is not available for another booking!";
	
}