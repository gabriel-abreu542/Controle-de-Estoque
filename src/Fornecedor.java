public class Fornecedor {
    private String nome;
    private String cnpj;
    private String telefone;
    private String email;
    private String endereco;

    public Fornecedor(String n, String cnpj, String tel, String e_mail, String end){
        nome = n;
        this.cnpj = cnpj;
        telefone = tel;
        email = e_mail;
        endereco = end;
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
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

    @Override
    public String toString() {
        return "Fornecedor:\nNome: " + nome + "\n" +
                "CNPJ: " + cnpj + "\n" +
                "Telefone: " + telefone + "\n" +
                "Email: " + email + "\n" +
                "Endereço: " + endereco;
    }
}
