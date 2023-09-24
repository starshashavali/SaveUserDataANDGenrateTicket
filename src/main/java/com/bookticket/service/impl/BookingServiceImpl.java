package com.bookticket.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookticket.domain.User;
import com.bookticket.dto.UserDto;
import com.bookticket.exception.DuplicateEmailException;
import com.bookticket.exception.DuplicateSeatNoException;
import com.bookticket.exception.InvalidOTPException;
import com.bookticket.repo.UserRepo;
import com.bookticket.service.BookingService;
import com.bookticket.utils.EmailUtils;
import com.bookticket.utils.OTPUtils;
import com.bookticket.utils.PdfGenarateUtils;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private PdfGenarateUtils pdfutils;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private OTPUtils otpUtils;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String saveUser(UserDto userDto) {
		Optional<User> existingUser = userRepo.findByEmail(userDto.getEmail());
		if (existingUser.isPresent()) {
			throw new DuplicateEmailException("Duplicate Email...");
		}
		Optional<User> findBySeatNo = userRepo.findBySeatNo(userDto.getSeatNo());
		if(findBySeatNo.isPresent()) {
			throw new DuplicateSeatNoException("Duplicate seat...");

		}

		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		String generateRandomOtp = otpUtils.generateRandomOtp();
		int otp = Integer.parseInt(generateRandomOtp);
		user.setOtp(otp);
		userRepo.save(user);
		String subject = "Thank you for booking, please enter your OTP to proceed further!!!";
		String text = "<h4>Please use the below OTP to proceed further... </h4> " + "<p>Your OTP is</p>" + otp;
		emailUtils.sendEmail(userDto.getEmail(), subject, text);

		return "Successfully saved...";
	}

	@Override
	public String verifyOTP(Integer otp) throws Exception {
	    User entity = userRepo.findByOtp(otp);

	    if (entity != null && entity.getOtp().equals(otp)) {
	        // Generate the PDF
	        byte[] generatePdf = pdfutils.generatePdf(entity);

	        // Send the email with the PDF attachment
	        String subject = "Your ticket is booked";
	        String text = "Please find your ticket details attached.";
	        String attachmentName = "ticket.pdf"; // Specify the attachment name here
	        emailUtils.sendEmailWithAttachment(entity.getEmail(), subject, text, generatePdf, attachmentName);

	        return "Please check your mail for the ticket details.";
	    }

	    throw new InvalidOTPException("Invalid OTP...");
	}
}
