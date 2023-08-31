package in.stackroute.repository;

import in.stackroute.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // This informs Spring that this interface is a repository. This is optional if we are inheriting from JpaRepository.
public interface ReminderRepository extends JpaRepository<Reminder, Integer> {
}