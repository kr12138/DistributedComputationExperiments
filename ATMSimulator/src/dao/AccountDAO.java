package dao;

public interface AccountDAO extends DAO {
    public Account findByName(String name) throws DAOException;
    public boolean updateSavingsByName(String name, long cash) throws DAOException;
}
