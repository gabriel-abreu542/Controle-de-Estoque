public class Cliente {
    private String nome;
    private String telefone;
    private boolean endividado;
    private float divida;

    public Cliente(String n, String tel){
        nome = n;
        telefone = tel;
        endividado = false;
        divida = 0;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean temDivida() {
        return endividado;
    }

    public void setDivida(float d) {
        if (d < 0){
            throw new IllegalArgumentException("Valor da dívida deve ser igual ou maior que zero");
        }
        this.divida = d;
        if(divida > 0){
            endividado = true;
        }
        else {
            endividado = false;
        }

    }

    @Override
    public String toString() {
        String detalhes = "Nome: " + nome + " - Telefone: " + telefone;
        if(endividado){
            detalhes = detalhes + " (ENDIVIDADO, R$" + divida + ")";
        }
        detalhes = detalhes + "\n";


        return detalhes;
    }
}
