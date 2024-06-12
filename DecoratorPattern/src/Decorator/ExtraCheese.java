package Decorator;

import Base.BasePizza;

public class ExtraCheese extends ToppingDecorator {
    BasePizza basePizza;

    public ExtraCheese(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int getCost() {
        return this.basePizza.getCost() + 15;
    }
}
