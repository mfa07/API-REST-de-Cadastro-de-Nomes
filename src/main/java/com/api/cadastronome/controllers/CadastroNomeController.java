package com.api.cadastronome.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cadastronome.dtos.CadastroNomeDto;
import com.api.cadastronome.models.CadastrarNomeModel;
import com.api.cadastronome.services.CadastroNomeService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cadastro-nome")
public class CadastroNomeController {
	
	final CadastroNomeService cadastroNomeService;

	public CadastroNomeController(CadastroNomeService cadastroNomeService) {
		this.cadastroNomeService = cadastroNomeService;
	}
	
	@PostMapping
		public ResponseEntity<Object> saveCadastroNome(@RequestBody @Valid CadastroNomeDto cadastroNomeDto){
		var cadastroNomeModel = new CadastrarNomeModel();
		BeanUtils.copyProperties(cadastroNomeDto, cadastroNomeModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroNomeService.save(cadastroNomeModel));
	}
	
	@GetMapping
	public ResponseEntity<List<CadastrarNomeModel>> getAllCadastroNome(){
		return ResponseEntity.status(HttpStatus.OK).body(cadastroNomeService.findAoll());
	}
	
	@GetMapping("/{id}")
		public ResponseEntity<Object> getOneCadastroNome(@PathVariable(value = "id") UUID id){
		Optional<CadastrarNomeModel> cadastrarNomeModelOptional = cadastroNomeService.findById(id);
		if (!cadastrarNomeModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nome não cadastrado.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cadastrarNomeModelOptional.get());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCadastroNome(@PathVariable(value = "id") UUID id){
	Optional<CadastrarNomeModel> cadastrarNomeModelOptional = cadastroNomeService.findById(id);
	if (!cadastrarNomeModelOptional.isPresent()) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nome não existe.");
	}
	cadastroNomeService.delete(cadastrarNomeModelOptional.get());
	return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
	
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<Object> updateCadastroNome(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid CadastroNomeDto cadastroNomeDto){
        Optional<CadastrarNomeModel> cadastrarNomeModelOptional = cadastroNomeService.findById(id);
        if (!cadastrarNomeModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nome não existe.");
        }
        var cadastrarNomeModel = new CadastrarNomeModel();
        BeanUtils.copyProperties(cadastroNomeDto, cadastrarNomeModel);
        cadastrarNomeModel.setId(cadastrarNomeModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(cadastroNomeService.save(cadastrarNomeModel));
    }
}
