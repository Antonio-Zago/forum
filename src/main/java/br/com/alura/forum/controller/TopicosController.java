package br.com.alura.forum.controller;
import br.com.alura.forum.controller.dto.TopicoDTO;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDTO> lista(String nomeCurso) {
		
		if(nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDTO.converter(topicos);
		}else {
			List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
			return TopicoDTO.converter(topicos);
		}
	}
	
	
	@PostMapping
	public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) { //Form são dados que chegam do cliente para a API
		
		Topico topico = form.converter(cursoRepository);
		
		topicoRepository.save(topico);
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDTO(topico));
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDTO> detalhar(@PathVariable Long id) {
		
		
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {
			return ResponseEntity.ok(new TopicoDTO(topico.get()));
		}
		
		return ResponseEntity.notFound().build();
		
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<TopicoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoForm form){
		Optional<Topico> optional = topicoRepository.findById(id);
		
		if(optional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);
			topicoRepository.save(topico);
			return ResponseEntity.ok(new TopicoDTO(topico));
		}
	
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		
		Optional<Topico> topico = topicoRepository.findById(id);
		
		if(topico.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		
		
		return ResponseEntity.notFound().build();
	}
	
	
}
