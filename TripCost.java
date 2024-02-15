public class TripCost {
    // Immutable fields for the cost components
    private final double distance;
    private final double gasolineCost;
    private final double gasMileage;
    private final double hotelCost;
    private final double foodCost;
    private final int days;
    private final double attractionsCost;
    private final boolean isDistanceInMiles;
    private final boolean isGasolineCostPerGallon;
    private final boolean isGasMileageInMpg;

    // Constructor
    public TripCost(double distance, double gasolineCost, double gasMileage, double hotelCost, double foodCost, int days, double attractionsCost, boolean isDistanceInMiles, boolean isGasolineCostPerGallon, boolean isGasMileageInMpg) {
        this.distance = distance;
        this.gasolineCost = gasolineCost;
        this.gasMileage = gasMileage;
        this.hotelCost = hotelCost;
        this.foodCost = foodCost;
        this.days = days;
        this.attractionsCost = attractionsCost;
        this.isDistanceInMiles = isDistanceInMiles;
        this.isGasolineCostPerGallon = isGasolineCostPerGallon;
        this.isGasMileageInMpg = isGasMileageInMpg;
    }

    // Method to calculate the total cost of the trip
    public double calculateTotalCost() {
        // Convert units if necessary
        double distanceInMiles = isDistanceInMiles ? distance : convertKilometersToMiles(distance);
        double gasolineCostPerGallon = isGasolineCostPerGallon ? gasolineCost : convertLitersToGallons(gasolineCost);
        double gasMileageInMpg = isGasMileageInMpg ? gasMileage : convertKilometersPerLiterToMilesPerGallon(gasMileage);

        // Calculate gasoline cost
        double totalGasolineCost = (distanceInMiles / gasMileageInMpg) * gasolineCostPerGallon;

        // Calculate other costs
        double totalHotelCost = hotelCost * days;
        double totalFoodCost = foodCost * days;

        // Sum up all costs to get the total
        return totalGasolineCost + totalHotelCost + totalFoodCost + attractionsCost;
    }

    private double convertKilometersToMiles(double kilometers) {
        return kilometers / 1.60934; // 1 mile = 1.60934 kilometers
    }

    private double convertLitersToGallons(double liters) {
        return liters / 3.78541; // 1 gallon = 3.78541 liters
    }

    private double convertKilometersPerLiterToMilesPerGallon(double kmPerLiter) {
        return kmPerLiter * 2.35215; // 1 km/liter = 2.35215 miles/gallon
    }
}
