package utilidades;

public class Ejemplo {

    // ---------------------------------- CATEGORIA ------------------------------------------

    public static String RegistrarCategoria = "RegistrarCategoria:nombre//categoria_sup// " +
            "\nEjemplo -> RegistrarCategoria:Material de oficina//3//" + 
            "\nEn caso de que no se desee agregar una categoria superior, colocar \"_\" en el ultimo campo" +
            "\nEjemplo -> RegistrarCategoria:Material de oficina//_//";

    public static String EditarCategoria = "EditarCategoria:id//nombreNuevo//categoria_sup// " +
            "\nEjemplo -> EditarCategoria:2//Material de oficina//3//" + 
            "\nEn caso de que no se desee modificar todos los campos, colocar \"_\" en el campo que no se desee" +
            "modificar." + "\nEjemplo -> EditarCategoria:2//Material de oficina//_//";

    public static String EliminarCategoria = "EliminarCategoria:id// " +
            "\nEjemplo -> EliminarCategoria:2//";

    public static String ListarCategoria = "ListarCategoria:" +
            "\nEjemplo -> ListarCategoria:";

    
    
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

    public static String RecuperarUsuario = "RecuperarUsuario:id//" +
            "\nEjemplo -> RecuperarUsuario:3//";

    public static String ListarUsuario = "ListarUsuario:" +
            "\nEjemplo -> ListarUsuario:";


    // ---------------------------------- MOV SUMI ------------------------------------------

    public static final String IngresoNuevoSuministro = "IngresoNuevoSuministro:nombre//descripcion//id_unidadMedida//" +
            "id_categoria//stockMinimo//stockMax//cantidad_ingresada//" +
            "\nEjemplo-> IngresoNuevoSuministro:Lapiceros Rojos//Lapicero Pilot de color rojo//2//4//50//200//40//";

    public static final String IngresoSuministro = "IngresoSuministro:id_suministro//cantidad//" +
            "\nEjemplo -> IngresoSuministro:2//100//";

    public static final String SalidaSuministro = "SalidaSuministro:id_suministro//cantidad//encargado//departamento//observacion//" +
            "\nEjemplo -> SalidaSuministro:2//100//Juan Perez//Finanzas//Ninguna//";

    public static final String DevolucionSuministro = "DevolucionSuministro:id_suministro//cantidad//encargado//departamento//observacion//" +
            "\nEjemplo -> DevolucionSuministro:2//100//Juan Perez//Finanzas//Devolucion de los materiales sobrados en la actividad//";

    public static final String CancelarMovimiento = "CancelarMovimiento:id_movimiento//" +
            "\nEjemplo -> CancelarMovimiento:7//";

    public static final String RestablecerMovimiento = "RestablecerMovimiento:id_movimiento//" +
            "\nEjemplo -> RestablecerMovimiento:7//";

    public static final String EliminarSuministro = "EliminarSuministro:id_suministro//" +
            "\nEjemplo -> EliminarSuministro:5//";

    public static final String EditarSuministro = "EditarSuministro:id_suministro//nombre//descripcion//id_unidadMedida" +
            "//id_categoria//stockMin//stockMax//" +
            "\nEjemplo -> EditarSuministro:3//Hojas bond Oficio//Papel bond tamano oficio//3//42//50//400//" +
            "\nEn caso de que no se desee modificar todos los campos, colocar \"_\" en el campo que NO se desee modificar" +
            "\nEjemplo -> EditarSuministro:3//Hojas bond Oficio//_//_//_//50//_//";

    public static final String VerSuministro = "VerSuministro:id_suministro//" +
            "\nEjemplo -> VerSuministro:33//";

    public static final String VerMovimiento = "VerMovimiento:id_movimiento//" +
            "\nEjemplo -> VerMovimiento:8//";

    public static final String ListarMovimientos = "ListarMovimientos:" +
            "\nEjemplo ListarMovimientos:";

    public static final String ListarMovimientosCancelados = "ListarMovimientosCancelados:" +
            "\nEjemplo ListarMovimientosCancelados:";
    
    public static final String MENU = 
    "\n\n\n---------------------------------- CATEGORIA ------------------------------------------"+

     "\n\n\nRegistrarCategoria:nombre//categoria_sup// " +
            "\nEjemplo -> RegistrarCategoria:Material de oficina//3//" + 
            "\nEn caso de que no se desee agregar una categoria superior, colocar \"_\" en el ultimo campo" +
            "\nEjemplo -> RegistrarCategoria:Material de oficina//_//"

    + "\n\n\nEditarCategoria:id//nombreNuevo//categoria_sup// " +
            "\nEjemplo -> EditarCategoria:2//Material de oficina//3//" + 
            "\nEn caso de que no se desee modificar todos los campos, colocar \"_\" en el campo que no se desee" +
            "modificar." + "\nEjemplo -> EditarCategoria:2//Material de oficina//_//"

   + "\n\n\nEliminarCategoria:id// " +
            "\nEjemplo -> EliminarCategoria:2//"
    + "\n\n\nListarCategoria:" +
            "\nEjemplo -> ListarCategoria:"

    
    
    +"\n\n\n---------------------------------- UNIDAD DE MEDIDA ------------------------------------------"

    +"\n\n\nRegistrarUnidadMedida:nombre// " +
            "\nEjemplo -> RegistrarUnidadMedida:Kilogramo//"

    + "\n\n\nEditarUnidadMedida:id//nombreNuevo// " +
            "\nEjemplo -> EditarUnidadMedida:2//Metro//"

   + "\n\n\nEliminarUnidadMedida:id// " +
            "\nEjemplo -> EliminarUnidadMedida:2//"

+ "\n\n\nListarUnidadMedida:" +
            "\nEjemplo -> ListarUnidadMedida:"

    +"\n\n\n---------------------------------- USUARIO ------------------------------------------"

    + "\n\n\nRegistrarUsuario:nombre//apellido//carnet//email// " +
            "\nEjemplo -> RegistrarUsuario:Juan//Perez//3342342//juan.perez@gmail.com//"

    + "\n\n\nEditarUsuario:id//nombre//apellido//carnet//email// " +
            "\nEjemplo -> EditarUsuario:2//Juan Jose//Apaza//4435232//juan.jose.apaza@gmail.com//" +
            "\nEn caso de que no se desee modificar todos los campos, colocar \"_\" en el campo que no se desee" +
            "modificar." +
            "\nEjemplo -> EditarUsuario:2//_//Apaza Calderon//_//jjapazacalderon@hotmail.com// "

    +"\n\n\nEliminarUsuario:id//" +
            "\nEjemplo -> EliminarUsuario:3//"

+ "\n\n\nRecuperarUsuario:id//" +
            "\nEjemplo -> RecuperarUsuario:3//"

    + "\n\n\nListarUsuario:" +
            "\nEjemplo -> ListarUsuario:"


    +"\n\n\n---------------------------------- MOV SUMI ------------------------------------------"

    +"\n\n\nIngresoNuevoSuministro:nombre//descripcion//id_unidadMedida//" +
            "id_categoria//stockMinimo//stockMax//cantidad_ingresada//" +
            "\nEjemplo-> IngresoNuevoSuministro:Lapiceros Rojos//Lapicero Pilot de color rojo//2//4//50//200//40//"

    + "\n\n\nIngresoSuministro:id_suministro//cantidad//" +
            "\nEjemplo -> IngresoSuministro:2//100//"

   + "\n\n\nSalidaSuministro:id_suministro//cantidad//encargado//departamento//observacion//" +
            "\nEjemplo -> SalidaSuministro:2//100//Juan Perez//Finanzas//Ninguna//"

    +"\n\n\nDevolucionSuministro:id_suministro//cantidad//encargado//departamento//observacion//" +
            "\nEjemplo -> DevolucionSuministro:2//100//Juan Perez//Finanzas//Devolucion de los materiales sobrados en la actividad//"

    +"\n\n\nCancelarMovimiento:id_movimiento//" +
            "\nEjemplo -> CancelarMovimiento:7//"

   + "\n\n\nRestablecerMovimiento:id_movimiento//" +
            "\nEjemplo -> RestablecerMovimiento:7//"
    
   +"\n\n\nEliminarSuministro:id_suministro//" +
            "\nEjemplo -> EliminarSuministro:5//"

    + "\n\n\nEditarSuministro:id_suministro//nombre//descripcion//id_unidadMedida" +
            "//id_categoria//stockMin//stockMax//" +
            "\nEjemplo -> EditarSuministro:3//Hojas bond Oficio//Papel bond tamano oficio//3//42//50//400//" +
            "\nEn caso de que no se desee modificar todos los campos, colocar \"_\" en el campo que NO se desee modificar" +
            "\nEjemplo -> EditarSuministro:3//Hojas bond Oficio//_//_//_//50//_//"

    +"\n\n\nVerSuministro:id_suministro//" +
            "\nEjemplo -> VerSuministro:33//"

    +"\n\n\nVerMovimiento:id_movimiento//" +
            "\nEjemplo -> VerMovimiento:8//"

    +"\n\n\nListarMovimientos:" +
            "\nEjemplo ListarMovimientos:"

    +"\n\n\nListarMovimientosCancelados:" +
            "\nEjemplo ListarMovimientosCancelados:";
    
    
    
    

}
