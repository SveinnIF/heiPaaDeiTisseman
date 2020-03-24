package Model;


public class Star extends CelestialBody {


    public Star(String name, double mass, double radius, double effectiveTemp,String pictureUrl) {
        super(name,mass,radius,effectiveTemp,pictureUrl);
    }

    public Star(){}

    @Override
    public String toString() {
        return super.getName() + " has a mass of: " + super.getMass() + ", and a radius of: " + super.getRadius() + ", and a temperature of: " + super.getEffectiveTemp();
    }
}
