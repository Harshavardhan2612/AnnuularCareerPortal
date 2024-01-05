package com.annular.career.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long candId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String educationalQual;
	private String keySkills;
	private String position;
	private String experience;
	private String passout;
	private String cctc;
	private String ectc;
	private String noticePeriod;
	private boolean active;
	private String filename;
	private String fileType;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] resume;

	public Candidate(String firstName, String middleName, String lastName, String phoneNo, String email,
			String educationalQual, String keySkills, String position, String experience, String passout, String cctc,
			String ectc, String noticePeriod, boolean active, String filename, String fileType, byte[] resume) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.educationalQual = educationalQual;
		this.keySkills = keySkills;
		this.position = position;
		this.experience = experience;
		this.passout = passout;
		this.cctc = cctc;
		this.ectc = ectc;
		this.noticePeriod = noticePeriod;
		this.active = active;
		this.filename = filename;
		this.fileType = fileType;
		this.resume = resume;
	}

	public Candidate() {
		super();
	}

	public long getCandId() {
		return candId;
	}

	public void setCandId(long candId) {
		this.candId = candId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducationalQual() {
		return educationalQual;
	}

	public void setEducationalQual(String educationalQual) {
		this.educationalQual = educationalQual;
	}

	public String getKeySkills() {
		return keySkills;
	}

	public void setKeySkills(String keySkills) {
		this.keySkills = keySkills;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getPassout() {
		return passout;
	}

	public void setPassout(String passout) {
		this.passout = passout;
	}

	public String getCctc() {
		return cctc;
	}

	public void setCctc(String cctc) {
		this.cctc = cctc;
	}

	public String getEctc() {
		return ectc;
	}

	public void setEctc(String ectc) {
		this.ectc = ectc;
	}

	public String getNoticePeriod() {
		return noticePeriod;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getResume() {
		return resume;
	}

	public void setResume(byte[] resume) {
		this.resume = resume;
	}

}
