package user;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(User.class)
public interface UserDAO {

    @SqlUpdate("""
    CREATE TABLE user (
        ID IDENTITY PRIMARY KEY,
        username VARCHAR UNIQUE,
        password VARCHAR NOT NULL,
        name VARCHAR NOT NULL,
        email VARCHAR NOT NULL,
        gender VARCHAR NOT NULL,
        dob DATE NOT NULL,
        enabled BIT NOT NULL
        )
""")

    void createTable();

    @SqlUpdate("""
            INSERT INTO user (username, password, name, email, gender, dob, enabled) VALUES
                             (:username, :password, :name, :email, :gender, :dob, :enabled)
            """)
    @GetGeneratedKeys
    Long insert(@BindBean User user);
    @SqlQuery("SELECT * FROM User WHERE id = :id")
    Optional<User> findByID(@Bind("id") Long id);
    @SqlQuery("SELECT * FROM User WHERE username = :username")
    Optional<User> findByUsername(@Bind("username") String username);
    @SqlUpdate("DELETE FROM User WHERE id = :id")
    void delete(@BindBean User user);
    @SqlQuery("SELECT * FROM user ORDER BY username")
    List<User> list();
}
