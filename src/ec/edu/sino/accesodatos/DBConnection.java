/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.sino.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author alexnder
 */
public class DBConnection {

    final private String DRIVER = "org.postgresql.Driver";
    private String user;
    private String password;
    private Connection connection;
    final private String URL = "jdbc:postgresql://localhost:5432/sino";

    public DBConnection() {
    }

    public DBConnection(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public Connection connect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName(DRIVER);
            try {
                connection = DriverManager.getConnection(URL, user, password);
            } catch (SQLException e) {
                throw e;
            }
        } catch (ClassNotFoundException e) {
            throw e;
        }
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw e;
            }
        }
    }

    public ResultSet executeQuery(String sql) throws ClassNotFoundException, SQLException {
        ResultSet rst = null;

        try {
            connect();
            Statement stm = connection.createStatement();
            rst = stm.executeQuery(sql);
        } catch (SQLException e) {
            throw e;
        } finally {
            disconnect();
        }
        return rst;
    }

    public ResultSet executeQuery(String sql, List<DBObject> lst) throws Exception {
        ResultSet rst = null;
        try {
            connect();
            PreparedStatement pstm = connection.prepareStatement(sql);
            for (DBObject par : lst) {
                if (par.getValue() instanceof java.util.Date) {
                    pstm.setObject(par.getPosition(),
                            new java.sql.Date(((java.util.Date) par.getValue()).getTime()));
                } else {
                    pstm.setObject(par.getPosition(), par.getValue());
                }
            }
            rst = pstm.executeQuery();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            disconnect();
        }
        return rst;
    }

    public int executeCommand(String sql) throws Exception {
        int affectedrows = 0;
        try {
            connect();
            Statement stm = connection.createStatement();
            affectedrows = stm.executeUpdate(sql);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            disconnect();
        }
        return affectedrows;
    }

    public int executeCommand(String sql, List<DBObject> lst) throws Exception {
        int affectedrows = 0;
        try {
            connect();
            PreparedStatement pstm = connection.prepareStatement(sql);
            for (DBObject dbo : lst) {
                if (dbo.getValue() instanceof java.util.Date) {
                    pstm.setObject(dbo.getPosition(),
                            new java.sql.Date(((java.util.Date) dbo.getValue()).getTime()));
                } else {
                    pstm.setObject(dbo.getPosition(), dbo.getValue());
                }
            }
            affectedrows = pstm.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            disconnect();
        }
        return affectedrows;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
