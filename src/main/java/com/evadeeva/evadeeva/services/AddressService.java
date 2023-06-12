package com.evadeeva.evadeeva.services;

import com.evadeeva.evadeeva.web.dto.request.AddressRequest;
import com.evadeeva.evadeeva.web.dto.response.AddressResponse;

public interface AddressService {
    AddressResponse createAddress(AddressRequest addressRequest);
    AddressResponse updateAddress(long addressId, AddressRequest addressRequest);
}
