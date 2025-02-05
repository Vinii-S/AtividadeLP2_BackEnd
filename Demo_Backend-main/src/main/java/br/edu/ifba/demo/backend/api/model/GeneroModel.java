package br.edu.ifba.demo.backend.api.model;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "genero")
public class GeneroModel {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_genero")
    private Long id_genero;

    @Column(name = "nome_genero", nullable = true)
	private String nome_genero;

    @Column(name = "status", nullable = true)
	private Boolean status;
}
