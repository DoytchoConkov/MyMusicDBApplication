package exam.init;

import exam.services.ArtistService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {
    private final ArtistService artistService;

    public AppInit(ArtistService artistService) {
        this.artistService = artistService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.artistService.init();
    }
}
