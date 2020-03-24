package Model;

import io.javalin.http.Context;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class PlanetSystemController {
    private IUniverseRepository universeJSONRepository;

    public PlanetSystemController(IUniverseRepository universeJSONRepository) {
        this.universeJSONRepository = universeJSONRepository;
    }

    public void getAllPlanets(Context context) {
        String systemId = context.pathParam(":planet-system-id");
        String sortBy = context.queryParam("sort_by");

        ArrayList<Planet> allPlanets = universeJSONRepository.getAllPlanets(systemId); //i think it is because this is not a copy, this is the actual array that i am sorting and when i press null it
                                                                                   //just prints the already sortet array
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
        Collection<PlanetSystem> allPlanetSystems = universeJSONRepository.getAllPlanetSystems();
        context.json(allPlanetSystems);
    }

    public void getSinglePlanet(Context context){
        String systemId = context.pathParam(":planet-system-id");
        String planetId = context.pathParam(":planet-id");
        Planet planet = universeJSONRepository.getPlanet(systemId,planetId);
        context.json(planet);
    }

    public void getSinglePlanetSystem(Context context){
        String systemId = context.pathParam(":planet-system-id");
        PlanetSystem system = universeJSONRepository.getPlanetSystem(systemId);
        context.json(system);
    }

    public void removePlanet(Context context){
        String systemId = context.pathParam(":planet-system-id");
        String planetId = context.pathParam(":planet-id");
        universeRepository.deletePlanet(systemId,planetId);
    }

}
