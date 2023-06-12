package com.evadeeva.evadeeva.web;

import com.evadeeva.evadeeva.mapper.AddressMapper;
import com.evadeeva.evadeeva.models.Address;
import com.evadeeva.evadeeva.repositories.AddressRepository;
import com.evadeeva.evadeeva.services.AddressService;
import com.evadeeva.evadeeva.services.UserService;
import com.evadeeva.evadeeva.web.dto.request.AddressRequest;
import com.evadeeva.evadeeva.web.dto.response.AddressResponse;
import com.evadeeva.evadeeva.web.dto.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserRest {
    private final UserService userService;
    private final AddressService addressService;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public UserRest(UserService userService, AddressService addressService, AddressRepository addressRepository, AddressMapper addressMapper) {
        this.userService = userService;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long userId){
        try{
            UserResponse userResponse = userService.getUser(userId);
            return ResponseEntity.ok(Objects.requireNonNullElse(userResponse, "Lỗi!"));
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/address/create")
    public ResponseEntity<?> newAddress(@RequestBody AddressRequest addressRequest){
        try{
            if(addressRequest.getUserId() != 0){
                AddressResponse addressResponse = addressService.createAddress(addressRequest);
                UserResponse userResponse = userService.getUser(addressRequest.getUserId());
                return ResponseEntity.ok(addressResponse);
            }else {
                return ResponseEntity.ok("Fail!");
            }
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/address/update/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id") long addressId,
                                           @RequestBody AddressRequest addressRequest){
        try{
            AddressResponse addressResponse = addressService.updateAddress(addressId, addressRequest);
            return ResponseEntity.ok(Objects.requireNonNullElse(addressResponse,"Không thành công!"));
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/address/default/{id}")
    public ResponseEntity<?> makeDefault(@PathVariable("id") long addressId){
        try{

            Address address = addressRepository.findById(addressId).orElseThrow();
            address.setFocus(1);
            Address newAddress = addressRepository.save(address);
            List<Address> addresses = addressRepository.findByUserId(address.getUser().getId());
            for (Address addr: addresses){
                if(addr.getId() == addressId){
                    break;
                }else {
                    addr.setFocus(0);
                    addressRepository.save(addr);
                }
            }
            return ResponseEntity.ok(addressMapper.mapToResponse(newAddress));
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!",HttpStatus.BAD_REQUEST);
        }
    }
}
