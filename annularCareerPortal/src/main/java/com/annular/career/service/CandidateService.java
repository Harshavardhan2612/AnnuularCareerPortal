package com.annular.career.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.annular.career.model.Candidate;

public interface CandidateService {
	
	 

	public default Candidate dataAssigning(Candidate can, String fname, String mname, String lname, String phoneno,
			String email, String eq, String ks, String position, String experience, String passout, String cctc,
			String ectc, String np, MultipartFile resume) throws IOException {
		return can;
	};
}