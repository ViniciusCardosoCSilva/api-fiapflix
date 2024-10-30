package br.com.fiap.api_fiapflix.service;


import br.com.fiap.api_fiapflix.dto.GeneroDTO;
import br.com.fiap.api_fiapflix.model.Genero;
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
public class GeneroService {

    @Autowired
    private GeneroRepository repository;

    @Transactional(readOnly = true)
    public List<GeneroDTO> findAll() {
        return repository.findAll().stream()
                .map(GeneroDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GeneroDTO findById(Long id) {
        Genero entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado! Id: " + id)
        );

        return new GeneroDTO(entity);
    }

    @Transactional
    public GeneroDTO insert(GeneroDTO dto) {
        Genero entity = new Genero();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new GeneroDTO(entity);
    }

    private void copyDtoToEntity(GeneroDTO dto, Genero entity) {
        entity.setNome(dto.getNome());
    }


    @Transactional
    public GeneroDTO update(Long id, GeneroDTO dto) {
        try {
            Genero entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new GeneroDTO(entity);
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
