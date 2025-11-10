package sv.edu.udb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.udb.resource.SubscriptionRequest;
import sv.edu.udb.resource.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse create(SubscriptionRequest req);
    SubscriptionResponse update(Long id, SubscriptionRequest req);
    void delete(Long id);
    SubscriptionResponse getById(Long id);
    Page<SubscriptionResponse> listByUser(Long userId, Pageable pageable);
}
