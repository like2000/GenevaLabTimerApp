package ch.li.k.genevalabtimerapp.tictoc;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicTocGenerator {

    public Duration duration;
    private LocalDateTime timestamp;
    private LocalDate date;
    private String event;

    public TicTocGenerator(LocalDate date, String event) {
        this.date = date;
        this.event = event;
        this.timestamp = LocalDateTime.now();
    }

    public Duration getDuration() {
        duration = Duration.between(
                LocalDateTime.now(),
                LocalDateTime.of(2018, 12, 1, 12, 00, 00));

        return duration;
    }

    public void readCsv() {

    }

    public void writeCsv() {

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
