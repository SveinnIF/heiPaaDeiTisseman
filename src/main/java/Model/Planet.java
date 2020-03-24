package Model;

public class Planet extends NaturalSatellite {

    public Planet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod,Star centralCelestialBody, String pictureUrl) {
        super(name, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, centralCelestialBody, pictureUrl);
    }
    public Planet(){}

    @Override
    public String toString() {
        return super.getName() + " has a mass of: " + super.getMass() + ", and a radius of: " + super.getRadius() + ", and a SemiMajorAxis of: "
                + super.getSemiMajorAxis() + ", and a eccentricity of: " + super.getEccentricity() + ", and a OrbitalPeriod of: " + super.getOrbitalPeriod();
    }
}
