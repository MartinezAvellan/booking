package com.booking.dto;

import com.booking.constants.BookingConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest implements Serializable {

    private static final long serialVersionUID = -6207032289614643795L;

	@NotNull(message = BookingConstants.ID_NOT_NULL)
    @JsonProperty("id")
    private Long id;

    @NotNull(message = BookingConstants.START_DATE_NOT_NULL)
    @JsonProperty("start_date")
    private String startDate;

    @NotNull(message = BookingConstants.END_DATE_NOT_NULL)
    @JsonProperty("end_date")
    private String endDate;
    
}