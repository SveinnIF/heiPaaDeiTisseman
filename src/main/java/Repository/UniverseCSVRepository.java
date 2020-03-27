package Repository;

import Model.IUniverseRepository;
import Model.Planet;
import Model.PlanetSystem;
import Model.Star;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UniverseCSVRepository implements IUniverseRepository {
    HashMap<String, PlanetSystem> planetSystemsHashMap = new HashMap<>();
    private File fileName;
    public UniverseCSVRepository(String fileName){
        this.fileName = new File(fileName);

        Star kepler11 = new Star("Kepler-11",1.889E30,710310,5680,"https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png");
        ArrayList<Planet> keplerList = new ArrayList<>();
        keplerList.add(new Planet("Kepler-11b",2.56796E25,12550,1.36134E7,0.045,10,kepler11,"https://asd.gsfc.nasa.gov/blueshift/wp-content/uploads/2015/07/PIA19562-Ceres-DwarfPlanet-Dawn-RC3-image19-20150506.jpg"));
        keplerList.add(new Planet("Kepler-11c",8.0622E25,20068,1.5857E7,0.026,13,kepler11,"https://asd.gsfc.nasa.gov/blueshift/wp-content/uploads/2015/07/PIA19562-Ceres-DwarfPlanet-Dawn-RC3-image19-20150506.jpg"));
        keplerList.add(new Planet("Kepler-11d",3.64292E25,21852,2.3786E7,0.004,22,kepler11,"https://asd.gsfc.nasa.gov/blueshift/wp-content/uploads/2015/07/PIA19562-Ceres-DwarfPlanet-Dawn-RC3-image19-20150506.jpg"));
        keplerList.add(new Planet("Kepler-11e",5.01648E25,28796,2.9021E7,0.012,31,kepler11,"https://asd.gsfc.nasa.gov/blueshift/wp-content/uploads/2015/07/PIA19562-Ceres-DwarfPlanet-Dawn-RC3-image19-20150506.jpg"));
        keplerList.add(new Planet("Kepler-11f",1.37356E25,16628,3.7399E7,0.013,36,kepler11,"https://asd.gsfc.nasa.gov/blueshift/wp-content/uploads/2015/07/PIA19562-Ceres-DwarfPlanet-Dawn-RC3-image19-20150506.jpg"));
        keplerList.add(new Planet("Kepler-11g",1.7916E27,23317,6.9114E7,0.015,118,kepler11,"https://asd.gsfc.nasa.gov/blueshift/wp-content/uploads/2015/07/PIA19562-Ceres-DwarfPlanet-Dawn-RC3-image19-20150506.jpg"));
        PlanetSystem keplerSystem = new PlanetSystem("Kepler-System", kepler11,keplerList,"https://upload.wikimedia.org/wikipedia/commons/6/64/Kepler11.png");

        Star sun = new Star("The Sun", 1.9885E30, 695342, 5777, "https://en.wikipedia.org/wiki/Sun#/media/File:Sun_white.jpg");
        ArrayList<Planet> planetList = new ArrayList<>();
        planetList.add(new Planet("Mercury", 3.283E23, 2439.7, 0.387, 0.206, 88, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Transit_Of_Mercury%2C_May_9th%2C_2016.png/480px-Transit_Of_Mercury%2C_May_9th%2C_2016.png"));
        planetList.add(new Planet("Venus", 4.867E24, 6051.8, 0.723, 0.007, 225, sun, "https://upload.wikimedia.org/wikipedia/commons/e/e5/Venus-real_color.jpg"));
        planetList.add(new Planet("Earth", 5.972E24, 6371, 1, 0.017, 365, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/NASA_Earth_America_2002.jpg/480px-NASA_Earth_America_2002.jpg"));
        planetList.add(new Planet("Mars", 6.39E23, 3389.5, 1.524, 0.093, 687, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Mars_23_aug_2003_hubble.jpg/480px-Mars_23_aug_2003_hubble.jpg"));
        planetList.add(new Planet("Jupiter", 1.898E27, 69911, 5.20440, 0.049, 4380, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Jupiter_and_its_shrunken_Great_Red_Spot.jpg/480px-Jupiter_and_its_shrunken_Great_Red_Spot.jpg"));
        planetList.add(new Planet("Saturn", 5.683E26, 58232, 9.5826, 0.057, 10585, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1c/Saturn_from_Cassini_Orbiter_-_Square_%282004-10-06%29.jpg/480px-Saturn_from_Cassini_Orbiter_-_Square_%282004-10-06%29.jpg"));
        planetList.add(new Planet("Uranus", 8.681E25, 25362, 19.2184, 0.046, 30660, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3d/Uranus2.jpg/480px-Uranus2.jpg"));
        planetList.add(new Planet("Neptune", 1.024E26, 24622, 30.11, 0.010, 60225, sun, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Neptune_Full_%28cropped%29.jpg/480px-Neptune_Full_%28cropped%29.jpg"));

        PlanetSystem solarSystem = new PlanetSystem("Solar System", sun, planetList, "https://upload.wikimedia.org/wikipedia/commons/c/c3/Solar_sys8.jpg");

        planetSystemsHashMap.put("Solar System", solarSystem);
        planetSystemsHashMap.put("Kepler-System",keplerSystem);

        ArrayList<PlanetSystem> readPlanets = readPlanetFromFile(new File(fileName));
        System.out.println("planets read from file: ");
        System.out.println(readPlanets);
        System.out.println("////end of planets read from file////");


//        System.out.println("getAllPlanets: ");
//        System.out.println(getAllPlanets("Solar System"));
//        //deletePlanet("Solar System", "Earth");
//        System.out.println("getPlanet: ");
//        System.out.println(getPlanet("Solar System", "Earth"));
//        System.out.println("getPlanetSystem: ");
//        System.out.println(getPlanetSystem("Solar System"));
//        System.out.println("getAllPlanetSystems: ");
//        System.out.println(getAllPlanetSystems());
    }



    public  ArrayList<PlanetSystem> readPlanetFromFile(File file) {
        HashMap<String, PlanetSystem> planetsFromFile = new HashMap<>();

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] splitter = line.split(",");

                if (!planetsFromFile.containsKey(splitter[0])) {
                    planetsFromFile.put(splitter[0], new PlanetSystem(splitter[0], new Star(splitter[2], Double.parseDouble(splitter[3]),
                            Double.parseDouble(splitter[4]), Double.parseDouble(splitter[5]), splitter[6]), new ArrayList<>(), splitter[1]));
                }
                planetsFromFile.get(splitter[0]).getPlanetList().add(new Planet(splitter[7], Double.parseDouble(splitter[8]), Double.parseDouble(splitter[9]),
                        Double.parseDouble(splitter[10]), Double.parseDouble(splitter[11]), Double.parseDouble(splitter[12]), planetsFromFile.get(splitter[0]).getCenterStar(), splitter[13]));
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioexc) {
            System.out.println(ioexc.getLocalizedMessage());
        }
        return new ArrayList<>(planetsFromFile.values());
    }


    public  void writePlanetToFile(File file, HashMap<String,PlanetSystem> planetSystems){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            for (PlanetSystem planetSystem : planetSystems.values()) {
                ArrayList<Planet> planets = planetSystem.getPlanets();
                for (Planet planet : planets) {
                    String line = planetSystem.getName() + "," + planetSystem.getPictureUrl() + "," + planetSystem.getCenterStar().getName() + "," + planetSystem.getCenterStar().getMass() +
                            "," + planetSystem.getCenterStar().getRadius() + "," + planetSystem.getCenterStar().getEffectiveTemp() + "," + planetSystem.getCenterStar().getPictureUrl() + "," +
                            planet.getName() + "," + planet.getMass() + "," + planet.getRadius() + "," + planet.getSemiMajorAxis() + "," + planet.getEccentricity() + "," + planet.getOrbitalPeriod() +
                            "," + planet.getPictureUrl();;
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.flush();
        } catch (FileNotFoundException fnfe) {
        System.out.println(fnfe.getMessage());
        } catch (IOException ioexc) {
            System.out.println(ioexc.getLocalizedMessage());
        }
    }


    @Override
    public ArrayList<Planet> getAllPlanets(String solarSystemName) {
        return planetSystemsHashMap.get(solarSystemName).getPlanets();
    }

    @Override
    public Planet getPlanet(String solarSystemName, String planetName) {
        return planetSystemsHashMap.get(solarSystemName).getPlanet(planetName);
    }

    @Override
    public PlanetSystem getPlanetSystem(String solarSystemName) {
       return planetSystemsHashMap.get(solarSystemName);
    }

    @Override
    public Collection<PlanetSystem> getAllPlanetSystems() {
        return  planetSystemsHashMap.values();
    }

    @Override
    public void createPlanet(String name, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystem) {
        planetSystemsHashMap.get(planetSystem).getPlanetList().add(new Planet(name,mass,radius,semiMajorAxis,eccentricity,orbitalPeriod,planetSystemsHashMap.get(planetSystem).getCenterStar(),pictureUrl));
        writePlanetToFile(fileName,planetSystemsHashMap);
    }


    @Override
    public void updatePlanet(String originalName,String newName, double mass, double radius, double semiMajorAxis, double eccentricity, double orbitalPeriod, String pictureUrl, String planetSystem) {
        planetSystemsHashMap.get(planetSystem).getPlanet(originalName).setAll(newName, mass, radius, semiMajorAxis, eccentricity, orbitalPeriod, planetSystemsHashMap.get(planetSystem).getCenterStar() , pictureUrl);
        writePlanetToFile(fileName,planetSystemsHashMap);
    }

    @Override
    public void deletePlanet(String planetSystem, String planet) {
      planetSystemsHashMap.get(planetSystem).getPlanetList().remove(planetSystemsHashMap.get(planetSystem).getPlanet(planet));
        writePlanetToFile(fileName,planetSystemsHashMap);
    }

}
