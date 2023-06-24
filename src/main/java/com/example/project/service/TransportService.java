package com.example.project.service;

import com.example.project.dto.CreateTransport;
import com.example.project.entity.Transport;
import com.example.project.exception.transport.TransportExistByNumberException;
import com.example.project.exception.transport.TransportNotFoundByIdException;
import com.example.project.mapper.TransportMapper;
import com.example.project.repository.TransportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportService {

    private final TransportRepository repository;

    private final TransportMapper mapper;

    public Transport create(CreateTransport dto) {

        if (repository.existsByNumber(dto.number())) {
            throw new TransportExistByNumberException(dto.number());
        }

        Transport transport = mapper.newTransport(dto);

        return repository.save(transport);
    }

    public List<Transport> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public Transport getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TransportNotFoundByIdException(id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
