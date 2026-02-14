package config;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    public void execute(TransactionCallback callback){
        try(Connection conn = DatabaseConfig.getConnection()){
            conn.setAutoCommit(false);
            try{
                callback.doInTransaction(conn);
                conn.commit();
            }catch(Exception e){
                conn.rollback();
                throw new RuntimeException("Transaction failed.Rolled back: ",e);
            }
        }catch (SQLException e){
            throw new RuntimeException("Database connection error. ",e);
        }
    }
}
