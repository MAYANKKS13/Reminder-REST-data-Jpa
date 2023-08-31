package in.stackroute.controller;

import in.stackroute.exceptions.ReminderNotFoundException;
import in.stackroute.model.Reminder;
import in.stackroute.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;
    private  final Logger logger = LoggerFactory.getLogger(ReminderController.class);

    // POST /api/v1/reminders
    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        logger.info("Creating reminder");
        var savedReminder = reminderService.save(reminder);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(savedReminder);
    }

    // GET /api/v1/reminders
    @GetMapping
    public ResponseEntity<List<Reminder>> getReminders() {
        logger.info("Fetching all reminders");
        var reminders = reminderService.findAll();
        if (reminders.isEmpty()) {
            logger.warn("No reminders found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reminders);
    }

    // GET /api/v1/reminders/1
    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminder(@PathVariable int id) {
        logger.info("Fetching reminder with id: {}", id);
        var reminder = reminderService.findById(id);
        return ResponseEntity.ok(reminder);
    }

    // PUT /api/v1/reminders/1
    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(@PathVariable int id, @RequestBody Reminder reminder) {
        logger.info("Updating reminder with id: {}", id);
        if (!reminderService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reminder.setId(id);
        var updatedReminder = reminderService.update(reminder);
        return ResponseEntity.ok(updatedReminder);
    }

    // DELETE /api/v1/reminders/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable int id) {
        logger.info("Deleting reminder with id: {}", id);
        if (!reminderService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reminderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ReminderNotFoundException.class)
    public ResponseEntity<String> handleReminderNotFoundException(ReminderNotFoundException ex) {
        logger.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

}
