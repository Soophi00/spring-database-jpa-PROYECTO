package sv.edu.udb.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.resource.SubscriptionRequest;
import sv.edu.udb.resource.SubscriptionResponse;
import sv.edu.udb.service.SubscriptionService;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @Operation(summary = "Create a new subscription")
    @PostMapping
    public ResponseEntity<SubscriptionResponse> create(@RequestBody @jakarta.validation.Valid SubscriptionRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(req));
    }

    @Operation(summary = "Update a subscription")
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> update(@PathVariable Long id,
                                                       @RequestBody @jakarta.validation.Valid SubscriptionRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @Operation(summary = "Delete a subscription")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get subscription by id")
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "List subscriptions by user (pagination)")
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<Page<SubscriptionResponse>> listByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(service.listByUser(userId, pageable));
    }
}
