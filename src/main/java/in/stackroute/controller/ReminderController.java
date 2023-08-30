package in.stackroute.controller;

import in.stackroute.model.Reminder;
import in.stackroute.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderRepository reminderRepository;

    // POST /api/v1/reminders
    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        var savedReminder = reminderRepository.save(reminder);
        return ResponseEntity.ok(savedReminder);
    }

    // GET /api/v1/reminders
    @GetMapping
    public ResponseEntity<List<Reminder>> getReminders() {
        var reminders = reminderRepository.findAll();
        if (reminders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reminders);
    }
}
