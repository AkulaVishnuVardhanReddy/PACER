package com.Backend.PACER.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.Backend.PACER.entities.CourtCase;
import com.Backend.PACER.repositories.CourtCaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourtCaseService{

	private final CourtCaseRepository courtCaseRepository;

	public CourtCaseService(CourtCaseRepository courtCaseRepository) {
		this.courtCaseRepository = courtCaseRepository;
	}

	public List<CourtCase> listPendingCases(String status){
		return courtCaseRepository.findByStatus("pending");
	}

	public List<CourtCase> listResolvedCases(String status){
		return courtCaseRepository.findByStatus("resolved");
	}

	public List<CourtCase> getByLawyerName(String lawyerName){
		return courtCaseRepository.findByLawyerName(lawyerName);
	}

	public List<CourtCase> getByDefendentName(String defendentName){
		return courtCaseRepository.findByDefendentName(defendentName);
	}

	public List<CourtCase> getByCourtName(String courtName){
		return courtCaseRepository.findByCourtName(courtName);
	}

	public List<CourtCase> getByArrestingOfficerName(String arrestingOfficerName){
		return courtCaseRepository.findByArrestingOfficerName(arrestingOfficerName);
	}

	public List<CourtCase> getByCrimeType(String crimeType){
		return courtCaseRepository.findByCrimeType(crimeType);
	}


	public List<CourtCase> getByPublicProsecutorName(String prosecutorName){
		return courtCaseRepository.findByPublicProsecutorName(prosecutorName);
	}

	public List<CourtCase> getByJudgeName(String judgeName){
		return courtCaseRepository.findByJudgeName(judgeName);
	}

	public List<CourtCase> getByCrimeDate(LocalDate crimeDate){
		return courtCaseRepository.findByCrimeDate(crimeDate);
	}

	public CourtCase createCourtCase(CourtCase courtCase) {
		return courtCaseRepository.save(courtCase);
	}

	public List<CourtCase> getAllCourtCases(){
		return courtCaseRepository.findAll();
	}

	public CourtCase updateStatus(Long cin ,String status) {
		Optional<CourtCase> courtCaseOptional = courtCaseRepository.findById(cin);
		if(courtCaseOptional.isPresent()) {
			CourtCase courtCase = courtCaseOptional.get();
			courtCase.setStatus(status);
			return courtCaseRepository.save(courtCase);
		}
		else {
			throw new IllegalArgumentException("CourtCase with CIN "+cin+" not found");
		}
	}
}