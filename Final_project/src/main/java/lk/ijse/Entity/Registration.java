package lk.ijse.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Registration {
    private String regId;
    private String name;
    private String email;
    private String date;
    private String parentId;
    private String userName;
}
