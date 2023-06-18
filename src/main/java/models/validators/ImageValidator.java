package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ImageView;
import constants.MessageConst;

public class ImageValidator {
    public static List<String> validate(ImageView iv){
        List<String> errors = new ArrayList<String>();

        String titleError = validateTitle(iv.getTitle());
        if(!titleError.equals("")) {
            errors.add(titleError);
        }

        String commentError = validateComment(iv.getComment());
        if(!commentError.equals("")) {
            errors.add(commentError);
        }

        String addressError = validateAddress(iv.getAdress());
        if(!addressError.equals("")) {
            errors.add(addressError);
        }

        return errors;
    }

    private static String validateTitle(String title) {
        if(title == null || title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }
        return "";
    }

    private static String validateComment(String comment) {
        if(comment == null || comment.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }
        return "";
    }

    private static String validateAddress(String address) {
        if(address == null || address.equals("")) {
            return MessageConst.E_NOIMAGE.getMessage();
        }
        return "";
    }

}
