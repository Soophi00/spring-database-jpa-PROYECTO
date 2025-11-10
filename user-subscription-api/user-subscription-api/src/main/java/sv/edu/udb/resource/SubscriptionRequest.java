package sv.edu.udb.resource;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class SubscriptionRequest {

    @NotBlank @Size(max = 60)
    private String plan;

    @NotNull
    private LocalDate startDate;

    @FutureOrPresent(message = "End date must be today or in the future")
    private LocalDate endDate;

    @NotNull
    private Long userId;

    // getters/setters
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
