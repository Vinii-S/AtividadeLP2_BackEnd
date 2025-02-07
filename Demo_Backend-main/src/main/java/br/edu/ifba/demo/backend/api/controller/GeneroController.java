package br.edu.ifba.demo.backend.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    // Listar todos os gêneros (já existente)
    @GetMapping("/listall")
    public List<GeneroModel> listall() {
        return generoRepository.findAll();
    }

    // Criar novo gênero (já existente)
    @PostMapping
    public ResponseEntity<GeneroModel> adicionarGenero(@RequestBody GeneroModel genero) {
        GeneroModel novoGenero = generoRepository.save(genero);
        return new ResponseEntity<>(novoGenero, HttpStatus.CREATED);
    }

    // Atualizar gênero (novo)
    @PutMapping("/{id}")
    public ResponseEntity<GeneroModel> atualizarGenero(
            @PathVariable Long id,
            @RequestBody GeneroModel generoAtualizado) {
        return generoRepository.findById(id)
                .map(genero -> {
                    genero.setNome_genero(generoAtualizado.getNome_genero());
                    genero.setStatus(generoAtualizado.getStatus());
                    GeneroModel generoSalvo = generoRepository.save(genero);
                    return ResponseEntity.ok(generoSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir gênero (novo)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirGenero(@PathVariable Long id) {
        GeneroModel genero = generoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        genero.setStatus(false); // Marca como inativo
        generoRepository.save(genero);

        return ResponseEntity.ok().build();
    }
}
