package com.example.beer.inventory.service.service;

import com.example.brewery.model.BeerOrderDto;

public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);

    void deallocateOrder(BeerOrderDto beerOrderDto);
}
