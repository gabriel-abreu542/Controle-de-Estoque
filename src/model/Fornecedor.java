package model;

public class Fornecedor implements Cadastravel{
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;

    public Fornecedor(String cnpj, String n, String tel, String e_mail, String end){
        this.cnpj = cnpj;
        nome = n;
        telefone = tel;
        email = e_mail;
        endereco = end;
    }

    public String getNome() {
        return nome;
    }

    public String getId() {
        return cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fornecedor:\nNome: " + nome + "\n" +
                "CNPJ: " + cnpj + "\n" +
                "Telefone: " + telefone + "\n" +
                "Email: " + email + "\n" +
                "Endereço: " + endereco;
    }
}
