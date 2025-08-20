import java.util.Scanner;

class Calculadora {
    private double resultadoOperacion;

    public void setResultadoOperacion(double resultadoOperacion) {
        this.resultadoOperacion = resultadoOperacion;
    }

    public double getResultadoOperacion() {
        return resultadoOperacion;
    }

    public String eliminarEspacios(String cadena) {
        String limpia = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (!cadena.substring(i, i + 1).equals(" ")) {
                limpia += cadena.substring(i, i + 1);
            }
        }
        return limpia;
    }

    public String obtenerOperacion(String cadena) {
        String operacion = "";
        for (int i = 0; i < cadena.length(); i++) {
            String c = cadena.substring(i, i + 1);
            if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("%") || c.equals("^")) {
                operacion = c;
                break;
            }
        }
        return operacion;
    }

    public void procesarOperacion(String cadena, String operador) {
        String[] partes = cadena.split("\\" + operador);
        if (partes.length != 2) {
            System.out.println("Error: formato incorrecto.");
            resultadoOperacion = 0;
            return;
        }

        double num1 = convertirCadenaADouble(partes[0]);
        double num2 = convertirCadenaADouble(partes[1]);

        if (operador.equals("+")) {
            resultadoOperacion = num1 + num2;
        } else if (operador.equals("-")) {
            resultadoOperacion = num1 - num2;
        } else if (operador.equals("*")) {
            resultadoOperacion = num1 * num2;
        } else if (operador.equals("/")) {
            if (num2 != 0) {
                resultadoOperacion = num1 / num2;
            } else {
                System.out.println("Error: división entre cero.");
                resultadoOperacion = 0;
            }
        } else if (operador.equals("%")) {
            if (num2 != 0) {
                resultadoOperacion = num1 % num2;
            } else {
                System.out.println("Error: módulo entre cero.");
                resultadoOperacion = 0;
            }
        } else if (operador.equals("^")) {
            resultadoOperacion = 1;
            for (int i = 0; i < (int) num2; i++) {
                resultadoOperacion = resultadoOperacion * num1;
            }
        }
    }

    public void mostrarResultado() {
        System.out.println("Resultado: " + resultadoOperacion);
    }

    public double convertirCadenaADouble(String texto) {
        double valor = 0;
        boolean decimal = false;
        double factor = 0.1;
        boolean negativo = false;

        int i = 0;
        if (texto.substring(0, 1).equals("-")) {
            negativo = true;
            i = 1;
        }

        for (; i < texto.length(); i++) {
            String letra = texto.substring(i, i + 1);
            if (letra.equals(".")) {
                decimal = true;
                continue;
            }

            int digito = letra.charAt(0) - '0';
            if (digito >= 0 && digito <= 9) {
                if (decimal) {
                    valor += digito * factor;
                    factor = factor / 10;
                } else {
                    valor = valor * 10 + digito;
                }
            }
        }

        if (negativo) {
            valor = -valor;
        }

        return valor;
    }
}


public class PruebaCalculadora {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Calculadora calculadora = new Calculadora();
        int opcion = 0;

        while (opcion != 2) {
            System.out.println("\n--- Menú Calculadora ---");
            System.out.println("1) Realizar operación");
            System.out.println("2) Salir");
            System.out.print("Elige una opción: ");
            opcion = entrada.nextInt();
            entrada.nextLine();

            if (opcion == 1) {
                System.out.print("Escribe la operación (ej: 8 * 9): ");
                String operacionIngresada = entrada.nextLine();

                String cadenaLimpia = calculadora.eliminarEspacios(operacionIngresada);
                String operador = calculadora.obtenerOperacion(cadenaLimpia);

                if (operador.equals("")) {
                    System.out.println("No se detectó una operación válida.");
                } else {
                    calculadora.procesarOperacion(cadenaLimpia, operador);
                    calculadora.mostrarResultado();
                }
            }else if (opcion ==2) {
            	 System.out.println("\nSaliendo del programa...");
                 System.out.println("Hasta pronto :D");
            }else {
                System.out.println("Opción no válida. Intente de nuevo. <3");
            }
        }

       
    }
}