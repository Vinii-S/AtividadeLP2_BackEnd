package br.edu.ifba.demo.backend.api.dto;

import org.springframework.stereotype.Component;

import br.edu.ifba.demo.backend.api.model.GeneroModel;
import br.edu.ifba.demo.backend.api.model.LivroModel;

@Component
public class LivroMapper {
     public LivroDTO toDTO(LivroModel livroModel) {
        LivroDTO dto = new LivroDTO();

        dto.setId_livro(livroModel.getId_livro());
        if (livroModel.getGenero() != null) {
            dto.setGeneroId(livroModel.getGenero().getId_genero());
            dto.setGeneroNome(livroModel.getGenero().getNome_genero());
        } else {
            dto.setGeneroId(null);
            dto.setGeneroNome(""); // ou simplesmente "", conforme sua necessidade
        }
        dto.setTitulo(livroModel.getTitulo());
        dto.setAutor(livroModel.getAutor());
        dto.setAno_publicacao(livroModel.getAno_publicacao());
        dto.setData_cadastro(livroModel.getData_cadastro());
        dto.setEditora(livroModel.getEditora());
        dto.setIdioma(livroModel.getIdioma());
        dto.setIsbn(livroModel.getIsbn());
        dto.setNum_paginas(livroModel.getNum_paginas());
        dto.setPreco(livroModel.getPreco());
        dto.setSinopse(livroModel.getSinopse());
        return dto;
    }

    public LivroModel toModel(LivroDTO livroDTO, GeneroModel genero) {
        LivroModel model = new LivroModel();
        model.setTitulo(livroDTO.getTitulo());
        model.setAutor(livroDTO.getAutor());
        model.setGenero(genero);
        model.setId_livro(livroDTO.getId_livro());
        model.setTitulo(livroDTO.getTitulo());
        model.setAutor(livroDTO.getAutor());
        model.setAno_publicacao(livroDTO.getAno_publicacao());
        model.setData_cadastro(livroDTO.getData_cadastro());
        model.setEditora(livroDTO.getEditora());
        model.setIdioma(livroDTO.getIdioma());
        model.setIsbn(livroDTO.getIsbn());
        model.setNum_paginas(livroDTO.getNum_paginas());
        model.setPreco(livroDTO.getPreco());
        model.setSinopse(livroDTO.getSinopse());
        return model;
    }
}
