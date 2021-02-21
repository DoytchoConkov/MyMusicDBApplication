package exam.services.impl;

import exam.models.entities.Artist;
import exam.models.entities.ArtistName;
import exam.repositories.ArtistRepository;
import exam.services.ArtistService;
import org.springframework.stereotype.Service;

import static exam.constants.Carriers.*;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public void init() {
        if (artistRepository.count() == 0) {
            for (ArtistName artistName : ArtistName.values()) {
                Artist artist = new Artist(artistName);
                switch (artistName) {
                    case METALLICA:
                        artist.setCarrierInformation(METALLICA);
                        break;
                    case MADONNA:
                        artist.setCarrierInformation(MADONNA);
                        break;
                    case QUEEN:
                        artist.setCarrierInformation(QUEEN);
                        break;
                }
                this.artistRepository.save(artist);
            }
        }
    }

    @Override
    public Artist findArtist(ArtistName artist) {
        return this.artistRepository.findByName(artist).orElse(null);
    }

}
