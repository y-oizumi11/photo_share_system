package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.UserView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import services.UserService;

public class AuthAction extends ActionBase {
    private UserService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new UserService();
        invoke();
        service.close();
    }

    public void showLogin() throws ServletException, IOException{
        putRequestScope(AttributeConst.TOKEN, getTokenId());
        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }
        forward(ForwardConst.FW_LOGIN);
    }

    public void login()throws ServletException, IOException{
        String code = getRequestParam(AttributeConst.U_CODE);
        String plainPass = getRequestParam(AttributeConst.U_PASS);
        String pepper = getContextScope(PropertyConst.PEPPER);

        Boolean isValidUser = service.validateLogin(code, plainPass, pepper);
        
        if(isValidUser) {
            if(checkToken()) {
                UserView uv = service.findOne(code, plainPass, pepper);
                putSessionScope(AttributeConst.LOGIN_USER, uv);
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGINED.getMessage());

                redirect(ForwardConst.ACT_TOP, ForwardConst.CMD_INDEX);
            }
        }else {
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.LOGIN_ERR, true);
            putRequestScope(AttributeConst.U_CODE, code);
            forward(ForwardConst.FW_LOGIN);
        }
    }

    public void logout() throws ServletException, IOException{
        removeSessionScope(AttributeConst.LOGIN_USER);
        putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGOUT.getMessage());
        redirect(ForwardConst.ACT_AUTH, ForwardConst.CMD_SHOW_LOGIN);
    }

}
