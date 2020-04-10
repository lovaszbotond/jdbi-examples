package user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

     public static enum Gender {
         FEMALE,
         MALE
     }

     private Long id;
     private String username;
     private String password;
     private String name;
     private String email;
     private Gender gender;
     private LocalDate dob;
     private boolean enabled;

}
