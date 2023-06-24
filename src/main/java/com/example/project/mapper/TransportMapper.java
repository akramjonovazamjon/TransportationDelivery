package com.example.project.mapper;

import com.example.project.controller.vm.TransportVm;
import com.example.project.dto.CreateTransport;
import com.example.project.entity.Transport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TransportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transportItems", ignore = true)
    Transport newTransport(CreateTransport dto);

    TransportVm asTransportVm(Transport transport);

    List<TransportVm> asTransportList(List<Transport> transports);

}
