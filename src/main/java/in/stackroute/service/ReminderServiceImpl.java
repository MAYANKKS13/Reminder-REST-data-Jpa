package in.stackroute.service;

import in.stackroute.exceptions.ReminderNotFoundException;
import in.stackroute.model.Reminder;
import in.stackroute.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository repository;

    @Override
    public Reminder save(Reminder reminder) {
        return repository.save(reminder);
    }

    @Override
    public Reminder update(Reminder reminder) {
        return repository.save(reminder);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public Reminder findById(int id) throws ReminderNotFoundException {
        var reminder = repository.findById(id);
        if (reminder.isEmpty()) {
            throw new ReminderNotFoundException("Reminder not found with id: " + id);
        }
        return reminder.get();

    }

    @Override
    public List<Reminder> findAll() {
        return repository.findAll();
    }

    @Override
    public boolean existsById(int id) {
        if (!repository.existsById(id)) {
            throw new ReminderNotFoundException("Reminder not found with id: " + id);
        }
        return true;
    }
}
