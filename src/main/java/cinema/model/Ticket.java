package cinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private MovieSession movieSession;
    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Ticket{"
                + "Id=" + id
                + ", movieSession_id =" + movieSession.getId()
                + ", user_id=" + user.getId()
                + '}';
    }
}
