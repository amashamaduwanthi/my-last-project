package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RegistrationDto {

        private String regId;
        private String name;
        private String email;
        private String date;
        private String parentId;
        private String userName;
    }


