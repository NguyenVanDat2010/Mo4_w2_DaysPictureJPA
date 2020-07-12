package picture.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import picture.model.Picture;
import picture.repository.impl.IPictureRepository;

import java.util.List;

public class PictureService implements IPictureService {
    @Autowired
    IPictureRepository pictureRepository;

    @Override
    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Override
    public Picture findById(Long id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void save(Picture model) {
        pictureRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        pictureRepository.remove(id);
    }

    @Override
    public void updateLike(Long id) {
        pictureRepository.updateLike(id);
    }
}
