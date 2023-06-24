package com.example.project.service;

import com.example.project.dto.CreateClientUser;
import com.example.project.entity.ClientUser;
import com.example.project.exception.client_user.ClientUserExistByPhoneNumber;
import com.example.project.exception.client_user.ClientUserNotFoundByIdException;
import com.example.project.mapper.ClientUserMapper;
import com.example.project.repository.ClientUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientUserService {

    private final ClientUserRepository repository;

    private final ClientUserMapper mapper;

    public ClientUser create(CreateClientUser dto) {

        if (repository.existsByPhoneNumber(dto.phoneNumber())) {
            throw new ClientUserExistByPhoneNumber(dto.phoneNumber());
        }

        ClientUser clientUser = mapper.newClientUser(dto);

        return repository.save(clientUser);
    }

    public List<ClientUser> getAll(Pageable pageable) {
        return repository.findAll(pageable).getContent();
    }

    public ClientUser getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ClientUserNotFoundByIdException(id));
    }

}
