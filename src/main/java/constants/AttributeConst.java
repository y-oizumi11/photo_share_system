package constants;

public enum AttributeConst {
    //フラッシュメッセージ
    FLUSH("flush"),

    //一覧画面共通
    MAX_ROW("maxRow"),
    PAGE("page"),

    //入力フォーム共通
    TOKEN("_token"),
    ERR("errors"),

    //ログイン中のユーザー
    LOGIN_USER("login_user"),

    //ログイン画面
    LOGIN_ERR("loginError"),

    //ユーザー管理
    USER("user"),
    USERS("users"),
    U_COUNT("users_count"),
    U_ID("id"),
    U_CODE("code"),
    U_PASS("password"),
    U_NAME("name"),
    U_CONTENT("users_msg"),
    U_ADMIN_FLG("admin_flag"),

    //管理者フラグ
    ROLE_ADMIN(1),
    ROLE_GENERAL(0),

    //削除フラグ
    DEL_FLAG_TRUE(1),
    DEL_FLAG_FALSE(0),

    //画像管理
    IMAGE("image"),
    IMAGES("images"),
    IMG_COUNT("image_count"),
    IMG_ID("id"),
    IMG_DATE("image_date"),
    IMG_TITLE("title"),
    IMG_ADDRESS("address"),
    IMG_CONTENT("content_msg");

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }

}
