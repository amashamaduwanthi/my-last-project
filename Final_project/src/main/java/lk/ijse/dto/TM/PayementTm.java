package lk.ijse.dto.TM;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PayementTm {
    private String id;
    private double amount;
    private String date;
    private String status;
    private String classId;
    private String stuId;
}
