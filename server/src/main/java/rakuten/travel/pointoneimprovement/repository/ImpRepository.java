package rakuten.travel.pointoneimprovement.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import rakuten.travel.pointoneimprovement.dto.Imp;
import rakuten.travel.pointoneimprovement.dto.ImpRowMapper;

import javax.swing.plaf.nimbus.State;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class ImpRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllImps() {
        String sql = "SELECT * FROM improvement";
        return jdbcTemplate.queryForList(sql);
    }

    public Imp getImpById(Integer id) {
        String sql = "SELECT * FROM improvement WHERE id = ?;";
        Imp tmpImp = jdbcTemplate.queryForObject(sql, new Object[]{id}, new ImpRowMapper());
        return tmpImp;
    }

    public Integer saveImp(Imp imp) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        LocalDateTime createdAt = LocalDateTime.now();
        String sql = "INSERT INTO improvement (content, created_at) VALUES (?, ?);";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, imp.getContent());
            ps.setString(2, createdAt.toString());
            return ps;
            }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Integer updateImp(Imp imp, Integer id) {
        String sql = "UPDATE improvement SET content = ? WHERE id = ?";
        jdbcTemplate.update(sql, imp.getContent(), id.toString());
        return id;
    }

    public boolean deleteImpById(Integer id){
        String sql = "DELETE FROM improvement WHERE id = ?";
        Object[] arg = new Object[] {id};

        return jdbcTemplate.update(sql, arg) == 1;
    }
}
