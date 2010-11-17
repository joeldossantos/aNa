package br.pensario.connector;

public abstract class NCLStatement implements Comparable<NCLStatement> {

    public abstract String parse(int ident);
    public abstract String toString();
}
