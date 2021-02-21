package exam.services;

import exam.models.entities.Artist;
import exam.models.entities.ArtistName;

public interface ArtistService {
    void init();

    Artist findArtist(ArtistName artist);
}
