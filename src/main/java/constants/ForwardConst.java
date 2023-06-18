package constants;

public enum ForwardConst {
  //action
    ACT("action"),
    ACT_TOP("Top"),
    ACT_USER("User"),
    ACT_IMG("Image"),
    ACT_AUTH("Auth"),

    //command
    CMD("command"),
    CMD_NONE(""),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),

    //jsp
    FW_ERR_UNKNOWN("error/unknown"),
    FW_TOP_INDEX("topPage/index"),
    FW_LOGIN("login/login"),
    FW_U_INDEX("users/index"),
    FW_U_SHOW("users/show"),
    FW_U_NEW("users/new"),
    FW_U_EDIT("users/edit"),
    FW_IMG_INDEX("images/index"),
    FW_IMG_SHOW("images/show"),
    FW_IMG_NEW("images/new"),
    FW_IMG_EDIT("images/edit");

    /**
     * 文字列
     */
    private final String text;

    /**
     * コンストラクタ
     */
    private ForwardConst(final String text) {
        this.text = text;
    }

    /**
     * 値(文字列)取得
     */
    public String getValue() {
        return this.text;
    }

}
