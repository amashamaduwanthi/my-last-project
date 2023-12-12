package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultDto {
    private String studentId;
    private String examId;
    private double mark;
}
