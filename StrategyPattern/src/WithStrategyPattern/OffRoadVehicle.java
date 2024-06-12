package WithStrategyPattern;

import WithStrategyPattern.Strategy.SpecialDriveStrategy;

public class OffRoadVehicle extends Vehicle{
    OffRoadVehicle() {
        super(new SpecialDriveStrategy());
    }
}
