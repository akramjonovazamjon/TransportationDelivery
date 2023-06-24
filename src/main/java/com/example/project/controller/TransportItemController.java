package com.example.project.controller;

import com.example.project.controller.vm.DetailedDelivery;
import com.example.project.dto.CreateTransportItem;
import com.example.project.dto.ResponseData;
import com.example.project.entity.TransportItem;
import com.example.project.mapper.TransportItemMapper;
import com.example.project.service.TransportItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transports/{transportId}/items")
public class TransportItemController {

    private final TransportItemService service;

    private final TransportItemMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseData<DetailedDelivery> create(@RequestBody @Valid CreateTransportItem dto, @PathVariable Long transportId) {
        TransportItem transportItem = service.create(dto, transportId);
        return ResponseData.of(mapper.asDetailedDelivery(transportItem));
    }

    @GetMapping("/all")
    public ResponseData<List<DetailedDelivery>> getAllDeliveries(Pageable pageable) {
        List<TransportItem> itemList = service.getAll(pageable);
        return ResponseData.of(mapper.asDetailedDeliveryList(itemList));
    }

    @GetMapping
    public ResponseData<List<DetailedDelivery>> getDeliveriesByTransportId(@PathVariable Long transportId, Pageable pageable) {
        List<TransportItem> itemList = service.getAllByTransportId(transportId, pageable);
        return ResponseData.of(mapper.asDetailedDeliveryList(itemList));
    }
}
