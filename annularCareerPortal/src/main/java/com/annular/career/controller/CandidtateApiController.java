package com.annular.career.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.annular.career.exceptionhandlers.emailAlreadyExistException;
import com.annular.career.model.Candidate;
import com.annular.career.repository.CandidateRepository;
import com.annular.career.service.CandidateService;
import com.annular.career.service.EmailService;
import com.annular.career.service.ExcelExporter;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/candidates")
public class CandidtateApiController {

	@Autowired
	CandidateRepository canRepo;

	@Autowired
	CandidateService service;
	
	@Autowired
	EmailService es;

	@PostMapping("/savecandidate")
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
			es.EmailforNewCandidate(candidate);
			return ResponseEntity.ok("Candidate Registered Successfully");
		}
	}

	@GetMapping("viewallcandidate")
	public List<Candidate> getAllCandidate() {
		return (List<Candidate>) canRepo.getAllCandidate();
	}

	@GetMapping("/viewparticularcandidate/{candId}")
	public ResponseEntity<Candidate> getCandidate(@PathVariable long candId) {
		Optional<Candidate> c = canRepo.findById(candId);
		if (c.isPresent()) {
			Candidate veh = c.get();
			return ResponseEntity.status(HttpStatus.OK).body(veh);
		} else {
			return ResponseEntity.notFound().header("Message", "Candidate not found").build();
		}
	}

	@PatchMapping("/updatecandidate/{candId}")
	public ResponseEntity<Candidate> updateCandidate(@PathVariable long candId, @RequestParam("firstName") String fname,
			@RequestParam("middleName") String mname, @RequestParam("lastName") String lname,
			@RequestParam("phoneNo") String phoneno, @RequestParam("email") String email,
			@RequestParam("educationalQual") String eq, @RequestParam("keySkills") String ks,
			@RequestParam("position") String position, @RequestParam("experience") String experience,
			@RequestParam("passout") String passout, @RequestParam("cctc") String cctc,
			@RequestParam("ectc") String ectc, @RequestParam("noticePeriod") String np,
			@RequestParam("resume") MultipartFile resume) throws IOException {

		Candidate candidate = new Candidate();
		Candidate can = service.dataAssigning(candidate, fname, mname, lname, phoneno, email, eq, ks, position,
				experience, passout, cctc, ectc, np, resume);

		Optional<Candidate> temp = canRepo.findById(candId);
		if (temp != null) {
			Candidate nuc = temp.get();
			if (can.getCctc() != null) {
				nuc.setCctc(can.getCctc());
			}
			if (can.getEctc() != null) {
				nuc.setEctc(can.getEctc());
			}
			if (can.getEducationalQual() != null) {
				nuc.setEducationalQual(can.getEducationalQual());
			}
			if (can.getEmail() != null) {
				nuc.setEmail(can.getEmail());
			}
			if (can.getExperience() != null) {
				nuc.setExperience(can.getExperience());
			}
			if (can.getFirstName() != null) {
				nuc.setFirstName(can.getFirstName());
			}
			if (can.getKeySkills() != null) {
				nuc.setKeySkills(can.getKeySkills());
			}
			if (can.getLastName() != null) {
				nuc.setLastName(can.getLastName());
			}
			if (can.getMiddleName() != null) {
				nuc.setMiddleName(can.getMiddleName());
			}
			if (can.getNoticePeriod() != null) {
				nuc.setNoticePeriod(can.getNoticePeriod());
			}
			if (can.getPassout() != null) {
				nuc.setPassout(can.getPassout());
			}
			if (can.getPhoneNo() != null) {
				nuc.setPhoneNo(can.getPhoneNo());
			}
			if (can.getPosition() != null) {
				nuc.setPosition(can.getPosition());
			}
			Candidate uc = canRepo.save(nuc);
			es.EmailforUpdatedCandidate(uc);
			return ResponseEntity.ok(uc);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/deletecandidate/{candId}")
	public ResponseEntity<String> deleteCandidate(@PathVariable long candId) {
		if (canRepo.existsById(candId)) {
			canRepo.deleteById(candId);
			return ResponseEntity.ok("Candidate deleted successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/exportToExcel")
    public void exportToExcel(HttpServletResponse response) {
        try {
            List<Candidate> candidates = canRepo.getAllCandidate();
            ExcelExporter.export(candidates, response);
        } catch (IOException e) {
            // Handle exception (log or provide a user-friendly message)
            e.printStackTrace();
        }
    }
	
	
//	  @GetMapping("/export")
//	    public void exportCandidates(HttpServletResponse response) throws IOException {
//	        List<Candidate> candidates = // Retrieve your candidates from the database or any other source
//	        		CandidtateApiController.export(candidates, response);
//	    }
	@GetMapping("/{candId:.+}/resume")
	public ResponseEntity<byte[]> downloadResume(@PathVariable Long candId) {
		Optional<Candidate> optionalCandidate = canRepo.findById(candId);
		if (optionalCandidate.isPresent()) {
			Candidate candidate = optionalCandidate.get();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.valueOf(candidate.getFileType()));
			headers.setContentDispositionFormData("attachment", candidate.getFilename());
			return new ResponseEntity<>(candidate.getResume(), headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}