package in.stackroute.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReminderDto (
        int id,
        @NotEmpty(message = "Reminder text cannot be empty")
        String text,
        @NotNull(message = "Reminder date cannot be null")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate date,
        boolean remindMe
) {}
