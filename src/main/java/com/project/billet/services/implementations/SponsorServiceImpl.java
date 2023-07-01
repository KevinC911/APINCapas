package com.project.billet.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.billet.models.entities.Sponsor;
import com.project.billet.repositories.SponsorRepository;
import com.project.billet.services.SponsorService;

import jakarta.transaction.Transactional;

@Service
public class SponsorServiceImpl implements SponsorService{
	
	@Autowired
	SponsorRepository sponsorRepository;

	@Override
	public Sponsor findOneByName(String name) {
		Sponsor aSponsor = sponsorRepository.findOnebyName(name);
		return aSponsor;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void addSponsor(String name) {
		Sponsor aSponsor = new Sponsor();
		aSponsor.setName(name);
		sponsorRepository.save(aSponsor);
		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deleteSponsor(String name) throws Exception{
		List<Sponsor> sponsorList = sponsorRepository.findAll();
		Sponsor tempSponsor = sponsorList.stream()
				.filter(sponsor-> sponsor.getName().equals(name))
				.findAny()
				.orElse(null);
		sponsorRepository.deleteById(tempSponsor.getCode());
		
	}

}
