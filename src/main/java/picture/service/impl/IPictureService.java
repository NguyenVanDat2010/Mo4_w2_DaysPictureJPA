package picture.service.impl;

import picture.model.Picture;
import picture.service.IGeneralService;

public interface IPictureService extends IGeneralService<Picture> {
    void updateLike(Long id);
}
