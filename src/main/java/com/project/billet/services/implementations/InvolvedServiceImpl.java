package com.project.billet.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.billet.models.entities.Involved;
import com.project.billet.repositories.InvolvedRepository;
import com.project.billet.services.InvolvedService;

@Service
public class InvolvedServiceImpl implements InvolvedService {
	
	@Autowired
	InvolvedRepository involvedRepository;

	@Override
	public Involved findOneByName(String name) {
		Involved aInvolved = involvedRepository.findOnebyName(name);
		return aInvolved;
	}

	@Override
	public void addInvolved(String name) throws Exception {
		Involved aInvolved = new Involved();
		aInvolved.setName(name);
		involvedRepository.save(aInvolved);
		
	}

	@Override
	public void deleteInvolved(String name) throws Exception {
		List<Involved> involvedList = involvedRepository.findAll();
		Involved tempInvolved = involvedList.stream()
				.filter(involved -> involved.getName().equals(name))
				.findAny()
				.orElse(null);
		involvedRepository.deleteById(tempInvolved.getCode());
		
	}

}
