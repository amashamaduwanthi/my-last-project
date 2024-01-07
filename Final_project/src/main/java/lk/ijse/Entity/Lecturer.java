package lk.ijse.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Lecturer {
    private String id;
    private String name;
    private String address;
    private int tel;
    private String Nic;
    private  String university;
}
