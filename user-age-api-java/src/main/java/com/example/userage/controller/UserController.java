package com.example.userage.controller;

import com.example.userage.dto.CreateUserRequest;
import com.example.userage.dto.UpdateUserRequest;
import com.example.userage.dto.UserResponse;
import com.example.userage.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody CreateUserRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Returns a plain JSON array when no pagination params are sent (matches
     * the spec). Sends a paginated wrapper when {@code ?page=} or
     * {@code ?pageSize=} is present.
     */
    @GetMapping
    public Object list(@RequestParam(value = "page", required = false) Integer page,
                       @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page == null && pageSize == null) {
            return service.listAll();
        }
        return service.listPaged(
                page == null ? 1 : page,
                pageSize == null ? 20 : pageSize);
    }
}
