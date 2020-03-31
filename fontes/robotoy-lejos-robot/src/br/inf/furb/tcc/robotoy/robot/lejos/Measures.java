package br.inf.furb.tcc.robotoy.robot.lejos;

/**
 * Medidas utilizadas pelo rob� para locomo��o. Essas medidas s�o espec�ficas
 * para o rob� montado com o <i>kit</i> <b>LEGO Mindstorms NXT 2.0</b>.
 * 
 * @author Maria Gabriela Torrens
 */
public final class Measures {

    private Measures() {
        // Classe n�o pode ser instanciada.
    }

    /**
     * O valor que deve ser percorrido para que uma roda percorra 90 graus.
     */
    public static final int WHEEL_NINETY_DEGREES = 280;

    /**
     * O valor que deve ser percorrido para que o motor gire 90 graus.
     */
    public static final int MOTOR_NINETY_DEGREES = 90;

    /**
     * Valor que representa um passo do rob�.
     */
    public static final int ONE_FOOTSTEP = 750;

}
