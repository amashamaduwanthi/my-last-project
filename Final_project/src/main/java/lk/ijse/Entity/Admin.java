package lk.ijse.Entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String username;
    private  String password;
    private String email;
    private  String type;
}
