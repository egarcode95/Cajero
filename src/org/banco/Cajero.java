package org.banco;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Cajero {

	private static double saldoInicial = 10000.00;
	private double saldo = saldoInicial;
	private int intentosInvalidos = 0;
	private LinkedList<String> ultimosMovimientos = new LinkedList<>();

	public static void main(String[] args) {
		Cajero cajero = new Cajero();
		cajero.iniciarCajero();
	}

	public void iniciarCajero() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			mostrarMenu();

			int opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				retirarDinero();
				break;
			case 2:
				hacerDeposito();
				break;
			case 3:
				consultarSaldo();
				break;
			case 4:
				System.out.println("-----------------------------------------------");
				System.out.println("No disponible por el momento, intente más tarde");
				System.out.println("-----------------------------------------------");
				break;
			case 5:
				verUltimosMovimientos();
				break;
			case 9:
				System.out.println("-----------------------------------------");
				System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
				System.out.println("------------------------------------------");
			
			default:
				intentosInvalidos++;
				System.out.println("----------------------------------------------");
				System.out.println("Opción inválida, por favor, vuelva a intentar.");
				System.out.println("----------------------------------------------");
				if (intentosInvalidos == 3) {
					System.out.println("-------------------------------------");
					System.out.println("Demasiados intentos inválidos. Adiós.");
					System.out.println("-------------------------------------");
					
				}
				continue;
			}

			intentosInvalidos = 0;
			sc.close();
		}
	}

	private void mostrarMenu() {
		
		System.out.println("-----Bienvenido-----");
		System.out.println("--------------------");
		System.out.println("Opciones del cajero:");
		System.out.println("1) Retirar dinero");
		System.out.println("2) Hacer depósitos");
		System.out.println("3) Consultar saldo");
		System.out.println("4) Quejas");
		System.out.println("5) Ver últimos movimientos");
		System.out.println("9) Salir del cajero");
		System.out.println("---------------------------");
		System.out.println("Ingrese la opción deseada: ");
		System.out.println("---------------------------");
		
	}

	private void retirarDinero() {
		Scanner sc = new Scanner(System.in);
		System.out.println("---------------------------------------");
		System.out.println("Cantidad disponible a retirar: " + saldo);
		System.out.println("---------------------------------------");
		System.out.println("Ingrese la cantidad a retirar: ");
		System.out.println("-------------------------------");

		double cantidad = sc.nextDouble();

		if (cantidad > 6000) {
			System.out.println("------------------------------------");
			System.out.println("No se puede retirar más de $6,000.00");
			System.out.println("-------------------------------------");
			
		}

		if (cantidad > saldo) {
			System.out.println("---------------------");
			System.out.println("Fondos insuficientes.");
			System.out.println("---------------------");
			
		}

		if (cantidad % 50 != 0) {
			System.out.println("--------------------------------------");
			System.out.println("Solo puede retirar múltiplos de $50.00");
			System.out.println("--------------------------------------");
			
		}

		saldo -= cantidad;
		agregarMovimiento("Retiro de $" + cantidad);
		System.out.println("-----------------------------------------------------");
		System.out.println("¿Desea donar $200 para la graduación de ch30? (s/n): ");
		System.out.println("-----------------------------------------------------");
		String respuesta = sc.next();

		if (respuesta.equalsIgnoreCase("s")) {
			if (saldo >= 200) {
				saldo -= 200;
				System.out.println("------------------------------------------");
				agregarMovimiento("Donación de $200 para la graduación de ch30");
				System.out.println("------------------------------------------");
				System.out.println("¡Gracias por su donación!");
				System.out.println("------------------------------------------");
			} else {
				System.out.println("-------------------------------------");
				System.out.println("No tiene suficiente saldo para donar.");
				System.out.println("--------------------------------------");
			}
			sc.close();
		}
	}

	private void hacerDeposito() {
		Scanner sc = new Scanner(System.in);

		System.out.println("-------------------------------");
		System.out.println("1) Depósito a cuenta de cheques");
		System.out.println("2) Depósito a Tarjeta de Crédito");
		System.out.println("Seleccione el tipo de depósito: ");
		System.out.println("-------------------------------");
		int opcion = sc.nextInt();
		System.out.println("-------------------------------");
		System.out.println("Ingrese la cantidad a depositar: ");
		System.out.println("-------------------------------");
		double cantidad = sc.nextDouble();

		switch (opcion) {
		case 1:
			if (cantidad % 50 != 0) {
				System.out.println("-------------------------------");
				System.out.println("Solo depósitos múltiplos de $50");
				System.out.println("-------------------------------");
				
			}
			saldo += cantidad;
			agregarMovimiento("Depósito a cuenta de cheques de $" + cantidad);
			break;
		case 2:
			saldo += cantidad;
			agregarMovimiento("Depósito a Tarjeta de Crédito de $" + cantidad);
			break;
		default:
			System.out.println("Opción inválida, por favor, vuelva a intentar.");
			
		}
		sc.close();
	}

	private void consultarSaldo() {
		System.out.println("---------------------------");
		System.out.println("Saldo disponible: $" + saldo);
		System.out.println("---------------------------");
	}

	private void verUltimosMovimientos() {
		if (ultimosMovimientos.isEmpty()) {
			System.out.println("------------------------------");
			System.out.println("No hay movimientos registrados.");
			System.out.println("------------------------------");
			
		}

		for (String movimiento : ultimosMovimientos) {
			System.out.println(movimiento);
		}
	}

	private void agregarMovimiento(String movimiento) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD hh:mm");
		String fecha = sdf.format(new Date());
		ultimosMovimientos.addFirst(fecha + " " + movimiento);

		if (ultimosMovimientos.size() > 5) {
			ultimosMovimientos.removeLast();
		}
	}
	
}
