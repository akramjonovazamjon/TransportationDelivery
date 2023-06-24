package com.example.project.mapper;

import com.example.project.controller.vm.DeliveryVm;
import com.example.project.entity.ClientUser;
import com.example.project.entity.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {ClientUserMapper.class})
public interface DeliveryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", source = "owner")
    Delivery newDelivery(ClientUser owner);


    DeliveryVm asDeliveryVm(Delivery delivery);

}
