package org.me.fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorConecta {
    private static final String URL = "jdbc:derby://localhost:1527/BDFornecedor";
    private static final String USERNAME = "EMPRESA";
    private static final String PASSWORD = "empresa";
    private Connection connection = null;
    
    private PreparedStatement insereFornecedor = null;
    private PreparedStatement selecionaFornecedor = null;
    private PreparedStatement alteraFornecedor = null;
    private PreparedStatement excluiFornecedor = null;
    
    public FornecedorConecta() throws ClassNotFoundException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            insereFornecedor = connection.prepareStatement(
                    "INSERT INTO EMPRESA.TABFORNECEDOR " + "(NOME , CNPJ, EMAIL, ENDERECO, CIDADE, ESTADO, TELEFONE) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            
            selecionaFornecedor = connection.prepareStatement(
            "SELECT * FROM EMPRESA.TABFORNECEDOR WHERE NOME = ?" );
            
            alteraFornecedor = connection.prepareStatement(
            "UPDATE EMPRESA.TABFORNECEDOR SET NOME = ?, CNPJ = ?, EMAIL = ?, ENDERECO = ?, CIDADE = ?, ESTADO = ?, "
                    + "TELEFONE = ?" + "WHERE ID = ?");
            
            excluiFornecedor = connection.prepareStatement("DELETE FROM EMPRESA.TABFORNECEDOR " + "WHERE ID = ?");
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }
        
        public int adicionaFornecedor(String nome, String cnpj, String mail, String endereco, String cidade, String estado, String fone) {
        int result = 0;
        try {
            insereFornecedor.setString(1, nome);
            insereFornecedor.setString(2, cnpj);
            insereFornecedor.setString(3, mail);
            insereFornecedor.setString(4, endereco);
            insereFornecedor.setString(5, cidade);
            insereFornecedor.setString(6, estado);
            insereFornecedor.setString(7, fone);

            result = insereFornecedor.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }
    
    public int atualizaFornecedor(
            String nome, String cnpj, String mail, String endereco, String cidade, String estado, String fone, int cod) {
        int result = 0;
        try {
            alteraFornecedor.setString(1, nome);
            alteraFornecedor.setString(2, cnpj);
            alteraFornecedor.setString(3, mail);
            alteraFornecedor.setString(4, endereco);
            alteraFornecedor.setString(5, cidade);
            alteraFornecedor.setString(6, estado);
            alteraFornecedor.setString(7, fone);
            alteraFornecedor.setInt(8, cod);
            result = alteraFornecedor.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return result;
    }
    
    public boolean deletaFornecedor(int id) {
        boolean exclui = false;
        try {
            excluiFornecedor.clearParameters();
            excluiFornecedor.setInt(1, id);
            excluiFornecedor.executeUpdate();
            exclui = true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return exclui;
    }
    
public List<Fornecedor> getNome(String nome) {
        List<Fornecedor> resultados = null;
        ResultSet resultSet = null;
        try {
            selecionaFornecedor.setString(1, nome);
            resultSet = selecionaFornecedor.executeQuery();
            resultados = new ArrayList<Fornecedor>();
            while (resultSet.next()) {
                resultados.add(new Fornecedor(
                        resultSet.getInt("ID"),
                        resultSet.getString("NOME"),
                        resultSet.getString("CNPJ"),
                        resultSet.getString("EMAIL"),
                        resultSet.getString("ENDERECO"),
                        resultSet.getString("CIDADE"),
                        resultSet.getString("ESTADO"),
                        resultSet.getString("TELEFONE")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
        return resultados;
    }


    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            close();
        }
    }
}
