package Model;

import Repository.UniverseCSVRepository;
import Repository.UniverseJSONRepository;
import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;


public class Application {


    public static void main(String[] args) {
        Javalin app = Javalin.create().start();
        app.config.enableWebjars();

        app.get("/planet-systems/:planet-system-id", new VueComponent("planet-system-detail"));
        app.get("/planet-systems/:planet-system-id/createplanet", new VueComponent("planet-create"));
        app.get("/planet-systems", new VueComponent("planet-system-overview"));
        app.get("/planet-systems/:planet-system-id/planets/:planet-id", new VueComponent("planet-detail"));
        app.get("/planet-systems/:planet-system-id/planets/:planet-id/update", new VueComponent("planet-update"));

        UniverseCSVRepository PlanetSystemRepository = new UniverseCSVRepository("planets.csv");
        PlanetSystemController planetSystemController = new PlanetSystemController(PlanetSystemRepository);

        app.get("/api/planet-systems/:planet-system-id", planetSystemController::getSinglePlanetSystem);
        app.get("/api/planet-systems/", planetSystemController::getAllPlanetSystems);
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id",planetSystemController::getSinglePlanet);
        app.get("/api/planet-systems/:planet-system-id/planets",planetSystemController::getAllPlanets);
        app.get("/api/planet-systems/:planet-system-id/planets/:planet-id/delete",planetSystemController::removePlanet);
        app.post("/api/planet-systems/:planet-system-id/createplanet",planetSystemController::createPlanet);
        app.post("/api/planet-systems/:planet-system-id/planets/:planet-id/update",planetSystemController::updatePlanet);

        app.get("/", ctx -> ctx.result("Hello, world"));

        UniverseJSONRepository universeJSONRepository = new UniverseJSONRepository("planets.json");
    }
}
