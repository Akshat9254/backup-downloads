package WithStrategyPattern;

import WithStrategyPattern.Strategy.SpecialDriveStrategy;

public class SportsVehicle extends Vehicle {
    SportsVehicle() {
        super(new SpecialDriveStrategy());
    }
}
