package lk.ijse.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {
    private   String id;
    private   double amount;
    private String date;
    private   String status;
    private   String classId;
    private   String stuId;
}
