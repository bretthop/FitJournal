package au.com.fitjournal.data.dao;

import au.com.fitjournal.data.connection.ConnectionManager;
import au.com.fitjournal.data.entity.JournalEntry;
import au.com.fitjournal.model.EntryType;
import au.com.fitjournal.util.DateUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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

    public List<JournalEntry> findBetweenDates(Date startDate, Date endDate)
    {
        try (
                Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                    String.format(
                        "SELECT * FROM journal_entry WHERE entryTime >= '%s' AND entryTime <= '%s' ORDER BY entryTime",
                        DateUtil.format(startDate, DateUtil.FULL_DATE_FORMAT), DateUtil.format(endDate, DateUtil.FULL_DATE_FORMAT)
                    )
                )
        ) {
            return this.mapResultsToList(rs);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    public JournalEntry findById(long id)
    {
        try (
                Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM journal_entry WHERE id = %s", id))
        ) {
            return this.mapResultsToEntry(rs);
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    public JournalEntry update(JournalEntry entity)
    {
        try (
                Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            String sql = String.format("UPDATE journal_entry SET name = '%s', kj = %s, protein = %s, entryTime = '%s', type = '%s' WHERE id = %s",
                                        entity.getName(), entity.getKj(), entity.getProtein(), entity.getEntryTime(), entity.getType().name(), entity.getId());

            stmt.executeUpdate(sql);

            return entity;
        }
        catch (Exception e) {
            // TODO: Add logging
            throw new RuntimeException(e);
        }
    }

    public JournalEntry insert(JournalEntry entity)
    {
        try (
                Connection conn = ConnectionManager.getConnection();
                Statement stmt = conn.createStatement()
        ) {
            String sql = "INSERT INTO journal_entry (name, kj, protein, entryTime, type) VALUES ";
            sql += String.format("('%s', %s, %s, '%s', '%s')", entity.getName(), entity.getKj(), entity.getProtein(), entity.getEntryTime(), entity.getType().name());

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
            JournalEntry entity = this.fromResult(rs);

            entities.add(entity);
        }

        return entities;
    }

    private JournalEntry mapResultsToEntry(ResultSet rs) throws SQLException
    {
        JournalEntry entity = null;

        if (rs.next()) {
            entity = this.fromResult(rs);
        }

        return entity;
    }

    /**
     * @param rs - Should have a valid result lined up and ready to be accessed
     * @return the mapped entity
     */
    private JournalEntry fromResult(ResultSet rs) throws SQLException
    {
        JournalEntry entity = new JournalEntry();

        entity.setId(rs.getLong("id"));
        entity.setName(rs.getString("name"));
        entity.setKj(rs.getBigDecimal("kj"));
        entity.setProtein(rs.getBigDecimal("protein"));
        entity.setEntryTime(rs.getTimestamp("entryTime"));
        entity.setType(EntryType.valueOf(rs.getString("type")));

        return entity;
    }
}