package br.edu.ifba.demo.backend.api.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.edu.ifba.demo.backend.api.repository.LivroRepository;

import br.edu.ifba.demo.backend.api.model.LivroModel;

@RestController
@RequestMapping("/livro")
public class LivroController {
	
	private LivroRepository livroRepository;

	
	public LivroController(LivroRepository repository) {
		super();
		this.livroRepository = repository;
	}

	@GetMapping
	public String teste() {
		return "Testando Rota Usuario";
	}
	
	// Método que retornar todos os usuarios do banco de dados
	@GetMapping("/listall")
	public List<LivroModel> listall() {
		var livros = livroRepository.findAll();
		return livros;
	}

	
    @GetMapping("/getById/{id}")
    public LivroModel getById(@PathVariable("id") Long id) {
        Optional<LivroModel> livro = livroRepository.findById(id);
        if (livro.isPresent())
            return livro.get();
        return null;
    }

    @GetMapping("/getByIsbn/{isbn}")
    public LivroModel getByIsbn(@PathVariable("isbn") Integer isbn) {
        Optional<LivroModel> livro = livroRepository.findByIsbn(isbn);
        if (livro.isPresent())
            return livro.get();
        return null;
    }
    
    @GetMapping("/getByTitulo/{titulo}")
    public LivroModel getbyTitulo(@PathVariable("titulo") String titulo) {
        Optional<LivroModel> livro = livroRepository.findByTitulo(titulo);
        if (livro.isPresent())
            return livro.get();
        return null;
    }

    @PostMapping
    public ResponseEntity<LivroModel> addLivro(@RequestBody LivroModel livro) {
        LivroModel savedLivro = livroRepository.save(livro);
        return new ResponseEntity<LivroModel>(savedLivro, HttpStatus.CREATED);
    }

   
    

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroModel> deleteLivro(@PathVariable("id") Long id) {
        Optional<LivroModel> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.delete(livro.get());
            return new ResponseEntity<LivroModel>(livro.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}