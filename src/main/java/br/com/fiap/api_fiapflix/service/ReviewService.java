package br.com.fiap.api_fiapflix.service;


import br.com.fiap.api_fiapflix.dto.ReviewDTO;
import br.com.fiap.api_fiapflix.model.Review;
import br.com.fiap.api_fiapflix.model.User;
import br.com.fiap.api_fiapflix.repository.FilmeRepository;
import br.com.fiap.api_fiapflix.repository.ReviewRepository;
import br.com.fiap.api_fiapflix.repository.UserRepository;
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
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Transactional(readOnly = true)
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream()
                .map(ReviewDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ReviewDTO findById(Long id) {
        Review entity = reviewRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado! Id: " + id)
        );

        return new ReviewDTO(entity);
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO dto) {
        User user = userRepository.getReferenceById(dto.getUserId());
        Review entity = new Review();
        copyDtoToEntity(dto, entity);
        entity = reviewRepository.save(entity);
        return new ReviewDTO(entity);

    }

    private void copyDtoToEntity(ReviewDTO dto, Review entity) {

        entity.setTexto(dto.getTexto());
        entity.setFilme(filmeRepository.getReferenceById(dto.getFilmeId()));
        entity.setUser(userRepository.getReferenceById(dto.getUserId()));

    }

    @Transactional
    public ReviewDTO update(Long id, ReviewDTO dto) {
        try {
            Review entity = reviewRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = reviewRepository.save(entity);
            return new ReviewDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try {
            reviewRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
