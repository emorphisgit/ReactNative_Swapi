
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
import { getFilmList,getFilmbyId} from "rn-swapi-plugin";


// get all Films
 getFilmList(function(data)
 { console.log("All results of Films", data);
 });

// get all Films for page 2
 getFilmList({page:2},function (data)
 {console.log("All results that match (page 2) ", data);
 });

 // get all Films for search string "r2"
 getFilmList({search:'r2'},function (data)
 { console.log("All results that match  (search r2)", data);
 });

// get all Films for page 2 and Films and search string "r2"
 getFilmList({page:2,search:'r2'},function (data)
 { console.log("All results that match  (page 2,search r2)", data);
 });

// get one Films (assumes 4 works)
 getFilmbyId(4,function (data)
 { console.log("All results that match 4 workers", data);
 });
```

#**Methods**
        

            getPeoplebyId(id) - Returns details of Specific person.
            getPeopleList({page: page number, search: search string}) - Returns details of everyone

            getFilmbyId(id) - Returns details of Specific film.
            getFilmList({page: page number, search: search string}) - Returns all the film resources .

            getPlanetbyId(id) - Returns details of Specific planet.
            getPlanetList({page: page number, search: search string}) - Returns all the planets resources .
          
            getSpeciesbyId(id) - Returns details of Specific species.
            getSpeciesList({page: page number, search: search string}) - Returns all the species resources .
          
            getStarshipbyId(id) - Returns details of Specific starship.
            getStarshipList({page: page number], search: search string}) - Returns all the starships resources .
           
            getVehiclebyId(id) - Returns details of Specific vehicle.
            getVehicleList({page: page number, search: search string}) - Returns all the vehicles resources .    


Every method that returns multiple results, e.g., getPeopleList(), take an optional object as their first argument with two optional properties:

**1.**   page: an integer\
**2.**   search: a search string



