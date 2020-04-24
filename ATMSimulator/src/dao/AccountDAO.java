package dao;

public interface AccountDAO extends DAO {
    Account findByName(String name) throws DAOException;
    boolean updateSavingsByName(String name, long cash) throws DAOException;
    boolean addWrongCountByName(String name) throws DAOException;
    boolean clearWrongCountByName(String name) throws DAOException;
}
