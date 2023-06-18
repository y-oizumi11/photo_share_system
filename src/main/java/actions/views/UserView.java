package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserView {
    private Integer id;

    private String code;

    private String name;

    private String password;

    private Integer adminFlag;

    private Integer deleteFlag;

    private String u_comment;

}
