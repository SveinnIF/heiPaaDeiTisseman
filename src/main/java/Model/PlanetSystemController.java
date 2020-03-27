package Model;

import io.javalin.http.Context;


import java.util.ArrayList;
import java.util.Comparator;

public class PlanetSystemController {
    private IUniverseRepository universeRepository;

    public PlanetSystemController(IUniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }

    public void getAllPlanets(Context context) {
        String systemId = context.pathParam(":planet-system-id");
        String sortBy = context.queryParam("sort_by");

        ArrayList<Planet> allPlanets = universeRepository.getAllPlanets(systemId);


        if (sortBy != null) {
            switch (sortBy) {
                case "name":
                    allPlanets.sort(Comparator.comparing(CelestialBody::getName));
                    break;
                case "mass":
                    allPlanets.sort(Comparator.comparing(CelestialBody::getMass));
                    break;
                case "radius":
                    allPlanets.sort(Comparator.comparing(CelestialBody::getRadius));
                    break;
            }
        }
        context.json(allPlanets);
    }

    public void getAllPlanetSystems(Context context) {
        ArrayList<PlanetSystem> allPlanetSystems = new ArrayList<>(universeRepository.getAllPlanetSystems());
        context.json(allPlanetSystems);
    }

    public void getSinglePlanet(Context context){
        String systemId = context.pathParam(":planet-system-id");
        String planetId = context.pathParam(":planet-id");
        Planet planet = universeRepository.getPlanet(systemId,planetId);
        context.json(planet);
    }

    public void getSinglePlanetSystem(Context context){
        String systemId = context.pathParam(":planet-system-id");
        PlanetSystem system = universeRepository.getPlanetSystem(systemId);
        context.json(system);
    }

    public void removePlanet(Context context){
        String systemId = context.pathParam(":planet-system-id");
        String planetId = context.pathParam(":planet-id");
        universeRepository.deletePlanet(systemId,planetId);
        context.redirect("/planet-systems/" + systemId);
    }

    public void createPlanet(Context context) {
        String systemId = context.pathParam(":planet-system-id");
        String name = context.formParam("name");
        String mass = context.formParam("mass");
        String radius = context.formParam("radius");
        String semiMajorAxis = context.formParam("semiMajorAxis");
        String eccentricity = context.formParam("eccentricity");
        String orbitalPeriod = context.formParam("orbitalPeriod");
        String pictureUrl = context.formParam("pictureUrl");
        universeRepository.createPlanet(name,Double.parseDouble(mass),Double.parseDouble(radius),Double.parseDouble(semiMajorAxis),Double.parseDouble(eccentricity),Double.parseDouble(orbitalPeriod),pictureUrl,systemId);
        context.redirect("/planet-systems/" + systemId);
    }

    public void updatePlanet(Context context){
        String systemId = context.pathParam(":planet-system-id");
        String originalName = context.pathParam("planet-id");
        String newName = context.formParam("name");
        String mass = context.formParam("mass");
        String radius = context.formParam("radius");
        String semiMajorAxis = context.formParam("semiMajorAxis");
        String eccentricity = context.formParam("eccentricity");
        String orbitalPeriod = context.formParam("orbitalPeriod");
        String pictureUrl = context.formParam("pictureUrl");
        universeRepository.updatePlanet(originalName, newName,Double.parseDouble(mass),Double.parseDouble(radius),Double.parseDouble(semiMajorAxis),Double.parseDouble(eccentricity),Double.parseDouble(orbitalPeriod),pictureUrl,systemId);
        context.redirect("/planet-systems/" + systemId +"/planets/"+ newName); //bedre å context.redirect("/planet-systems/" + systemId); fordi da må du ikke ta backspace gjennom alt sammen også refreshe siden, burde være en knapp for det. kanskje jeg har gjort noe galt også, jeg er ikke spesielt smart
    }
}
