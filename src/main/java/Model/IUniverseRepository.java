package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface IUniverseRepository {
    ArrayList<Planet> getAllPlanets(String solarSystemName);
    Planet getPlanet(String solarSystemName, String planetName);
    PlanetSystem getPlanetSystem(String solarSystemName);
    Collection<PlanetSystem> getAllPlanetSystems();
    void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystem);
    void updatePlanet();
    void deletePlanet(String planetSystem, String planet);
}
