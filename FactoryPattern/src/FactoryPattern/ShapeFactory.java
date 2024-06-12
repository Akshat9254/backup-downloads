package FactoryPattern;

public class ShapeFactory {
    public Shape getShape(ShapeType shape) {
        switch (shape) {
            case CIRCLE: return new Circle();
            case RECTANGLE: return new Rectangle();
            default: return null;
        }
    }
}
