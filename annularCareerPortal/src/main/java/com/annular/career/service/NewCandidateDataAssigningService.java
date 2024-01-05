package com.annular.career.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.annular.career.model.Candidate;
import com.annular.career.repository.CandidateRepository;

@Service
public class NewCandidateDataAssigningService implements CandidateService {

	@Autowired
	CandidateRepository canRepo;

	public Candidate dataAssigning(Candidate can, String fname, String mname, String lname, String phoneno,
			String email, String eq, String ks, String position, String experience, String passout, String cctc,
			String ectc, String np, MultipartFile resume) throws IOException {

		can.setCctc(cctc);
		can.setEctc(ectc);
		can.setEducationalQual(eq);
		can.setEmail(email);
		can.setExperience(experience);
		can.setFilename(resume.getOriginalFilename());
		can.setFileType(resume.getContentType());
		can.setFirstName(fname);
		can.setKeySkills(ks);
		can.setLastName(lname);
		can.setMiddleName(mname);
		can.setNoticePeriod(np);
		can.setPassout(passout);
		can.setPhoneNo(phoneno);
		can.setPosition(position);
		can.setResume(resume.getBytes());

		if (experience.equals("0")) {
			can.setCctc("NA");
			can.setEctc("NA");
			can.setNoticePeriod("NA");
		} else {
			can.setPassout("Experienced");
		}
		can.setActive(true);
		return can;
	}

}
