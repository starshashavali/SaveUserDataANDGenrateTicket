package com.bookticket.utils;

import java.util.Random;

import org.springframework.stereotype.Component;
@Component
public class OTPUtils {
	
	    public String generateRandomOtp() {
	        // Define the length of the OTP
	        int otpLength = 6;
	        
	        // Create a StringBuilder to store the OTP
	        StringBuilder otp = new StringBuilder(otpLength);
	        
	        // Create a random number generator
	        Random random = new Random();
	        
	        // Generate each digit of the OTP
	        for (int i = 0; i < otpLength; i++) {
	            int digit = random.nextInt(10); // Generate a random digit (0-9)
	            otp.append(digit); // Append the digit to the OTP
	        }
	        
	        return otp.toString();
	    }
	}



