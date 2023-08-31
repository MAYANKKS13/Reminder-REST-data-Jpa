package in.stackroute.service;

import in.stackroute.model.Reminder;

import java.util.List;

public interface ReminderService {

    Reminder save(Reminder reminder);

    Reminder update(Reminder reminder);

    void deleteById(int id);

    Reminder findById(int id);

    List<Reminder> findAll();
    boolean existsById(int id);
}
