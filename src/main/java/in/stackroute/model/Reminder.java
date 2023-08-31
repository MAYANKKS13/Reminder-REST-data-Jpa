package in.stackroute.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // This informs JPA that this class is mapped to a database table.
@Table(name = "reminders_jpa") // This specifies the name of the database table to be used for mapping.
public class Reminder {

    /**
     * Rules of creating an Entity class:
     * 1. The class must have a public or protected, no-argument constructor.
     * 2. The class must not be declared final.
     * 3. No field of the entity class should be final.
     * 4. The class must not be declared abstract.
     * 5. If an entity instance is passed by value as a detached object, such as through a session bean’s remote business
     * interface, the class must implement the Serializable interface.
     * 6. Entities may extend both entity and non-entity classes, and non-entity classes may extend entity classes.
     * 7. Persistent instance fields and properties must be declared private, protected, or package-private and can be
     * accessed directly only by the entity class’s methods. Clients must access the entity’s state through accessor or
     * business methods.
     * 8. The entity class should follow standard JavaBean conventions. In particular, getters and setters should be
     * used for persistent properties.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "r_id")
    private int id;

    @Column(name = "r_text", nullable = false)
    private String text;

    @Column(name = "r_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "r_remind_me", nullable = false)
    private boolean remindMe;

    public Reminder(String text, LocalDate date, boolean remindMe) {
        this.text = text;
        this.date = date;
        this.remindMe = remindMe;
    }
}
