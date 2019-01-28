package ch.li.k.genevalabtimerapp.stats;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "stats_table")
public class StatsRoomModel {

    @NonNull
    @PrimaryKey
    private String date;
    private String event;
    private String duration;
    private String timestamp;

    public StatsRoomModel(String date, String event, String timestamp) {
        this.date = date;
        this.event = event;
        this.timestamp = timestamp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    public String getEvent() {
        return event;
    }

    public void setEvent(@NonNull String event) {
        this.event = event;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
