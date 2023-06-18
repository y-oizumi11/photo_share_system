package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.UserView;
import constants.MessageConst;
import services.UserService;

public class UserValidator {
    //@param service呼び出し元serviceクラスのインスタンス
    //@param uv UseViewのインスタンス
    //@param codeDuplicateCheckFlag ユーザーIDの重複チェック true:実施 false:実施しない
    //@param passwordCheckFlag パスワードのチェック
    //@return errorのリスト
    public static List<String> validate (UserService service, UserView uv, Boolean codeDuplicateCheckFlag, Boolean passwordCheckFlag){
        List<String> errors = new ArrayList<String>();
        String codeError = validateCode(service, uv.getCode(), codeDuplicateCheckFlag);
        if(!codeError.equals("")) {
            errors.add(codeError);
        }

        String nameError = validateName(uv.getName());
        if(!nameError.equals("")) {
            errors.add(nameError);
        }

        String passError = validatePassword(uv.getPassword(), passwordCheckFlag);
        if(!passError.equals("")) {
            errors.add(passError);
        }
        return errors;
    }

    /*ユーザーIDのチェックを行いエラーメッセージを返却
     * @param service UserServiceのインスタンス
     * @param code ユーザーID
     * @param codeDuplicateCheckFlag ユーザーIDの重複チェック true:実施 false:実施しない
     * return メッセージ
     */
    private static String validateCode(UserService service, String code, Boolean codeDuplicateCheckFlag) {
        if(code == null || code.equals("")) {
            return MessageConst.E_NOU_CODE.getMessage();
        }
        if(codeDuplicateCheckFlag) {
            long userCount = isDuplicateUser(service, code);
            if(userCount > 0) {
                return MessageConst.E_U_CODE_EXIST.getMessage();
            }
        }
        return "";
    }

    //@param return ユーザーテーブルに登録されている同一ユーザーIDのデータ件数
    private static long isDuplicateUser(UserService service, String code) {
        long UsersCount = service.countByCode(code);
        return UsersCount;
    }

    /*氏名に入力値があるかチェック
     * @param name 氏名
     * @return エラーメッセージ
     */
    private static String validateName(String name) {
        if(name == null || name.equals("")) {
            return MessageConst.E_NONAME.getMessage();
        }

        return "";
    }

    /*パスワードチェック
     * @param password パスワード
     * @param passwordCheckFlag パスワードの入力チェック
     * @return エラーメッセージ
     */
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        if(passwordCheckFlag && (password == null || password.equals(""))) {
            return MessageConst.E_NOPASSWORD.getMessage();
        }
        return "";
    }

}
