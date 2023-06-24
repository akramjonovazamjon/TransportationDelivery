package com.example.project.controller;

import com.example.project.controller.vm.TransportVm;
import com.example.project.dto.CreateTransport;
import com.example.project.dto.ResponseData;
import com.example.project.entity.Transport;
import com.example.project.mapper.TransportMapper;
import com.example.project.service.TransportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transports")
public class TransportController {

    private final TransportService service;

    private final TransportMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseData<TransportVm> create(@RequestBody @Valid CreateTransport dto) {
        Transport transport = service.create(dto);
        return ResponseData.of(mapper.asTransportVm(transport));
    }

    @GetMapping
    public ResponseData<List<TransportVm>> getAll(Pageable pageable) {
        List<Transport> transports = service.getAll(pageable);
        return ResponseData.of(mapper.asTransportList(transports));
    }

    @GetMapping("/{id}")
    public ResponseData<TransportVm> getById(@PathVariable Long id) {
        Transport transport = service.getById(id);
        return ResponseData.of(mapper.asTransportVm(transport));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
