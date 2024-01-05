
package com.annular.career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.annular.career.model.Candidate;

@Service
public class EmailService {

	@Autowired
	JavaMailSender ms;

	public void EmailforNewCandidate(Candidate can) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("chinnasam01@gmail.com");
		mail.setTo(can.getEmail());
		mail.setSubject("Applied for the Role " + can.getPosition());
		mail.setText("Hello " + can.getFirstName() + ",\n\n\tYour Candidate id is : " + can.getCandId()
				+ ". You applied for the Role " + can.getPosition()
				+ " Successfully. We will get back to you Once your resume will be shortlisted.\n\nRegards,\nHR Team, Annular Technologies");
		ms.send(mail);
	}

	public void EmailforUpdatedCandidate(Candidate can) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("chinnasam01@gmail.com");
		mail.setTo(can.getEmail());
		mail.setSubject("Applied for the Role " + can.getPosition());
		mail.setText("Hello " + can.getFirstName() + ",\n\n\tYour Candidate id is : " + can.getCandId()
				+ ". You applied for the Role " + can.getPosition()
				+ " earlier. And now you have Changed your Email to : " + can.getEmail()
				+ ". We will get back to you Once your resume will be shortlisted.\n\nRegards,\nHR Team, Annular Technologies");
		ms.send(mail);
	}

}
