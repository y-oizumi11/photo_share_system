package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ImageView;
import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ImageService;

public class ImageAction extends ActionBase {
    private ImageService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new ImageService();

        invoke();
        service.close();
    }

    public void index() throws ServletException, IOException{
        int page = getPage();
        List<ImageView> images = service.getAllPerPage(page);

        long imagesCount = service.countAll();

        putRequestScope(AttributeConst.IMAGES, images);
        putRequestScope(AttributeConst.IMG_COUNT, imagesCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        forward(ForwardConst.FW_IMG_INDEX);
    }

    public void entryNew() throws ServletException, IOException{
        putRequestScope(AttributeConst.TOKEN, getTokenId());
        ImageView iv = new ImageView();
        iv.setCreated_at(LocalDate.now());
        putRequestScope(AttributeConst.IMAGE, iv);

        forward(ForwardConst.FW_IMG_NEW);
    }

    public void create() throws ServletException, IOException{
        if(checkToken()) {
            LocalDate day = null;
            if(getRequestParam(AttributeConst.IMG_DATE) == null
                    || getRequestParam(AttributeConst.IMG_DATE).equals("")) {
                day = LocalDate.now();
            }else {
                day = LocalDate.parse(getRequestParam(AttributeConst.IMG_DATE));
            }

            UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

            ImageView iv = new ImageView(
                    null,
                    uv,
                    getRequestParam(AttributeConst.IMG_TITLE),
                    getRequestParam(AttributeConst.IMG_COMMENT),
                    getRequestParam(AttributeConst.IMG_ADDRESS),
                    day,
                    getRequestParam(AttributeConst.IMG_ADDRESS)
                    );

            List<String> errors = service.create(iv);

            if(errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.IMAGE, iv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_IMG_NEW);

            }else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                redirect(ForwardConst.ACT_IMG, ForwardConst.CMD_INDEX);
            }
        }
    }

    public void show() throws ServletException, IOException{
        ImageView iv = service.findOne(toNumber(getRequestParam(AttributeConst.IMG_ID)));

        if(iv == null) {
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }else {
            putRequestScope(AttributeConst.IMAGE, iv);
            forward(ForwardConst.FW_IMG_SHOW);
        }
    }

    public void edit() throws ServletException, IOException{
        ImageView iv = service.findOne(toNumber(getRequestParam(AttributeConst.IMG_ID)));
        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);
        if(iv == null || uv.getId() != iv.getUser().getId()) {
            forward(ForwardConst.FW_ERR_UNKNOWN);
        }else {
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.IMAGE, iv);

            forward(ForwardConst.FW_IMG_EDIT);
        }
    }

    public void update() throws ServletException, IOException{
        if(checkToken()) {
            ImageView iv = service.findOne(toNumber(getRequestParam(AttributeConst.U_ID)));

            iv.setTitle(getRequestParam(AttributeConst.IMG_TITLE));
            iv.setComment(getRequestParam(AttributeConst.IMG_COMMENT));

            List<String> errors = service.update(iv);

            if(errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.IMAGE, iv);
                putRequestScope(AttributeConst.ERR, errors);

            forward(ForwardConst.FW_IMG_EDIT);
        }else {
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
            redirect(ForwardConst.ACT_IMG, ForwardConst.CMD_INDEX);
        }
    }
    }

}
