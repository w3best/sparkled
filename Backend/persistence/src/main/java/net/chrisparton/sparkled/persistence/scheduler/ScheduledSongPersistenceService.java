package net.chrisparton.sparkled.persistence.scheduler;

import net.chrisparton.sparkled.model.entity.ScheduledSong;
import net.chrisparton.sparkled.model.entity.Song;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduledSongPersistenceService {

    Optional<ScheduledSong> getNextScheduledSong();

    Optional<Song> getNextAutoSchedulableSong(int lastSongId);

    List<ScheduledSong> getScheduledSongs(Date startDate, Date endDate);

    Optional<ScheduledSong> getScheduledSongAtTime(Date time);

    boolean removeScheduledSong(int scheduledSongId);

    boolean saveScheduledSong(ScheduledSong scheduledSong);
}
