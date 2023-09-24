package com.bookticket.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDto {

	@NotBlank(message = "Name is required")
	@Pattern(regexp = "^[A-Za-z].*", message = "Name must start with alphabets")
	@Size(max = 255, message = "Name must not exceed 255 characters")
	private String name;

	@Email(message = "Invalid email format")
	private String email;

	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phno;

	@NotBlank(message = "From location is required")
	private String sourceLocation;

	@NotBlank(message = "To location is required")
	private String destination;

	@NotNull(message = "Seat number cannot be null")
	@PositiveOrZero(message = "Seat number must be non-negative")
	private Integer seatNo;

	@Positive(message = "Ticket price must be a positive number")
	private Double ticketPrice;



}
