package enums;

public enum TipoPagamento {
    PIX,
    CARTAO,
    DINHEIRO,
    PENDENTE;


    public static TipoPagamento fromInt(int op) {
        return switch (op) {
            case 1 -> PIX;
            case 2 -> CARTAO;
            case 3 -> DINHEIRO;
            default -> PENDENTE;
        };
    }
}