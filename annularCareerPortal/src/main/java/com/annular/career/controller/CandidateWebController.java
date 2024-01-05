package com.annular.career.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.annular.career.exceptionhandlers.emailAlreadyExistException;
import com.annular.career.model.Candidate;
import com.annular.career.repository.CandidateRepository;
import com.annular.career.service.CandidateService;

@Controller
public class CandidateWebController {

	@Autowired
	CandidateRepository canRepo;

	@Autowired
	CandidateService service;

	@GetMapping("/")
	public String welcome() {
		return "annular";
	}

	@GetMapping("/apply")
	public String application(Model model) {
		Candidate candidate = new Candidate();
		model.addAttribute("candidate", candidate);
		return "application";
	}

	@PostMapping("/save")
	public ResponseEntity<String> insertCandidate(@RequestParam("firstName") String fname,
			@RequestParam("middleName") String mname, @RequestParam("lastName") String lname,
			@RequestParam("phoneNo") String phoneno, @RequestParam("email") String email,
			@RequestParam("educationalQual") String eq, @RequestParam("keySkills") String ks,
			@RequestParam("position") String position, @RequestParam("experience") String experience,
			@RequestParam("passout") String passout, @RequestParam("cctc") String cctc,
			@RequestParam("ectc") String ectc, @RequestParam("noticePeriod") String np,
			@RequestParam("resume") MultipartFile resume) throws IOException {
		Candidate can = new Candidate();
		Candidate candidate = service.dataAssigning(can, fname, mname, lname, phoneno, email, eq, ks, position,
				experience, passout, cctc, ectc, np, resume);
		
		if (canRepo.findByEmail(email) != null) {
			throw new emailAlreadyExistException("Email Already Found");
		} else {
			canRepo.save(candidate);
			return ResponseEntity.ok("Candidate Registered Successfully");
		}
	}

	@GetMapping("/hrpanel")
	public String hrdashboard(Model model) {
		List<Candidate> candidates = canRepo.getAllCandidate();
		model.addAttribute("AllCand", candidates);
		return "admin";
	}

	@GetMapping("/downloadresume/{candId:.+}")
	public ResponseEntity<byte[]> downloadResume(@PathVariable Long candId) {
		Candidate candidate = canRepo.findById(candId).get();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf(candidate.getFileType()));
		headers.setContentDispositionFormData("attachment", candidate.getFilename());
		return new ResponseEntity<>(candidate.getResume(), headers, HttpStatus.OK);
	}

}