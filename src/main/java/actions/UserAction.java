package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.UserService;

public class UserAction extends ActionBase {
    private UserService service;
    @Override
    public void process() throws ServletException, IOException {
        service = new UserService();
        invoke();
        service.close();
    }

    public void index() throws ServletException, IOException{
        int page = getPage();
        List<UserView> users = service.getPerPage(page);
        long userCount = service.countAll();
        putRequestScope(AttributeConst.USERS, users);
        putRequestScope(AttributeConst.U_COUNT, userCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_U_INDEX);
    }

   public void entryNew() throws ServletException, IOException {
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.USER, new UserView());
            forward(ForwardConst.FW_U_NEW);
        }

    public void create() throws ServletException, IOException{
        if(checkToken()) {
            UserView uv = new UserView(
                    null,
                    getRequestParam(AttributeConst.U_CODE),
                    getRequestParam(AttributeConst.U_NAME),
                    getRequestParam(AttributeConst.U_PASS),
                    toNumber(getRequestParam(AttributeConst.U_ADMIN_FLG)),
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue(),
                    getRequestParam(AttributeConst.U_CONTENT)
                    );

            String pepper = getContextScope(PropertyConst.PEPPER);

            List<String> errors = service.create(uv, pepper);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.USER, uv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_U_NEW);

            } else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                redirect(ForwardConst.ACT_USER, ForwardConst.CMD_INDEX);
            }

        }
    }

    public void show() throws ServletException, IOException{
        UserView uv = service.findOne(toNumber(getRequestParam(AttributeConst.U_ID)));
        if(uv == null || uv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }
        putRequestScope(AttributeConst.USER, uv);
        forward(ForwardConst.FW_U_SHOW);
    }

    public void edit() throws ServletException, IOException{

        UserView uv = service.findOne(toNumber(getRequestParam(AttributeConst.U_ID)));

          if(uv == null || uv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()) {
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return;
        }

        putRequestScope(AttributeConst.TOKEN, getTokenId());
        putRequestScope(AttributeConst.USER, uv);

        forward(ForwardConst.FW_U_EDIT);

    }

    public void update() throws ServletException, IOException{
        if(checkToken()) {
            UserView uv = new UserView(
                    toNumber(getRequestParam(AttributeConst.U_ID)),
                    getRequestParam(AttributeConst.U_CODE),
                    getRequestParam(AttributeConst.U_NAME),
                    getRequestParam(AttributeConst.U_PASS),
                    toNumber(getRequestParam(AttributeConst.U_ADMIN_FLG)),
                    AttributeConst.DEL_FLAG_FALSE.getIntegerValue(),
                    getRequestParam(AttributeConst.U_CONTENT)
                    );
            String pepper = getContextScope(PropertyConst.PEPPER);
            List<String> errors = service.update(uv, pepper);
            if(errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.USER, uv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_U_EDIT);
            }else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
                redirect(ForwardConst.ACT_USER, ForwardConst.CMD_INDEX);
                }
        }
    }

    public void destroy() throws ServletException, IOException{
        if(checkToken() && checkAdmin()) {
            service.destroy(toNumber(getRequestParam(AttributeConst.U_ID)));
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DELETED.getMessage());
            redirect(ForwardConst.ACT_USER, ForwardConst.CMD_INDEX);
        }
    }

    private boolean checkAdmin() throws ServletException, IOException {

        UserView uv = (UserView) getSessionScope(AttributeConst.LOGIN_USER);

        if (uv.getAdminFlag() == AttributeConst.ROLE_ADMIN.getIntegerValue()) {

            return true;

        }else {
            forward(ForwardConst.FW_ERR_UNKNOWN);
            return false;
        }

    }

}
