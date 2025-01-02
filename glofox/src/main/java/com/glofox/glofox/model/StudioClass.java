package com.glofox.glofox.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
public class StudioClass {
    @NotBlank(message = "Class name is required")
    private String className;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private int capacity;

    public StudioClass(String className, LocalDate startDate, LocalDate endDate, int capacity) {
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
    }

    // Override equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Check if both references point to the same object
        if (o == null || getClass() != o.getClass()) return false; // Check for null and type mismatch
        StudioClass that = (StudioClass) o;
        // Check if all relevant fields are equal
        return capacity == that.capacity &&
                Objects.equals(className, that.className) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    // Override hashCode() method
    @Override
    public int hashCode() {
        return Objects.hash(className, startDate, endDate, capacity);
    }
}
