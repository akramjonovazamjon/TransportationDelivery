package com.example.project.controller;

import com.example.project.controller.vm.ClientUserVm;
import com.example.project.dto.CreateClientUser;
import com.example.project.dto.ResponseData;
import com.example.project.entity.ClientUser;
import com.example.project.mapper.ClientUserMapper;
import com.example.project.service.ClientUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client-users")
public class ClientUserController {

    private final ClientUserService service;

    private final ClientUserMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseData<ClientUserVm> create(@RequestBody @Valid CreateClientUser dto) {
        ClientUser clientUser = service.create(dto);
        return ResponseData.of(mapper.asClientUserVm(clientUser));
    }

    @GetMapping
    public ResponseData<List<ClientUserVm>> getAll(Pageable pageable) {
        List<ClientUser> clientUserList = service.getAll(pageable);
        return ResponseData.of(mapper.asClientUserList(clientUserList));
    }

    @GetMapping("/{id}")
    public ResponseData<ClientUserVm> getById(@PathVariable Long id) {
        ClientUser clientUser = service.getById(id);
        return ResponseData.of(mapper.asClientUserVm(clientUser));
    }

}
