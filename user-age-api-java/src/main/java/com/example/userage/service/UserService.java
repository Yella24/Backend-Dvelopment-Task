package com.example.userage.service;

import com.example.userage.dto.CreateUserRequest;
import com.example.userage.dto.PaginatedUsers;
import com.example.userage.dto.UpdateUserRequest;
import com.example.userage.dto.UserResponse;
import com.example.userage.entity.UserEntity;
import com.example.userage.exception.UserNotFoundException;
import com.example.userage.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Business layer between controller and repository. Owns DTO ↔ entity
 * mapping, age calculation, and pagination defaults.
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository repository;
    private final AgeCalculator ageCalculator;

    public UserService(UserRepository repository, AgeCalculator ageCalculator) {
        this.repository = repository;
        this.ageCalculator = ageCalculator;
    }

    @Transactional
    public UserResponse create(CreateUserRequest req) {
        UserEntity saved = repository.save(new UserEntity(req.getName(), req.getDob()));
        log.info("user created id={}", saved.getId());
        return toResponse(saved, false);
    }

    @Transactional(readOnly = true)
    public UserResponse get(Long id) {
        UserEntity u = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return toResponse(u, true);
    }

    @Transactional
    public UserResponse update(Long id, UpdateUserRequest req) {
        UserEntity u = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        u.setName(req.getName());
        u.setDob(req.getDob());
        UserEntity saved = repository.save(u);
        log.info("user updated id={}", saved.getId());
        return toResponse(saved, false);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
        log.info("user deleted id={}", id);
    }

    /** Plain list version (matches the spec's array shape). */
    @Transactional(readOnly = true)
    public List<UserResponse> listAll() {
        return repository.findAll(Sort.by("id").ascending())
                .stream()
                .map(u -> toResponse(u, true))
                .toList();
    }

    /** Paginated bonus endpoint. */
    @Transactional(readOnly = true)
    public PaginatedUsers listPaged(int page, int pageSize) {
        if (page < 1) {
            page = 1;
        }
        if (pageSize < 1 || pageSize > 100) {
            pageSize = 20;
        }
        Page<UserEntity> p = repository.findAll(
                PageRequest.of(page - 1, pageSize, Sort.by("id").ascending()));
        List<UserResponse> data = p.getContent().stream()
                .map(u -> toResponse(u, true))
                .toList();
        return new PaginatedUsers(data, page, pageSize, p.getTotalElements());
    }

    private UserResponse toResponse(UserEntity u, boolean withAge) {
        Integer age = withAge ? ageCalculator.calculateAge(u.getDob(), LocalDate.now()) : null;
        return new UserResponse(u.getId(), u.getName(), u.getDob(), age);
    }
}
