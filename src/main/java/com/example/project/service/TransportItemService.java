package com.example.project.service;

import com.example.project.dto.CreateTransportItem;
import com.example.project.entity.Delivery;
import com.example.project.entity.Transport;
import com.example.project.entity.TransportItem;
import com.example.project.mapper.TransportItemMapper;
import com.example.project.repository.TransportItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransportItemService {

    private final TransportItemRepository repository;

    private final TransportItemMapper mapper;

    private final TransportService transportService;

    private final DeliveryService deliveryService;

    public TransportItem create(CreateTransportItem dto, Long transportId) {

        Transport transport = transportService.getById(transportId);

        Delivery delivery = deliveryService.getById(dto.deliveryId());

        TransportItem transportItem = mapper.newTransportItem(dto, delivery, transport);

        return repository.save(transportItem);
    }

    public List<TransportItem> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public List<TransportItem> getAllByTransportId(Long transportId, Pageable pageable) {
        return repository.findAllByTransport_Id(transportId, pageable).getContent();
    }

}
