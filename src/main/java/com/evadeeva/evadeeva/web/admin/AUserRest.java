package com.evadeeva.evadeeva.web.admin;

import com.evadeeva.evadeeva.services.UserService;
import com.evadeeva.evadeeva.web.dto.request.UserRequest;
import com.evadeeva.evadeeva.web.dto.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/user")
public class AUserRest {
    @Autowired
    private final UserService userService;

    public AUserRest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "15") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
        Page<UserResponse> userResponses = userService.getAllUsers(pageNumber,pageSize,sortBy);

        return ResponseEntity.ok(userResponses);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") Long userId,
                                                   @RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.updateUser(userId,userRequest);
        if(userResponse != null){
            return ResponseEntity.ok(userResponse);
        }
        return new ResponseEntity<>("Cập nhật thông tin không thành công", HttpStatus.BAD_REQUEST);
    }



}
