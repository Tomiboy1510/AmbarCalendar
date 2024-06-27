package entity.enums;

/**
 * Represents the types of services provided by the business.
 */
@SuppressWarnings("NonAsciiCharacters")
public enum Servicio {

    DEPILACION_LASER,
    MANIPEDICURA,
    CEJAS_PESTAÑAS,
    DERMATOCOSMIATRIA,
    MAQUILLAJE,
    HIFU,
    ESTETICA_CORPORAL;

    /**
     * Returns the string representation of the service type in a readable format.
     * @return a string representation of the service type.
     */
    @Override
    public String toString() {
        return switch (this) {
            case MAQUILLAJE -> "Maquillaje";
            case HIFU -> "Hifu";
            case MANIPEDICURA -> "Manicura y pedicura";
            case CEJAS_PESTAÑAS -> "Cejas y pestañas";
            case DEPILACION_LASER -> "Depilación láser";
            case ESTETICA_CORPORAL -> "Estética corporal";
            case DERMATOCOSMIATRIA -> "Dermatocosmiatría";
        };
    }
}
