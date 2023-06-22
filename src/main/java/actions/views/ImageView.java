package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ImageView {
    private Integer id;

    private UserView user;

    private String title;

    private String comment;

    private String address;

    private LocalDateTime created_at;
}
