package control;

public class LoginSesion {
    // Lista de usuarios válidos
    String[] usuarios = {
        "Thomas", "Rockemma", "Alejo", "Jorge_Aju",
        "Victor", "Dereck", "Erick", "Nicole",
        "pythonLover", "Dulce", "Pereira"
    };

    // Método para validar solo el usuario
    public boolean validacionUsuario(String EUsuario) {
        for (String user : usuarios) {
            if (user.equals(EUsuario)) {
                return true; // Usuario encontrado
            }
        }
        return false; // Usuario no encontrado
    }
}
