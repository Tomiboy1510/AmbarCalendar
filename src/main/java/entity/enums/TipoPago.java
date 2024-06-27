package entity.enums;

/**
 * Represents the payment methods accepted by the business.
 */
public enum TipoPago {

    EFECTIVO,
    DEBITO,
    CREDITO,
    TRANSFERENCIA,
    MERCADOPAGO;

    /**
     * Returns the string representation of the payment method in a readable format.
     * @return a string representation of the payment method.
     */
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
