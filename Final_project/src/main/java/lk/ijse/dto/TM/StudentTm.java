package lk.ijse.dto.TM;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentTm {
    private String stuId;
    private String name;
    private String address;
    private String email;
    private int contactNo;
    private String gender;
    private  String dateOfBirth;

}
