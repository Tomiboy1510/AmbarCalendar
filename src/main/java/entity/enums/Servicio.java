package entity.enums;

public enum Servicio {

    DEPILACION_LASER,
    MANIPEDICURA,
    CEJAS_PESTAÑAS,
    DERMATOCOSMIATRIA,
    MAQUILLAJE,
    HIFU,
    ESTETICA_CORPORAL;

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
