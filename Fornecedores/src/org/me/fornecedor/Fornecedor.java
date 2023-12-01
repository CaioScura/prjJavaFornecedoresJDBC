package org.me.fornecedor;

public class Fornecedor {
    private int id;
    private String nome;
    private String cnpj;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String telefone;

    public Fornecedor(int id, String nome, String cnpj, String email, String endereco, String cidade, String estado, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
    
}
