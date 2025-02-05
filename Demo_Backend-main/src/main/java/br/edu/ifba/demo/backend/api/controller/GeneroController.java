package br.edu.ifba.demo.backend.api.controller;


import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.edu.ifba.demo.backend.api.repository.GeneroRepository;

import br.edu.ifba.demo.backend.api.model.GeneroModel;

@RestController
@RequestMapping("/genero")
public class GeneroController {
    private GeneroRepository generoRepository;

    
    public GeneroController(GeneroRepository generoRepository) {
        super();
        this.generoRepository = generoRepository;
    }
// Método que retornar todos os generos do banco de dados
	@GetMapping("/listall")
	public List<GeneroModel> listall() {
		var generos = generoRepository.findAll();
		return generos;
	}

    @PostMapping
    public ResponseEntity<GeneroModel> addGenero(@RequestBody GeneroModel genero) {
        GeneroModel savedGenero = generoRepository.save(genero);
        return new ResponseEntity<GeneroModel>(savedGenero, HttpStatus.CREATED);
    }
}
