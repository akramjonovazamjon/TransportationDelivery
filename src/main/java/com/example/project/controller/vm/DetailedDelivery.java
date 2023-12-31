package com.example.project.controller.vm;

import java.time.LocalDateTime;
import java.util.List;

public record DetailedDelivery(
        Long id,
        LocalDateTime departureTime,
        String departureAddress,
        LocalDateTime arrivalTime,
        String arrivalAddress,
        Long distance,
        DeliveryVm delivery,
        TransportVm transport,
        List<String> stoppedAddresses
) {
}
