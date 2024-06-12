import Base.BasePizza;
import Base.Margherita;
import Decorator.ExtraCheese;
import Decorator.Mushroom;

public class PizzaStore {
    public static void main(String[] args) {
        BasePizza pizza = new Margherita();
        System.out.println("Cost of Margherita = " + pizza.getCost());

        pizza = new ExtraCheese(pizza);
        System.out.println("Cost of ExtraCheese = " + pizza.getCost());

        pizza = new Mushroom(pizza);
        System.out.println("Cost of Mushroom = " + pizza.getCost());
    }
}
