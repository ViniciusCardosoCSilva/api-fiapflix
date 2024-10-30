package br.com.fiap.api_fiapflix.service;

import br.com.fiap.api_fiapflix.dto.FilmeDTO;
import br.com.fiap.api_fiapflix.model.Filme;
import br.com.fiap.api_fiapflix.model.Genero;
import br.com.fiap.api_fiapflix.repository.FilmeRepository;
import br.com.fiap.api_fiapflix.repository.GeneroRepository;
import br.com.fiap.api_fiapflix.service.exception.DatabaseException;
import br.com.fiap.api_fiapflix.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository repository;

    @Autowired
    private GeneroRepository generoRepository;


    @Transactional(readOnly = true)
    public List<FilmeDTO> findAll() {
        return repository.findAll().stream()
                .map(FilmeDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FilmeDTO findById(Long id) {
        Filme entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado! Id: " + id)
        );

        return new FilmeDTO(entity);
    }

    @Transactional
    public FilmeDTO insert(FilmeDTO dto) {
        Filme entity = new Filme();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new FilmeDTO(entity);
    }

    private void copyDtoToEntity(FilmeDTO dto, Filme entity) {
        entity.setTitulo(dto.getTitulo());
        entity.setAno(dto.getAno());
        //objeto completo gerenciado
        Genero genero = generoRepository.getReferenceById(dto.getGenero().getId());
        entity.setGenero(genero);
    }


    @Transactional
    public FilmeDTO update(Long id, FilmeDTO dto) {
        try {
            Filme entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new FilmeDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

   
}
