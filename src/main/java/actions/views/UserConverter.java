package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.user;

public class UserConverter {
    //@param uv UserViewのクラス
    //@return userのインスタンス
    public static user toModel(UserView uv) {
        return new user(
        uv.getId(),
        uv.getCode(),
        uv.getName(),
        uv.getPassword(),
        uv.getAdminFlag() == null
        ? null
        : uv.getAdminFlag() == AttributeConst.ROLE_ADMIN.getIntegerValue()
                ? JpaConst.ROLE_ADMIN
                : JpaConst.ROLE_GENERAL,
        uv.getDeleteFlag() == null
                ? null
                : uv.getDeleteFlag() == AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                        ? JpaConst.U_DEL_TRUE
                        : JpaConst.U_DEL_FALSE,
        uv.getU_comment() );
    }

    //@param u userのインスタンス
    //return UserViewのインスタンス
    public static UserView toView(user u) {
        if(u == null) {
            return null;
        }
        return new UserView(
                u.getId(),
                u.getCode(),
                u.getName(),
                u.getPassword(),
                u.getAdminFlag() == null
                ? null
                : u.getAdminFlag() == JpaConst.ROLE_ADMIN
                        ? AttributeConst.ROLE_ADMIN.getIntegerValue()
                        : AttributeConst.ROLE_GENERAL.getIntegerValue(),
                u.getDeleteFlag() == null
                        ? null
                        : u.getDeleteFlag() == JpaConst.U_DEL_TRUE
                                ? AttributeConst.DEL_FLAG_TRUE.getIntegerValue()
                                : AttributeConst.DEL_FLAG_FALSE.getIntegerValue(),
                u.getU_comment()
                );
    }

    //@param list DTOモデルのリスト
    //@return Viewモデルのリスト
    public static List<UserView> toViewList(List<user> list){
        List<UserView> uvs = new ArrayList<>();
        for(user u : list) {
            uvs.add(toView(u));
        }
        return uvs;
    }

    //@param u DTOモデル（コピー先）
    //@param uv Viewモデル（コピーもと）
    public static void copyViewToModel(user u, UserView uv) {
        u.setId(uv.getId());
        u.setCode(uv.getCode());
        u.setName(uv.getName());
        u.setPassword(uv.getPassword());
        u.setAdminFlag(uv.getAdminFlag());
        u.setDeleteFlag(uv.getDeleteFlag());
        u.setU_comment(uv.getU_comment());
    }

}
