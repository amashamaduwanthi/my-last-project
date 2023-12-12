package lk.ijse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentClassDto {
    private String id;
    private String classId;
    private String subName;
    private String lecName;
}
