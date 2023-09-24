package com.bookticket.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookticket.dto.UserDto;
import com.bookticket.service.BookingService;

@RestController
public class BookingRestController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/save")
	public ResponseEntity<?> bookTicket( @Validated @RequestBody UserDto userDto) {
	 String saveUser = bookingService.saveUser(userDto);
		return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
	}
	@GetMapping("/verify/{otp}")
	public ResponseEntity<?> Verify( @Validated @PathVariable Integer  otp) throws Exception {
	 String verifyOTP = bookingService.verifyOTP(otp);
		return new ResponseEntity<>(verifyOTP, HttpStatus.OK);
	}
}
