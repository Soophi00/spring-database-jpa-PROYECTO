package sv.edu.udb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.udb.resource.UserRequest;
import sv.edu.udb.resource.UserResponse;

public interface UserService {
    UserResponse create(UserRequest r);
    UserResponse update(Long id, UserRequest r);
    void delete(Long id);
    UserResponse getById(Long id);
    Page<UserResponse> list(String q, Pageable pageable);
}

