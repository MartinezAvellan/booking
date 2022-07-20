package com.booking.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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

import com.booking.entity.BookingEntity;
import com.booking.exception.BookingNotFoundException;
import com.booking.exception.RoomNotAvailableException;
import com.booking.mapper.BookingMapper;
import com.booking.repository.BookingRepository;
import com.booking.utils.BookingUtils;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class BookingServiceImplTest {

	@InjectMocks
	private BookingService bookingService = new BookingServiceImpl();

	@Mock
	private BookingRepository bookingRepository;

	@Spy
	private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

	private BookingUtils bookingUtils;
	private BookingEntity bookingEntity;
	private List<BookingEntity> bookingEntities;

	@BeforeEach
	public void init() {
		bookingUtils = new BookingUtils();
		bookingEntity = new BookingEntity();

		bookingUtils.setId(1L);
		bookingUtils.setStartDate(LocalDate.now());
		bookingUtils.setEndDate(LocalDate.now().plusDays(1));

		bookingEntity.setId(1L);
		bookingEntity.setStartDate(LocalDate.now());
		bookingEntity.setEndDate(LocalDate.now().plusDays(1));

		bookingEntities = Arrays.asList(bookingEntity);
	}

	@Test
	public void shouldCreateABooking() {
		when(bookingRepository.count()).thenReturn(0L);
		when(bookingMapper.mapBookingToBookingEntity(any())).thenReturn(bookingEntity);
		when(bookingRepository.save(any())).thenReturn(bookingEntity);
		when(bookingMapper.mapBookingEntityToBooking(any())).thenReturn(bookingUtils);

		BookingUtils BookingResult = bookingService.createBooking(bookingUtils);

		verify(bookingRepository, Mockito.times(1)).save(bookingEntity);
		assertNotNull(BookingResult);
		assertEquals(Long.valueOf(1L), bookingEntity.getId());
		assertEquals(LocalDate.now(), bookingEntity.getStartDate());
		assertEquals(LocalDate.now().plusDays(1), bookingEntity.getEndDate());
	}

	@Test
	public void shouldThrowARoomNotAvailableExceptionWhenTryingToCreateABooking() {
		when(bookingRepository.count()).thenReturn(1L);

		Assertions.assertThrows(RoomNotAvailableException.class, () -> bookingService.createBooking(bookingUtils));

		verify(bookingRepository, Mockito.times(0)).save(bookingEntity);
	}

	@Test
	public void shouldCheckBookingAvailability() {
		when(bookingRepository.count()).thenReturn(1L);
		when(bookingRepository.findAll()).thenReturn(bookingEntities);
		when(bookingMapper.mapBookingEntityToBooking(any())).thenReturn(bookingUtils);

		BookingUtils bookingUtils = bookingService.checkBookingAvailability();

		verify(bookingRepository, Mockito.times(1)).findAll();
		assertNotNull(bookingUtils);
		assertEquals(Long.valueOf(1L), bookingEntities.get(0).getId());
		assertEquals(LocalDate.now(), bookingEntities.get(0).getStartDate());
		assertEquals(LocalDate.now().plusDays(1), bookingEntities.get(0).getEndDate());
	}

	@Test
	public void shouldThrowABookingNotFoundExceptionWhenTryingToCheckForBookingAvailability() {
		when(bookingRepository.count()).thenReturn(0L);

		Assertions.assertThrows(BookingNotFoundException.class, () -> bookingService.checkBookingAvailability());

		verify(bookingRepository, Mockito.times(0)).findAll();
	}

	@Test
	public void shouldUpdateABooking() {
		when(bookingRepository.count()).thenReturn(1L);
		when(bookingMapper.mapBookingToBookingEntity(any())).thenReturn(bookingEntity);
		when(bookingRepository.save(any())).thenReturn(bookingEntity);
		when(bookingMapper.mapBookingEntityToBooking(any())).thenReturn(bookingUtils);

		BookingUtils BookingResult = bookingService.updateBooking(bookingUtils);

		verify(bookingRepository, Mockito.times(1)).save(bookingEntity);
		assertNotNull(BookingResult);
		assertEquals(Long.valueOf(1L), bookingEntity.getId());
		assertEquals(LocalDate.now(), bookingEntity.getStartDate());
		assertEquals(LocalDate.now().plusDays(1), bookingEntity.getEndDate());
	}

	@Test
	public void shouldThrowABookingNotFoundExceptionWhenTryingToUpdateABooking() {
		when(bookingRepository.count()).thenReturn(0L);

		Assertions.assertThrows(BookingNotFoundException.class, () -> bookingService.updateBooking(bookingUtils));

		verify(bookingRepository, Mockito.times(0)).save(bookingEntity);
	}

	@Test
	public void shouldCancelABooking() {
		when(bookingRepository.count()).thenReturn(1L);
		doNothing().when(bookingRepository).deleteById(any());

		bookingService.cancelBookingById(1L);

		verify(bookingRepository, Mockito.times(1)).deleteById(1L);
	}

	@Test
	public void shouldThrowABookingNotFoundExceptionWhenTryingToCancelABooking() {
		when(bookingRepository.count()).thenReturn(0L);

		Assertions.assertThrows(BookingNotFoundException.class, () -> bookingService.cancelBookingById(1L));

		verify(bookingRepository, Mockito.times(0)).deleteById(1L);
	}
}