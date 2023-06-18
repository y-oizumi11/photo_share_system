package constants;

public interface JpaConst {
    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "image_share_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 12; //1ページに表示するレコードの数

    //ユーザーテーブル
    String TABLE_USER = "users"; //テーブル名
    //ユーザーテーブルカラム
    String U_COL_ID = "id"; //id
    String U_COL_CODE = "code"; //ユーザー名
    String U_COL_NAME = "name"; //氏名
    String U_COL_PASS = "password"; //パスワード
    String U_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String U_COL_DELETE_FLAG = "delete_flag"; //削除フラグ
    String U_COL_COMMENT = "u_comment"; //ユーザーコメント

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int U_DEL_TRUE = 1; //削除フラグON(削除済み)
    int U_DEL_FALSE = 0; //削除フラグOFF(現役)

    //画像テーブル
    String TABLE_IMG = "images"; //テーブル名
    //画像テーブルカラム
    String IMG_COL_ID = "id"; //id
    String IMG_COL_U = "user_id"; //写真を投稿したユーザーのid
    String IMG_COL_TITLE = "title"; //画像のタイトル
    String IMG_COL_COMMENT = "comment"; //画像へのコメント
    String IMG_COL_ADRESS = "adress"; //画像の場所
    String IMG_COL_CREATED_AT = "created_at"; //投稿日時

    //Entity名
    String ENTITY_USER = "user"; //ユーザー
    String ENTITY_IMG = "image"; //画像

    //JPQL内パラメータ
    String JPQL_PARM_CODE = "code"; //ユーザーID
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_USER = "user"; //ユーザー

    //NamedQueryの nameとquery
    //全てのユーザーをidの降順に取得する
    String Q_U_GET_ALL = ENTITY_USER + ".getAll"; //name
    String Q_U_GET_ALL_DEF = "SELECT u FROM user AS u ORDER BY u.id DESC"; //query
    //全てのユーザーの件数を取得する
    String Q_U_COUNT = ENTITY_USER + ".count";
    String Q_U_COUNT_DEF = "SELECT COUNT(u) FROM user AS u";
    //ユーザーIDとハッシュ化済パスワードを条件に未削除のユーザーを取得する
    String Q_U_GET_BY_CODE_AND_PASS = ENTITY_USER + ".getByCodeAndPass";
    String Q_U_GET_BY_CODE_AND_PASS_DEF = "SELECT u FROM user AS u WHERE u.deleteFlag = 0 AND u.code = :" + JPQL_PARM_CODE + " AND u.password = :" + JPQL_PARM_PASSWORD;
    //指定したユーザーIDを保持するユーザーの件数を取得する
    String Q_U_COUNT_REGISTERED_BY_CODE = ENTITY_USER + ".countRegisteredByCode";
    String Q_U_COUNT_REGISTERED_BY_CODE_DEF = "SELECT COUNT(u) FROM user AS u WHERE u.code = :" + JPQL_PARM_CODE;
    //全ての画像をidの降順に取得する
    String Q_IMG_GET_ALL = ENTITY_IMG + ".getAll";
    String Q_IMG_GET_ALL_DEF = "SELECT i FROM Image AS i ORDER BY i.id DESC";
    //全ての画像の件数を取得する
    String Q_IMG_COUNT = ENTITY_IMG + ".count";
    String Q_IMG_COUNT_DEF = "SELECT COUNT(i) FROM Image AS i";
    //指定したユーザーが作成した画像を全件idの降順で取得する
    String Q_IMG_GET_ALL_MINE = ENTITY_IMG + ".getAllMine";
    String Q_IMG_GET_ALL_MINE_DEF = "SELECT i FROM Image AS i WHERE i.user = :" + JPQL_PARM_USER + " ORDER BY i.id DESC";
    //指定したユーザーが作成した画像の件数を取得する
    String Q_IMG_COUNT_ALL_MINE = ENTITY_IMG + ".countAllMine";
    String Q_IMG_COUNT_ALL_MINE_DEF = "SELECT COUNT(i) FROM Image AS i WHERE i.user = :" + JPQL_PARM_USER;

}
