
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import control.DepencePos;
import control.Connectory;
 
public class DepencePosDAO {

    Connection connection = null;
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public DepencePosDAO() {
    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = Connectory.getInstance().getConnection();
        return conn;
    }

    public void add(DepencePos DepencePos) {
        try {
            String queryString = "INSERT INTO VOIMALIK_ALLUVUS VALUES (?,?,'anynyymne programmikasutaja', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, DepencePos.getYlem_id());
            ptmt.setInt(2, DepencePos.getAlam_id());

            ptmt.executeUpdate();
            System.out.println("Alluvussuhe lisatud");
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

    public void update(DepencePos DepencePos) {
        try {
            String queryString = "UPDATE VOIMALIK_ALLUVUS SET VOIMALIK_ALLUV_LIIK_ID=? WHERE RIIGI_ADMIN_YKSUSE_LIIK_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, DepencePos.getAlam_id());
            ptmt.setInt(2, DepencePos.getYlem_id());
            ptmt.executeUpdate();
            System.out.println("Alluvussuhe muudetud");
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

    public void delete(int id_ylem, int id_alam) {
        try {
            String queryString = "DELETE FROM VOIMALIK_ALLUVUS WHERE RIIGI_ADMIN_YKSUSE_LIIK_ID=? AND VOIMALIK_ALLUV_LIIK_ID=?";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, id_ylem);
            ptmt.setInt(2, id_alam);
            ptmt.executeUpdate();
            System.out.println("Alluvussuhe kustutatud");
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

 
    public void findAll() {
        try {
            String queryString = "SELECT * FROM VOIMALIK_ALLUVUS";

            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.executeUpdate();
            while (resultSet.next()) {
                System.out.println("Id ülem" + resultSet.getInt("RIIGI_ADMIN_YKSUSE_LIIK_ID")
                        + ", alluvüksuse id" + resultSet.getString("VOIMALIK_ALLUV_LIIK_ID"));
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
