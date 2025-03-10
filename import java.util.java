import java.util.Scanner;

import javafx.scene.control.Menu;
public class Cajero { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
        final int PIN_CORRECTO = 1234;
        double saldo = 50000; 
        int intentos = 0; boolean 
        cuentaBloqueada = false;

// Autenticación según intentos
    ConfirmarPin confirmador = new ConfirmarPin();
    while (intentos < 3 && !cuentaBloqueada) {
        System.out.println("Ingrese su pin; ");
        int pinIngresado = scanner.nextInt();
        
        if (confirmador.confirmarPIN(pinIngresado)) {
            break;
        } else {
            intentos++;
        }
    }
    
    if (confirmador.CuentisBloqueada()) {
        System.out.println("Cuenta bloqueada. Saliendo del sistema.");
        scanner.close();
        return;
    }
    
    // Menú del cajero
    CajeroGroseron cajeroGroseron = new CajeroGroseron(scanner);
    MenuOpciones menuOpciones = new MenuOpciones(saldo, scanner);
    
    int opcion;
    do {
        opcion = cajeroGroseron.mostrarMenu();
        menuOpciones.menurOpcion(opcion);
    } while (opcion != 4);
    
    scanner.close();
}

}

class ConfirmarPin { 
    private static final int PIN_CORRECTO = 1234; 
    private int intentos = 0; 
    private boolean cuentaBloqueada = false;

public boolean confirmarPIN(int pinIngresado) {
    if (cuentaBloqueada) {
        System.out.println("Mera Gueva, cuenta bloqueada");
        return false;
    }
    
    if (pinIngresado == PIN_CORRECTO) {
        System.out.println("Bienvenido Pichurria");
        intentos = 0;
        return true;
    } else {
        intentos++;
        System.out.println("Pin Incorrecto. Mosca Pues " + intentos);
        
        if (intentos == 3) {
            cuentaBloqueada = true;
            System.out.println("Mera Gueva, cuenta bloqueada");
        }
        return false;
    }
}

public boolean CuentisBloqueada() {
    return cuentaBloqueada;
}

}

class CajeroGroseron { private Scanner scanner;

public CajeroGroseron(Scanner scanner) {
    this.scanner = scanner;
}

public int mostrarMenu() {
    System.out.println("Bienvenido a su cajero Groserón");
    System.out.println("1. Consultar Chichigua");
    System.out.println("2. Retirar Menuda");
    System.out.println("3. Depositar Money");
    System.out.println("4. Abrirse del Parche");
    return scanner.nextInt();
}

}

class MenuOpciones { private double saldo; private Scanner scanner;

public MenuOpciones(double saldoInicial, Scanner scanner) {
    this.saldo = saldoInicial;
    this.scanner = scanner;
}

public void menurOpcion(int opcion) {
    switch (opcion) {
        case 1:
            System.out.println("Ay marica tenes apenas $$ " + saldo);
            break;
        case 2:
            System.out.println("¿Cuanta chichigua vas a sacar? ");
            double retiro = scanner.nextDouble();
            if (retiro > 0 && retiro <= saldo) {
                saldo -= retiro;
                System.out.println("Su nueva chichigua es de: " + saldo);
            } else {
                System.out.println("Oigan a esta gueva, si solo tenes " + saldo);
            }
            break;
        case 3:
            System.out.println("Empiece a cantar, cuanta plata va a consignar? ");
            double deposito = scanner.nextDouble();
            if (deposito > 0) {
                saldo += deposito;
                System.out.println("Manoo, su nueva consignacion fue exitosa. Su saldo es: " + saldo);
            } else {
                System.out.println("Despierte pues mijo, ese valor no se puede!!! ");
            }
            break;
        case 4:
            System.out.println("Suerte pues mi amor, perdete!");
            break;
        default:
            System.out.println("Bajese de esa nube ome gueva");
    }
}

}