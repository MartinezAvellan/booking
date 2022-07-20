package com.booking.entity;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "TB_BOOKING")
public class BookingEntity implements Serializable {

    private static final long serialVersionUID = 318090229483121912L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @Column(name = "STATUS")
    private String status;
    
}