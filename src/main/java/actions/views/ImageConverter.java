package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Image;

public class ImageConverter {
    public static Image toModel(ImageView iv) {
        return new Image(
                iv.getId(),
                UserConverter.toModel(iv.getUser()),
                iv.getTitle(),
                iv.getComment(),
                iv.getAdress(),
                iv.getCreated_at()
                );
    }

    public static ImageView toView(Image i) {
        if (i == null) {
            return null;
        }

        return new ImageView(
                i.getId(),
                UserConverter.toView(i.getUser()),
                i.getTitle(),
                i.getComment(),
                i.getAdress(),
                i.getCreated_at()
                );
    }

    public static List<ImageView> toViewList(List<Image> list){
        List<ImageView> uvs = new ArrayList<>();

        for (Image i : list) {
            uvs.add(toView(i));
        }

        return uvs;
    }

    public static void copyViewToModel(Image i, ImageView iv) {
        i.setId(iv.getId());
        i.setUser(UserConverter.toModel(iv.getUser()));
        i.setTitle(iv.getTitle());
        i.setComment(iv.getComment());
        i.setAdress(iv.getAdress());
        i.setCreated_at(iv.getCreated_at());
    }

}
