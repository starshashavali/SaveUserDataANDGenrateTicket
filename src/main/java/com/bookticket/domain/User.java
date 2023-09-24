package com.bookticket.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "User_Tbl")
public class User {
	@Id
	@GeneratedValue
	private Integer id;

	private String name;

	private String email;
	
	private Integer otp;

	private String phno;

	private String sourceLocation;

	private String destination;

	private Double ticketPrice;

	private Integer seatNo;
	@CreationTimestamp
	@Column(name = "created_at")
	private LocalDate createdAt;

}
