package com.booking.service;

import com.booking.dto.BookingRequest;
import com.booking.dto.BookingResponse;
import com.booking.exception.BookingNotCreatedException;
import com.booking.mapper.BookingMapper;
import com.booking.utils.BookingUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookingFacadeTest {

	@InjectMocks
	private BookingFacade bookingFacade;

	@Mock
	private BookingService bookingService;

	@Spy
	private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

	private BookingUtils bookingUtils;
	private BookingRequest bookingRequest;
	private BookingResponse bookingResponse;

	@BeforeEach
	public void init() {
		bookingUtils = new BookingUtils();
		bookingRequest = new BookingRequest();
		bookingResponse = new BookingResponse();

		bookingUtils.setId(1L);
		bookingUtils.setStartDate(LocalDate.now());
		bookingUtils.setEndDate(LocalDate.now().plusDays(1));

		bookingRequest.setId(1L);
		bookingRequest.setStartDate(LocalDate.now().toString());
		bookingRequest.setEndDate(LocalDate.now().plusDays(1).toString());

		bookingResponse.setId(1L);
		bookingResponse.setStartDate(LocalDate.now().toString());
		bookingResponse.setEndDate(LocalDate.now().plusDays(1).toString());
	}

	@Test
	public void shouldCreateABookingWithValidDates() {
		when(bookingMapper.mapBookingRequestDTOToBooking(any())).thenReturn(bookingUtils);
		when(bookingService.createBooking(any())).thenReturn(bookingUtils);
		when(bookingMapper.mapBookingToBookingResponseDTO(any())).thenReturn(bookingResponse);

		BookingResponse BookingDTOResult = bookingFacade.createBooking(bookingRequest);

		verify(bookingService, Mockito.times(1)).createBooking(bookingUtils);
		assertNotNull(BookingDTOResult);
		assertEquals(Long.valueOf(1L), BookingDTOResult.getId());
		assertEquals(LocalDate.now().toString(), BookingDTOResult.getStartDate());
		assertEquals(LocalDate.now().plusDays(1).toString(), BookingDTOResult.getEndDate());
	}

	@Test
	public void shouldThrowABookingNotCreatedExceptionWhenTryingToCreateABookingWithInvalidDates() {
		bookingRequest.setEndDate(LocalDate.now().minusDays(3).toString());

		when(bookingMapper.mapBookingRequestDTOToBooking(any())).thenReturn(bookingUtils);
		when(bookingService.createBooking(any())).thenReturn(null);
		when(bookingMapper.mapBookingToBookingResponseDTO(any())).thenReturn(bookingResponse);

		Assertions.assertThrows(BookingNotCreatedException.class, () -> bookingFacade.createBooking(bookingRequest));

		verify(bookingService, Mockito.times(0)).createBooking(bookingUtils);
	}

	@Test
	public void shouldCheckBookingAvailability() {
		when(bookingMapper.mapBookingToBookingResponseDTO(any())).thenReturn(bookingResponse);
		when(bookingService.checkBookingAvailability()).thenReturn(bookingUtils);

		bookingFacade.checkBookingAvailability();

		verify(bookingService, Mockito.times(1)).checkBookingAvailability();
	}

	@Test
	public void shouldUpdateABookingWithValidDates() {
		when(bookingMapper.mapBookingRequestDTOToBooking(any())).thenReturn(bookingUtils);
		when(bookingService.updateBooking(any())).thenReturn(bookingUtils);
		when(bookingMapper.mapBookingToBookingResponseDTO(any())).thenReturn(bookingResponse);

		BookingResponse BookingDTOResult = bookingFacade.updateBooking(bookingRequest);

		verify(bookingService, Mockito.times(1)).updateBooking(bookingUtils);
		assertNotNull(BookingDTOResult);
		assertEquals(Long.valueOf(1L), BookingDTOResult.getId());
		assertEquals(LocalDate.now().toString(), BookingDTOResult.getStartDate());
		assertEquals(LocalDate.now().plusDays(1).toString(), BookingDTOResult.getEndDate());
	}

	@Test
	public void shouldThrowABookingNotCreatedExceptionWhenTryingToUpdateABookingWithInvalidDates() {
		bookingRequest.setEndDate(LocalDate.now().minusDays(3).toString());

		when(bookingMapper.mapBookingRequestDTOToBooking(any())).thenReturn(bookingUtils);
		when(bookingService.updateBooking(any())).thenReturn(null);
		when(bookingMapper.mapBookingToBookingResponseDTO(any())).thenReturn(bookingResponse);

		Assertions.assertThrows(BookingNotCreatedException.class, () -> bookingFacade.updateBooking(bookingRequest));

		verify(bookingService, Mockito.times(0)).updateBooking(bookingUtils);
	}

	@Test
	public void shouldCancelABooking() {
		doNothing().when(bookingService).cancelBookingById(any());

		bookingFacade.deleteBookingById(1L);

		verify(bookingService, Mockito.times(1)).cancelBookingById(1L);
	}
}