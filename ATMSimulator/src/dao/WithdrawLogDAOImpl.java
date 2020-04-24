package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WithdrawLogDAOImpl implements WithdrawLogDAO {

    @Override
    public void insert(WithdrawLog log) {
        String sql = "INSERT INTO withdrawLog VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, log.getName());
            ps.setLong(2, log.getBefore());
            ps.setLong(3, log.getAfter());
            ps.setString(4, log.getTime());
            int count = ps.executeUpdate();
            System.out.println(" insert count:"+count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
