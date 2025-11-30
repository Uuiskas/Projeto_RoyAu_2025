package model;

import enums.TipoPelo;
import exceptions.RegraNegocioException;

public class Tosa extends Servico {

    // Atributo para armazenar o tipo de pelo específico que o Pet tem (para o construtor)
    private final TipoPelo tipoPelo;

    //  nome, valorBase, data E o TipoPelo.
    public Tosa(String nome, double valorBase, String data, TipoPelo tipoPelo) {
        super(nome, valorBase, data); // ⬅️ Chama o construtor do Pai (Servico)
        this.tipoPelo = tipoPelo;
    }

    //  Método CORRIGIDO: Usa a assinatura 'calcularCustoTotal' do Pai
    @Override
    public double calcularCustoTotal(Pet pet) {
        double custo = this.valorBase; // ⬅️ Usa valorBase do Pai

        // Regra de Negócio: Adicional por Tipo de Pelo
        switch(pet.getTipoPelo()){
            case CURTO:

                break;
            case LONGO:
                custo += 15.00;
                break;
            case ESPECIAL:
                custo += 25.00;
                break;
            default:

                break;
        }

        if (custo <= 0) {
            throw new RegraNegocioException("O valor da Tosa resultou em custo zero ou negativo.");
        }
        return custo;
    }
}