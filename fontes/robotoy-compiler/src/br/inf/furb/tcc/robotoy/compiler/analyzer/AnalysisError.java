package br.inf.furb.tcc.robotoy.compiler.analyzer;

public class AnalysisError extends Exception {

    private static final String MESSAGE_FORMAT = "Erro na linha %s: %s";
    private int position;

    public AnalysisError(String msg, int position) {
        super(msg);
        this.position = position;
    }

    public AnalysisError(String msg) {
        super(msg);
        this.position = -1;
    }

    public AnalysisError(int line, String msg) {
        super(String.format(MESSAGE_FORMAT, line, msg));
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        return super.toString() + ", @ " + position;
    }
}
