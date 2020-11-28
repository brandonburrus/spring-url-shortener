package com.brandonburrus.urlshortener.util;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ShortIdGenerator implements IdentifierGenerator {

    private final String tableName;
    private final IdShortener idShortener = new IdShortener();

    public ShortIdGenerator(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        try (ResultSet resultSet = session
            .connection()
            .createStatement()
            .executeQuery("SELECT COUNT(id) AS idCount FROM " + tableName)
        ) {
            if (resultSet.next()) {
                double idCount = resultSet.getDouble("idCount") + 1;
                return idShortener.shortenId(idCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
