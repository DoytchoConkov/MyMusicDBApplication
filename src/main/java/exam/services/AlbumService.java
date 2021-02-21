package exam.services;

import exam.models.service.AlbumServiceModel;
import exam.models.views.AlbumViewModel;

import java.util.List;

public interface AlbumService {
    void addAlbum(AlbumServiceModel albumServiceModel);

    List<AlbumViewModel> viewAll();

    Long getAllCopies();

    void deleteAlbum(String id);
}
