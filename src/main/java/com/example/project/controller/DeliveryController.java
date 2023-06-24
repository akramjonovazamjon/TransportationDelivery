package com.example.project.controller;

import com.example.project.controller.vm.DeliveryVm;
import com.example.project.dto.CreateDelivery;
import com.example.project.dto.ResponseData;
import com.example.project.entity.Delivery;
import com.example.project.mapper.DeliveryMapper;
import com.example.project.service.DeliveryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService service;
    private final DeliveryMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseData<DeliveryVm> create(@RequestBody @Valid CreateDelivery dto) {
        Delivery delivery = service.create(dto);
        return ResponseData.of(mapper.asDeliveryVm(delivery));
    }

    @GetMapping("/{id}")
    public ResponseData<DeliveryVm> getById(@PathVariable Long id) {
        Delivery delivery = service.getById(id);
        return ResponseData.of(mapper.asDeliveryVm(delivery));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
