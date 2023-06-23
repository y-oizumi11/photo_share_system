package models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = JpaConst.TABLE_IMG)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_IMG_GET_ALL,
            query = JpaConst.Q_IMG_GET_ALL_DEF
            ),
    @NamedQuery(
            name = JpaConst.Q_IMG_COUNT,
            query = JpaConst.Q_IMG_COUNT_DEF),
    @NamedQuery(
            name = JpaConst.Q_IMG_GET_ALL_MINE,
            query = JpaConst.Q_IMG_GET_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_IMG_COUNT_ALL_MINE,
            query = JpaConst.Q_IMG_COUNT_ALL_MINE_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {
    @Id
    @Column(name = JpaConst.IMG_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = JpaConst.IMG_COL_U, nullable = false)
    private user user;

    @Column(name = JpaConst.IMG_COL_TITLE, length = 255, nullable = false)
    private String title;

    @Lob
    @Column(name = JpaConst.IMG_COL_COMMENT, nullable = false)
    private String comment;

    @Column(name = JpaConst.IMG_COL_ADDRESS, nullable = false)
    private String address;

    @Column(name = JpaConst.IMG_COL_CREATED_AT, nullable = false)
    private LocalDate created_at;

}
