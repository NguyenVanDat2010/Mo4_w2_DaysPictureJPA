package picture.repository.impl;

import picture.model.Picture;
import picture.repository.IGeneralRepository;
import picture.service.IGeneralService;

import javax.transaction.Transactional;

@Transactional
public interface IPictureRepository extends IGeneralRepository<Picture> {
    void updateLike(Long id);
}
