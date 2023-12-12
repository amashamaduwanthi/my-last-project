package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PayementDto {
          private   String id;
          private   double amount;
           private String date;
          private   String status;
          private   String classId;
          private   String stuId;
    }

