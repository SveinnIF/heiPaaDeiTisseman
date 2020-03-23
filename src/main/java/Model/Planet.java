package Model;

public class Planet extends NaturalSatellite {

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod,Star centralCelestialBody, String pictureUrl) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody, pictureUrl);
    }
    public Planet(){}

    @Override
    public String toString() {
        return super.getName() + "," + super.getMass() + "," + super.getRadius() + "," + super.getSemiMajorAxis() + "," + super.getEccentricity() + "," + super.getOrbitalPeriod() + "," + super.getPictureUrl();
        // <planet name>, <planet mass>, <planet radius>, <planet semi-major axis>,<planet eccentricity>, <planet orbital period>, <planet image url>
    }
}
