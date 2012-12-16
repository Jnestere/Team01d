
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import control.Connectory;
import control.AdminEss;


public class AdminEssDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public AdminEssDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = Connectory.getInstance().getConnection();
        return conn;
    }

    public void add(AdminEss AdminEss) {
        try {
            String queryString = "INSERT INTO RIIGI_ADMIN_YKSUS VALUES (null,?,?,?,'anynyymne programmikasutaja', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',? )";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, AdminEss.getKood());
            ptmt.setString(2, AdminEss.getNimetus());
            ptmt.setString(3, AdminEss.getKommentaar());
            ptmt.setInt(4, AdminEss.getFk());
            ptmt.executeUpdate();
            System.out.println("Administratiivüksus lisatud");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }

    public void update(AdminEss AdminEss) {
        try {
            String queryString = "UPDATE RIIGI_ADMIN_YKSUS SET KOOD=?,NIMETUS = ?,KOMMENTAAR=?,RIIGI_ADMIN_YKSUSE_LIIK_ID=? WHERE RIIGI_ADMIN_YKSUS_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, AdminEss.getKood());
            ptmt.setString(2, AdminEss.getNimetus());
            ptmt.setString(3, AdminEss.getKommentaar());
            ptmt.setInt(5, AdminEss.getFk());
            ptmt.setInt(5, AdminEss.getId());
            ptmt.executeUpdate();
            System.out.println("Administratiivüksus uuendatud");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }

    public void delete(int id) {
        try {
            String queryString = "DELETE FROM RIIGI_ADMIN_YKSUS WHERE RIIGI_ADMIN_YKSUS_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
            System.out.println("Administratiivüksus kustutatud");
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }

    /**
     * Meetod annab kõik tabeli kirjed, while loopi saab panna muud targemat ka
     * tegema nt. mingit kollektsiooni
     */
    public void findAll() {
        try {
            String queryString = "SELECT * FROM RIIGI_ADMIN_YKSUS";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.executeUpdate();
            while (resultSet.next()) {
                System.out.println("Id " + resultSet.getInt("RIIGI_ADMIN_YKSUS_ID")
                        + ", kood" + resultSet.getString("KOOD") + ", nimetus "
                        + resultSet.getString("NIMETUS") + ", Kommentaar "
                        + resultSet.getString("KOMMENTAAR"));
            }

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            try {
                if (ptmt != null) {
                    ptmt.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.getMessage();
            } catch (Exception e) {
                e.getMessage();
            }

        }

    }
}
