package services;

import java.util.List;

import javax.persistence.NoResultException;

import actions.views.UserConverter;
import actions.views.UserView;
import constants.JpaConst;
import models.user;
import models.validators.UserValidator;
import utils.EncryptUtil;

public class UserService extends ServiceBase {
    public List<UserView>getPerPage(int Page){
        List<user> users = em.createNamedQuery(JpaConst.Q_U_GET_ALL, user.class)
                .setFirstResult(JpaConst.ROW_PER_PAGE *(Page -1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();

        return UserConverter.toViewList(users);
    }

    public long countAll() {
        long userCount = (long)em.createNamedQuery(JpaConst.Q_U_COUNT, Long.class).getSingleResult();
        return userCount;
    }

    public UserView findOne(String code, String plainPass, String pepper) {
        user u = null;
        try {
            String pass = EncryptUtil.getPasswordEncrypt(plainPass, pepper);
            u = em.createNamedQuery(JpaConst.Q_U_GET_BY_CODE_AND_PASS, user.class)
                    .setParameter(JpaConst.JPQL_PARM_CODE, code)
                    .setParameter(JpaConst.JPQL_PARM_PASSWORD, pass)
                    .getSingleResult();
        }catch(NoResultException ex) {

        }
        return UserConverter.toView(u);
    }

    public UserView findOne(int id) {
        user u = findOneInternal(id);
        return UserConverter.toView(u);
    }

    public long countByCode(String code) {
        long users_count = (long)em.createNamedQuery(JpaConst.Q_U_COUNT_REGISTERED_BY_CODE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_CODE, code)
                .getSingleResult();
        return users_count;
    }

    public List<String> create(UserView uv, String pepper){
        String pass = EncryptUtil.getPasswordEncrypt(uv.getPassword(), pepper);
        uv.setPassword(pass);

        List<String> errors = UserValidator.validate(this, uv, true, true);
        if(errors.size() == 0) {
            create(uv);
        }
        return errors;
    }

    public List<String> update(UserView uv, String pepper){
        UserView savedUser = findOne(uv.getId());
        boolean validateCode = false;
        if(!savedUser.getCode().equals(uv.getCode())) {
            validateCode = true;
            savedUser.setCode(uv.getCode());
        }

        boolean validatePass = false;
        if(uv.getPassword() != null && !uv.getPassword().equals("")) {
            validatePass = true;
            savedUser.setPassword(EncryptUtil.getPasswordEncrypt(uv.getPassword(), pepper));
        }

        savedUser.setName(uv.getName());
        savedUser.setAdminFlag(uv.getAdminFlag());

        List<String> errors = UserValidator.validate(this, savedUser, validateCode, validatePass);
        if(errors.size() == 0) {
            update(savedUser);
        }
        return errors;
    }

    public void destroy(Integer id) {
        UserView savedUser = findOne(id);
        savedUser.setDeleteFlag(JpaConst.U_DEL_TRUE);
        update(savedUser);
    }

    public Boolean validateLogin(String code, String plainPass, String pepper) {
        boolean isValidUser = false;
        if(code != null && !code.equals("") && plainPass != null && plainPass.equals("")) {
            UserView uv = findOne(code, plainPass, pepper);
            if(uv != null && uv.getId() != null) {
                isValidUser = true;
            }
        }
        return isValidUser;
    }

    private user findOneInternal(int id) {
        user u = em.find(user.class, id);
        return u;
    }

    private void create(UserView uv) {
        em.getTransaction().begin();
        em.persist(UserConverter.toModel(uv));
        em.getTransaction().commit();
    }

    private void update(UserView uv) {
        em.getTransaction().begin();
        user u = findOneInternal(uv.getId());
        UserConverter.copyViewToModel(u, uv);
        em.getTransaction().commit();
    }

}
