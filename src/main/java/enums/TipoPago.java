package enums;

public enum TipoPago {

    EFECTIVO,
    DEBITO,
    CREDITO,
    TRANSFERENCIA,
    MERCADOPAGO;

    @Override
    public String toString() {
        return switch (this) {
            case EFECTIVO -> "Efectivo";
            case DEBITO -> "Débito";
            case CREDITO -> "Crédito";
            case TRANSFERENCIA -> "Transferencia bancaria";
            case MERCADOPAGO -> "MercadoPago";
        };
    }
}
