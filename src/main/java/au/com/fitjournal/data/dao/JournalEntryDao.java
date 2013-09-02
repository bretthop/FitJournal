package au.com.fitjournal.data.dao;

import au.com.fitjournal.data.connection.ConnectionManager;
import au.com.fitjournal.data.entity.JournalEntry;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JournalEntryDao
{
    public List<JournalEntry> findAll()
    {
        try (
                Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM journal_entry ORDER BY entryTime"))
        ) {
            return this.mapResultsToList(rs);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    public JournalEntry save(JournalEntry entity)
    {
        try (
                Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            String sql = "INSERT INTO journal_entry (name, kj, entryTime) VALUES ";
            sql += String.format("('%s', %s, '%s')", entity.getName(), entity.getKj(), entity.getEntryTime());

            stmt.executeUpdate(sql);

            return entity;
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    private List<JournalEntry> mapResultsToList(ResultSet rs) throws SQLException
    {
        List<JournalEntry> entities = new ArrayList<>();

        while (rs.next()) {
            JournalEntry entity = new JournalEntry();

            entity.setId(rs.getLong("id"));
            entity.setName(rs.getString("name"));
            entity.setKj(rs.getBigDecimal("kj"));
            entity.setEntryTime(rs.getTimestamp("entryTime"));

            entities.add(entity);
        }

        return entities;
    }
}
