package com.evadeeva.evadeeva.services.impl;

import com.evadeeva.evadeeva.mapper.AddressMapper;
import com.evadeeva.evadeeva.models.Address;
import com.evadeeva.evadeeva.repositories.AddressRepository;
import com.evadeeva.evadeeva.services.AddressService;
import com.evadeeva.evadeeva.web.dto.request.AddressRequest;
import com.evadeeva.evadeeva.web.dto.response.AddressResponse;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        Address address = addressMapper.mapToModel(addressRequest);
        address.setStatus(1);
        address.setFocus(0);
        Date currentDate = new Date();
        address.setCreatedDate(currentDate);
        address.setModifiedDate(currentDate);
        return addressMapper.mapToResponse(addressRepository.save(address));
    }

    @Override
    public AddressResponse updateAddress(long addressId, AddressRequest addressRequest) {
        Address address = addressRepository.findById(addressId).orElseThrow();
        addressMapper.updateModel(address, addressRequest);
        Date currentDate = new Date();
        address.setModifiedDate(currentDate);
        addressRepository.save(address);
        return addressMapper.mapToResponse(address);
    }
}
