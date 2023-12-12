package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class paymentDto {
    private String id;
    private double amount;
    private String date;
    private String status;
    private String classId;


}
