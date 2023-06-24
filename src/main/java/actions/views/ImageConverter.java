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
                iv.getAddress(),
                iv.getCreated_at(),
                iv.getFilePath()
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
                i.getAddress(),
                i.getCreated_at(),
                i.getFilePath()
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
        i.setAddress(iv.getAddress());
        i.setCreated_at(iv.getCreated_at());
        i.setFilePath(iv.getFilePath());
    }

}
