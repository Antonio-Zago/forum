package br.com.alura.forum.controller.form;

import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

public class TopicoForm {
	
	@NotNull @NotEmpty
	private String titulo; 
	
	@NotNull @NotEmpty
	private String mensagem;
	
	@NotNull @NotEmpty
	private String nomeCurso;
	
	
	public Topico converter(CursoRepository CursoRepository) {
		
		Curso curso = CursoRepository.findByNome(nomeCurso);
		
		return new Topico(titulo, mensagem, curso);
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {
		
		Topico topico = topicoRepository.getById(id);
		topico.setTitulo(this.getTitulo());
		topico.setMensagem(this.getMensagem());
		return topico;
	}
}
