package server;

import server.utils.Inicializador;

import java.io.IOException;

public class App {

  public static void main(String[] args) throws IOException {
    Server.init();
    Inicializador inicializador = new Inicializador();
    inicializador.inicializar();

  }

}