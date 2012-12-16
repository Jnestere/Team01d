
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import control.AdminType;
import control.Connectory;

public class AdminTypeDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public AdminTypeDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = Connectory.getInstance().getConnection();
        return conn;
    }

    public void add(AdminType AdminType) {
        try {
            String queryString = "INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,?,?,?,'anynyymne programmikasutaja', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, AdminType.getKood());
            ptmt.setString(2, AdminType.getNimetus());
            ptmt.setString(3, AdminType.getKommentaar());
            ptmt.executeUpdate();
            System.out.println("Administratiivüksuse liik lisatud");
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

    public void update(AdminType AdminType) {
        try {
            String queryString = "UPDATE RIIGI_ADMIN_YKSUSE_LIIK SET KOOD=?,NIMETUS = ?,KOMMENTAAR=? WHERE RIIGI_ADMIN_YKSUSE_LIIK_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, AdminType.getKood());
            ptmt.setString(2, AdminType.getNimetus());
            ptmt.setString(3, AdminType.getKommentaar());
            ptmt.setInt(4, AdminType.getId());
            ptmt.executeUpdate();
            System.out.println("Administratiivüksuse liik uuendatud");
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
            String queryString = "DELETE FROM RIIGI_ADMIN_YKSUSE_LIIK WHERE RIIGI_ADMIN_YKSUSE_LIIK_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
            System.out.println("Administratiivüksuse liigi andmed kustutatud");
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
     *  Meetod annab kõik tabeli kirjed, while loopi saab panna muud targemat ka tegema nt. mingit kollektsiooni
     */
    public void findAll() {
        try {
            String queryString = "SELECT * FROM RIIGI_ADMIN_YKSUSE_LIIK";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.executeUpdate(); 
            while (resultSet.next()) {
				System.out.println("Id " + resultSet.getInt("RIIGI_ADMIN_YKSUSE_LIIK_ID")
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
