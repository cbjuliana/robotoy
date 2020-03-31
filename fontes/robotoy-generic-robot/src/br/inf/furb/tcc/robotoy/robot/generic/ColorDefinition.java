package br.inf.furb.tcc.robotoy.robot.generic;

public final class ColorDefinition {

    private final String name;
    private final Color color;

    public ColorDefinition(Color color) {
        this.name = color.getName();
        this.color = color;
    }

    public ColorDefinition(String description, Color color) {
        this.name = description;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

}
