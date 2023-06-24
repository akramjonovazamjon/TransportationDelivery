package com.example.project.mapper;

import com.example.project.controller.vm.DetailedDelivery;
import com.example.project.dto.CreateTransportItem;
import com.example.project.entity.Delivery;
import com.example.project.entity.Transport;
import com.example.project.entity.TransportItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = {TransportMapper.class, DeliveryMapper.class})
public interface TransportItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "delivery", source = "delivery")
    @Mapping(target = "transport", source = "transport")
    TransportItem newTransportItem(CreateTransportItem dto, Delivery delivery, Transport transport);

    DetailedDelivery asDetailedDelivery(TransportItem transportItem);

    List<DetailedDelivery> asDetailedDeliveryList(List<TransportItem> transportItems);

}
