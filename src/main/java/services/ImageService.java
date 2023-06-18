package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.ImageConverter;
import actions.views.ImageView;
import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.Image;
import models.validators.ImageValidator;

public class ImageService extends ServiceBase {
    public List<ImageView> getMinePerPage(UserView user, int page){
        List<Image> images = em.createNamedQuery(JpaConst.Q_IMG_GET_ALL_MINE, Image.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page -1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ImageConverter.toViewList(images);
    }

    public long countAllMine(UserView user) {
        long count = (long)em.createNamedQuery(JpaConst.Q_IMG_COUNT_ALL_MINE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_USER, UserConverter.toModel(user))
                .getSingleResult();

        return count;
    }

    public List<ImageView> getAllPerPage(int page){
        List<Image> images = em.createNamedQuery(JpaConst.Q_IMG_GET_ALL, Image.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return ImageConverter.toViewList(images);
    }

    public long countAll() {
        long images_count = (long)em.createNamedQuery(JpaConst.Q_IMG_COUNT, Long.class)
                .getSingleResult();
        return images_count;
    }

    public ImageView findOne(int id) {
        return ImageConverter.toView(findOneInternal(id));
    }

    public List<String> create(ImageView iv){
        List<String> errors = ImageValidator.validate(iv);
        if(errors.size() == 0) {
            LocalDateTime ldt = LocalDateTime.now();
            iv.setCreated_at(ldt);
            createInternal(iv);
        }
        return errors;
    }

    public List<String> update(ImageView iv){
        List<String> errors = ImageValidator.validate(iv);
        if(errors.size() == 0) {
            updateInternal(iv);
        }
        return errors;
    }

    private Image findOneInternal(int id) {
        return em.find(Image.class, id);
    }

    private void createInternal(ImageView iv) {
        em.getTransaction().begin();
        em.persist(ImageConverter.toModel(iv));
        em.getTransaction().commit();
    }

    private void updateInternal(ImageView iv) {
        em.getTransaction().begin();
        Image i = findOneInternal(iv.getId());
        ImageConverter.copyViewToModel(i, iv);
        em.getTransaction().commit();
    }

}
