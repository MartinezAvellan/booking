package com.booking.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 5043917324495455606L;
	
	private UUID uuid;
    private int statusCode;
    private Date timestamp;
    private String message;
    
}