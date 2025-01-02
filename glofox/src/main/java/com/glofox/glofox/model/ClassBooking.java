package com.glofox.glofox.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassBooking {
    @NotBlank(message = "Member name is required")
    private String memberName;
    @NotBlank(message = "Class name is required")
    private String className;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;
}
