package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOImpl implements AccountDAO {

    @Override
    public Account findByName(String name) throws DAOException {
        String sql = "SELECT * FROM account WHERE(name = ?)";
        Account a = new Account();
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    a.setName(rs.getString("name"));
                    a.setPassword(rs.getLong("password"));
                    a.setWrongCount(rs.getShort("wrongCount"));
                    a.setSavings(rs.getLong("savings"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return a;
    }

    @Override
    public boolean updateSavingsByName(String name, long cash) throws DAOException {
        String sql = "UPDATE account SET savings = savings + ? WHERE(name = ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, cash);
            ps.setString(2, name);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addWrongCountByName(String name) throws DAOException {
        String sql = "UPDATE account SET wrongCount = wrongCount + 1 WHERE(name = ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clearWrongCountByName(String name) throws DAOException {
        String sql = "UPDATE account SET wrongCount = 0 WHERE(name = ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, name);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
