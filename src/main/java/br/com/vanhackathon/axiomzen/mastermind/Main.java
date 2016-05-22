package br.com.vanhackathon.axiomzen.mastermind;

import java.io.IOException;

import br.com.vanhackathon.axiomzen.mastermind.webserver.Server;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();

        System.out.println("Mastermind Axiomzen Challenge is up and running on " + Server.BASE_URI + ".\nCan you defeat me??\nPress enter to give up. :-)" );
        System.in.read();
        System.out.println("Give up already, shame on you. }:-(");
        
        server.shutdown();
    }
}

