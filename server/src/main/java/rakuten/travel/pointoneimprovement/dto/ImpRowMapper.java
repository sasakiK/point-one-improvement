package rakuten.travel.pointoneimprovement.dto;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImpRowMapper implements RowMapper<Imp> {
    @Override
    public Imp mapRow(ResultSet rs, int rowNum) throws SQLException {
        Imp imp = new Imp();
        imp.setId(rs.getInt("id"));
        imp.setContent(rs.getString("content"));
        imp.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        imp.setUserId(rs.getInt("user_id"));
        return imp;
    }

}
