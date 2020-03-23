package Model;


public class Star extends CelestialBody {


    public Star(String name, double mass, double radius, double effectiveTemp,String pictureUrl) {
        super(name,mass,radius,effectiveTemp,pictureUrl);
    }

    public Star(){}

    @Override
    public String toString() {
        return super.getName() + "," + super.getMass() + "," + super.getRadius() + "," + super.getEffectiveTemp() + "," + super.getPictureUrl() + ",";
    }
}
