package cinema.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime time;
    @OneToMany
    private List<Ticket> tickets;
    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", tickets =" + tickets
                + ", user=" + user.getId()
                + '}';
    }
}
