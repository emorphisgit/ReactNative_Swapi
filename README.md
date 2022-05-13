
# SWAPI-React-Native-Plugin

A simple wrapper to the Star Wars API. Include the library and then make calls to the various API end points

## Installation

Install rn-swapi-plugin with npm

```bash
  npm install rn-swapi-plugin
  
```
Install rn-swapi-plugin with yarn

```bash
  yarn add rn-swapi-plugin
  
```
    
## Usage/Examples

Every method below can take a callback argument as their final argument.

```javascript
import { getAllFilm,getFilm} from "rn-swapi-plugin";


// get all Films
 getAllFilm(function(data)
 { console.log("All results of Films", data);
 });

// get all Films for page 2
 getAllFilm({page:2},function (data)
 {console.log("All results that match (page 2) ", data);
 });

 // get all Films for search string "r2"
 getAllFilm({search:'r2'},function (data)
 { console.log("All results that match  (search r2)", data);
 });

// get all Films for page 2 and Films and search string "r2"
 getAllFilm({page:2,search:'r2'},function (data)
 { console.log("All results that match  (page 2,search r2)", data);
 });

// get Films by id
 getFilm(4,function (data)
 { console.log("All results that match 4 workers", data);
 });
```

#**Methods**
        

            getPeople(id) - Returns details of Specific person.
            getAllPeople({page: page number, search: search string}) - Returns details of everyone

            getFilm(id) - Returns details of Specific film.
            getAllFilm({page: page number, search: search string}) - Returns all the film resources .

            getPlanet(id) - Returns details of Specific planet.
            getAllPlanet({page: page number, search: search string}) - Returns all the planets resources .
          
            getSpecies(id) - Returns details of Specific species.
            getAllSpecies({page: page number, search: search string}) - Returns all the species resources .
          
            getStarship(id) - Returns details of Specific starship.
            getAllStarship({page: page number], search: search string}) - Returns all the starships resources .
           
            getVehicle(id) - Returns details of Specific vehicle.
            getAllVehicle({page: page number, search: search string}) - Returns all the vehicles resources .    


Every method that returns multiple results, e.g., getPeopleList(), take an optional object as their first argument with two optional properties:

**1.**   page: an integer\
**2.**   search: a search string



