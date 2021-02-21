package exam.services.impl;

import exam.models.entities.Album;
import exam.models.entities.Artist;
import exam.models.security.CurrentUser;
import exam.models.service.AlbumServiceModel;
import exam.models.views.AlbumViewModel;
import exam.repositories.AlbumRepository;
import exam.services.AlbumService;
import exam.services.ArtistService;
import exam.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final ModelMapper modelMapper;
    private final AlbumRepository albumRepository;
    private final ArtistService artistService;
    private final CurrentUser currentUser;
    private final UserService userService;

    public AlbumServiceImpl(ModelMapper modelMapper, AlbumRepository albumRepository, ArtistService artistService, CurrentUser currentUser, UserService userService) {
        this.modelMapper = modelMapper;
        this.albumRepository = albumRepository;
        this.artistService = artistService;
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @Override
    public void addAlbum(AlbumServiceModel albumServiceModel) {
        Album album = modelMapper.map(albumServiceModel, Album.class);
        Artist artist = this.artistService.findArtist(albumServiceModel.getArtist());
        album.setArtist(artist);
        album.setUser(this.userService.findByUsername(currentUser.getUsername()));
        this.albumRepository.save(album);
    }

    @Override
    public List<AlbumViewModel> viewAll() {
        return this.albumRepository.findAll().stream().map(a ->
        {
           AlbumViewModel albumViewModel= modelMapper.map(a, AlbumViewModel.class);
albumViewModel.setArtist(a.getArtist().getName());
                    return albumViewModel;
        }).collect(Collectors.toList());
    }

    @Override
    public Long getAllCopies() {
        return this.albumRepository.getAllCopies();
    }

    @Override
    public void deleteAlbum(String id) {
        this.albumRepository.deleteById(id);
    }
}
