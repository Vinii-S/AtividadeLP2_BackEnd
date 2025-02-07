package br.edu.ifba.demo.backend.api.controller;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifba.demo.backend.api.repository.LivroRepository;
import br.edu.ifba.demo.backend.api.repository.GeneroRepository;
import br.edu.ifba.demo.backend.api.dto.LivroDTO;
import br.edu.ifba.demo.backend.api.dto.LivroMapper;
import br.edu.ifba.demo.backend.api.model.LivroModel;
import br.edu.ifba.demo.backend.api.model.GeneroModel;

@RestController
@RequestMapping("/livro")
public class LivroController {
    
    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper;
    private final GeneroRepository generoRepository;

 
    public LivroController(
        LivroRepository repository, 
        LivroMapper livroMapper,
        GeneroRepository generoRepository
    ) {
        this.livroRepository = repository;
        this.livroMapper = livroMapper;
        this.generoRepository = generoRepository;
    }

    @GetMapping
    public String teste() {
        return "Testando Rota Usuario";
    }
    
    @GetMapping("/listall")
    public List<LivroModel> listAll() {
        return livroRepository.findAll();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<LivroDTO> getById(@PathVariable("id") Long id) {
        return livroRepository.findById(id)
            .map(livro -> ResponseEntity.ok(livroMapper.toDTO(livro)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getByIsbn/{isbn}")
    public ResponseEntity<LivroDTO> getByIsbn(@PathVariable("isbn") Integer isbn) {
        return livroRepository.findByIsbn(isbn)
            .map(livro -> ResponseEntity.ok(livroMapper.toDTO(livro)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/getByTitulo/{titulo}")
    public ResponseEntity<LivroDTO> getByTitulo(@PathVariable("titulo") String titulo) {
        return livroRepository.findByTitulo(titulo)
            .map(livro -> ResponseEntity.ok(livroMapper.toDTO(livro)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LivroDTO> addLivro(@RequestBody LivroDTO livroDTO) {
        // Busca o gênero pelo ID
        GeneroModel genero = generoRepository.findById(livroDTO.getGeneroId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "Gênero não encontrado com ID: " + livroDTO.getGeneroId()
            ));
        
        // Converte DTO para Model (com o gênero)
        LivroModel livroModel = livroMapper.toModel(livroDTO, genero);
        
        // Salva e converte de volta para DTO
        LivroModel savedLivro = livroRepository.save(livroModel);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(livroMapper.toDTO(savedLivro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<LivroDTO> deleteLivro(@PathVariable("id") Long id) {
        return livroRepository.findById(id)
            .map(livro -> {
                livroRepository.delete(livro);
                return ResponseEntity.ok(livroMapper.toDTO(livro));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}