package com.example.project.service;

import com.example.project.dto.CreateDelivery;
import com.example.project.entity.ClientUser;
import com.example.project.entity.Delivery;
import com.example.project.exception.delivery.DeliveryNotFoundByIdException;
import com.example.project.mapper.DeliveryMapper;
import com.example.project.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository repository;

    private final DeliveryMapper mapper;

    private final ClientUserService clientUserService;

    public Delivery create(CreateDelivery dto) {

        ClientUser clientUser = clientUserService.getById(dto.ownerId());

        Delivery delivery = mapper.newDelivery(clientUser);

        return repository.save(delivery);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Delivery getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new DeliveryNotFoundByIdException(id));
    }
}
