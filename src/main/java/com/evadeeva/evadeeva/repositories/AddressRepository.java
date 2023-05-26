package com.evadeeva.evadeeva.repositories;

import com.evadeeva.evadeeva.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserId(long userId);
}
