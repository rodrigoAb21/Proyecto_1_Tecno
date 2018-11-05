package utilidades;

public class Ejemplo {

    // ---------------------------------- UNIDAD DE MEDIDA ------------------------------------------

    public static String RegistrarUnidadMedida = "RegistrarUnidadMedida:nombre// " +
            "\nEjemplo -> RegistrarUnidadMedida:Kilogramo//";

    public static String EditarUnidadMedida = "EditarUnidadMedida:id//nombreNuevo// " +
            "\nEjemplo -> EditarUnidadMedida:2//Metro//";

    public static String EliminarUnidadMedida = "EliminarUnidadMedida:id// " +
            "\nEjemplo -> EliminarUnidadMedida:2//";

    public static String ListarUnidadMedida = "ListarUnidadMedida:" +
            "\nEjemplo -> ListarUnidadMedida:";

    // ---------------------------------- USUARIO ------------------------------------------

    public static String RegistrarUsuario = "RegistrarUsuario:nombre//apellido//carnet//email// " +
            "\nEjemplo -> RegistrarUsuario:Juan//Perez//3342342//juan.perez@gmail.com//";

    public static String EditarUsuario = "EditarUsuario:id//nombre//apellido//carnet//email// " +
            "\nEjemplo -> EditarUsuario:2//Juan Jose//Apaza//4435232//juan.jose.apaza@gmail.com//" +
            "\nEn caso de que no se desee modificar todos los campos, colocar \"_\" en el campo que no se desee" +
            "modificar." +
            "\nEjemplo -> EditarUsuario:2//_//Apaza Calderon//_//jjapazacalderon@hotmail.com// ";

    public static String EliminarUsuario = "EliminarUsuario:id//" +
            "\nEjemplo -> EliminarUsuario:3//";

    public static String ListarUsuario = "ListarUsuario:" +
            "\nEjemplo -> ListarUsuario:";

}
