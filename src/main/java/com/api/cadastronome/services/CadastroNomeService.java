package com.api.cadastronome.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.api.cadastronome.models.CadastrarNomeModel;
import com.api.cadastronome.repositories.CadastroNomeRepository;

@Service
public class CadastroNomeService {
	
	
	final CadastroNomeRepository cadastroNomeRepository;
	
	public CadastroNomeService(CadastroNomeRepository cadastroNomeRepository) {
		this.cadastroNomeRepository = cadastroNomeRepository;
	}
	
	@Transactional
	public CadastrarNomeModel save(CadastrarNomeModel cadastroNomeModel) {
		
		return cadastroNomeRepository.save(cadastroNomeModel);
	}

	public List<CadastrarNomeModel> findAoll() {
		
		return cadastroNomeRepository.findAll();
	}

	public Optional<CadastrarNomeModel> findById(UUID id) {
		
		return cadastroNomeRepository.findById(id);
	}
	
	@Transactional
	public void delete(CadastrarNomeModel cadastrarNomeModel) {
		
		cadastroNomeRepository.delete(cadastrarNomeModel);
		
	}
}
