package config;

import java.sql.Connection;

@FunctionalInterface
public interface TransactionCallback {
    void doInTransaction(Connection conn) throws Exception;
}
