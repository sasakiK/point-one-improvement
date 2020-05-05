package rakuten.travel.pointoneimprovement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import rakuten.travel.pointoneimprovement.dto.User;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer getUserId(String email) {
        String sql = "SELECT id FROM user WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);
    }

    public Integer saveUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO user (email) VALUES (?);";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

}
