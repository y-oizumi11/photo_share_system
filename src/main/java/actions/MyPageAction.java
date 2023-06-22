package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ImageView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import services.ImageService;

public class MyPageAction extends ActionBase {
    private ImageService service;

    @Override
    public void process() throws ServletException, IOException {

        service = new ImageService();

        invoke();

        service.close();
    }

    public void index() throws ServletException, IOException{
        UserView loginUser = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        int page = getPage();
        List<ImageView> images = service.getMinePerPage(loginUser, page);
        long myImageCount = service.countAllMine(loginUser);

        putRequestScope(AttributeConst.IMAGES, images);
        putRequestScope(AttributeConst.IMG_COUNT, myImageCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        forward(ForwardConst.FW_TOP_INDEX);
    }

}
