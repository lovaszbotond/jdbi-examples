package user;


import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.w3c.dom.ls.LSOutput;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            UserDAO dao = handle.attach(UserDAO.class);
            dao.createTable();
            User user = User.builder()
                    .name("Hulk")
                    .username("zoldmajom")
                    .password("TheStrongestAvenger")
                    .email("imhulk@avengers.com")
                    .gender(User.Gender.MALE)
                    .enabled(true)
                    .dob(LocalDate.parse("1941-12-24"))
                    .build();
            dao.insert(user);
            User user2 = User.builder()
                    .name("Thor")
                    .username("Asgard")
                    .password("")
                    .email("imthor@avengers.com")
                    .gender(User.Gender.MALE)
                    .enabled(true)
                    .dob(LocalDate.parse("1923-11-01"))
                    .build();
            dao.insert(user2);
            User user3 = User.builder()
                    .name("Tony Stark")
                    .username("Ironman")
                    .password("Money")
                    .email("imironman@avengers.com")
                    .gender(User.Gender.MALE)
                    .enabled(true)
                    .dob(LocalDate.parse("1993-05-13"))
                    .build();
            dao.insert(user3);
            try {
                dao.delete(dao.findByID(Long.valueOf(2)).get());
            }catch(NoSuchElementException e)
            {
                e.printStackTrace();
            }

            try {
               dao.delete(dao.findByUsername("zoldmajom").get());
            }catch(NoSuchElementException e)
            {
                e.printStackTrace();
            }

            System.out.println("-----------------------------------");
            dao.list().stream().forEach(System.out::println);
            System.out.println("-----------------------------------");

        }
    }
}
