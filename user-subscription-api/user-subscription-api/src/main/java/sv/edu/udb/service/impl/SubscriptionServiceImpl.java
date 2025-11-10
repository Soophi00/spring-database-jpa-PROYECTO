package sv.edu.udb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.domain.Subscription;
import sv.edu.udb.domain.User;
import sv.edu.udb.exception.BadRequestException;
import sv.edu.udb.exception.NotFoundException;
import sv.edu.udb.repository.SubscriptionRepository;
import sv.edu.udb.repository.UserRepository;
import sv.edu.udb.resource.SubscriptionRequest;
import sv.edu.udb.resource.SubscriptionResponse;
import sv.edu.udb.service.SubscriptionService;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repo;
    private final UserRepository userRepo;

    @Override @Transactional
    public SubscriptionResponse create(SubscriptionRequest r) {
        if (r.getEndDate() != null && r.getEndDate().isBefore(r.getStartDate()))
            throw new BadRequestException("endDate must be on/after startDate", "endDate");

        User u = userRepo.findById(r.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));

        Subscription e = new Subscription();
        e.setPlan(r.getPlan());
        e.setStartDate(r.getStartDate());
        e.setEndDate(r.getEndDate());
        e.setUser(u);
        return toResp(repo.save(e));
    }

    @Override @Transactional
    public SubscriptionResponse update(Long id, SubscriptionRequest r) {
        if (r.getEndDate() != null && r.getEndDate().isBefore(r.getStartDate()))
            throw new BadRequestException("endDate must be on/after startDate", "endDate");

        Subscription e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Subscription not found"));

        if (!e.getUser().getId().equals(r.getUserId())) {
            User u = userRepo.findById(r.getUserId())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            e.setUser(u);
        }
        e.setPlan(r.getPlan());
        e.setStartDate(r.getStartDate());
        e.setEndDate(r.getEndDate());
        return toResp(repo.save(e));
    }

    @Override @Transactional
    public void delete(Long id) {
        Subscription e = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Subscription not found"));
        repo.delete(e);
    }

    @Override
    public SubscriptionResponse getById(Long id) {
        return repo.findById(id).map(this::toResp)
                .orElseThrow(() -> new NotFoundException("Subscription not found"));
    }

    @Override
    public Page<SubscriptionResponse> listByUser(Long userId, Pageable pageable) {
        userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return repo.findByUserId(userId, pageable).map(this::toResp);
    }

    private SubscriptionResponse toResp(Subscription s) {
        SubscriptionResponse r = new SubscriptionResponse();
        r.setId(s.getId());
        r.setPlan(s.getPlan());
        r.setStartDate(s.getStartDate());
        r.setEndDate(s.getEndDate());
        r.setUserId(s.getUser().getId());
        return r;
    }
}

