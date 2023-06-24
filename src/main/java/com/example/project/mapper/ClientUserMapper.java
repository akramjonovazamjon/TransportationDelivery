package com.example.project.mapper;

import com.example.project.controller.vm.ClientUserVm;
import com.example.project.dto.CreateClientUser;
import com.example.project.entity.ClientUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ClientUserMapper {
    @Mapping(target = "id", ignore = true)
    ClientUser newClientUser(CreateClientUser dto);

    ClientUserVm asClientUserVm(ClientUser clientUser);

    List<ClientUserVm> asClientUserList(List<ClientUser> clientUsers);
}
