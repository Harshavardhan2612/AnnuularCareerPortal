package com.annular.career.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.annular.career.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

	Candidate findByEmail(String value);
	
	@Query(nativeQuery = true, value = "select * from annular.candidate where active=true")
	List<Candidate> getAllCandidate();
	
}