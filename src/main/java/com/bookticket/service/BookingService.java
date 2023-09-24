package com.bookticket.service;

import com.bookticket.dto.UserDto;

public interface BookingService {

	public String saveUser(UserDto userDto);
	
	public String verifyOTP(Integer otp) throws Exception;
	


}
