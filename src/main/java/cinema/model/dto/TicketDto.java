package cinema.model.dto;

public class TicketDto {
    private Long id;
    private Long movieSessionId;
    private Long userId;

    public TicketDto(Long id, Long movieSessionId, Long userId) {
        this.id = id;
        this.movieSessionId = movieSessionId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
