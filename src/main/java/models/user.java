package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_USER)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_U_GET_ALL,
            query = JpaConst.Q_U_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_U_COUNT,
            query = JpaConst.Q_U_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_U_GET_BY_CODE_AND_PASS,
            query = JpaConst.Q_U_GET_BY_CODE_AND_PASS_DEF),
    @NamedQuery(
            name = JpaConst.Q_U_COUNT_REGISTERED_BY_CODE,
            query = JpaConst.Q_U_COUNT_REGISTERED_BY_CODE_DEF)

})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class user {
    @Id
    @Column(name = JpaConst.U_COL_ID)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = JpaConst.U_COL_CODE, nullable = false, unique = true)
    private String code;

    @Column(name = JpaConst.U_COL_NAME, nullable = false)
    private String name;

    @Column(name = JpaConst.U_COL_PASS, length = 64, nullable = false)
    private String password;

    @Column(name = JpaConst.U_COL_ADMIN_FLAG, nullable = false)
    private Integer adminFlag;

    @Column(name = JpaConst.U_COL_DELETE_FLAG, nullable = false)
    private Integer deleteFlag;

    @Column(name = JpaConst.U_COL_COMMENT, nullable = false)
    private String u_comment;

}
