package com.booking.mapper;

import com.booking.dto.BookingResponse;
import com.booking.dto.BookingRequest;
import com.booking.entity.BookingEntity;
import com.booking.utils.BookingUtils;
import org.mapstruct.ReportingPolicy;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);
    BookingEntity mapBookingToBookingEntity(final BookingUtils bookingUtils);
    BookingUtils mapBookingEntityToBooking(final BookingEntity bookingEntity);
    BookingResponse mapBookingToBookingResponseDTO(final BookingUtils bookingUtils);
    BookingUtils mapBookingRequestDTOToBooking(final BookingRequest bookingRequest);
    
}