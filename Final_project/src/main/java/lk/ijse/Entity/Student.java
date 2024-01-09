package lk.ijse.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private String id;
    private String name;
    private String address;
    private String email;
    private int contactNo;
    private String gender;
    private String dateOfBirth;
}
