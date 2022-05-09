# react-native-swapi-custom-plugin

swapi plugin
A simple wrapper to the Star Wars API. Include the library and then make calls to the various API end points. Current methods are


getPeoplebyId(id) - Returns details of one person.\
getPeopleList({page: page number, search: search string})(arguments are optional) - Returns details of everyone, search string. Defaults to page - 1 .\

getFilmbyId(id) - Returns details of one film.\
getFilmList({page: page number, search: search string})(arguments are optional) - Returns deatils of all films, paged,  search string. Defaults to page - 1 .\

getPlanetbyId(id) - Returns details of one planet.\
getPlanetList({page: page number, search: search string})(arguments are optional) - Returns all planets, paged,search string. Defaults to page - 1 .\

getSpeciesbyId(id) - Returns details of one species.\
getSpeciesList({page: page number, search: search string})(arguments are optional) - Returns all species, paged, search string. Defaults to page - 1 .\

getStarshipbyId(id) - Returns details of one starship.\
getStarshipList({page: page number], search: search string})(arguments are optional) - Returns all starships, paged, search string . Defaults to page - 1 .\

getVehiclebyId(id) - Returns details of one vehicle.\
getVehicleList({page: page number, search: search string})(arguments are optional) - Returns all vehicles, paged, search string. Defaults to page - 1 .

## Installation

```sh
npm install react-native-swapi-custom-plugin
```

## Usage

```js
import { getFilmList,getFilmbyId} from "react-native-swapi-custom-plugin";

// get all Films
 getFilmList(function(data)
 { console.log("All results of Films", data);
 });

// get all Films, page 2
 getFilmList({page:2},function (data)
 {console.log("All results that match (page 2) ", data);
 });

 // get all Films, search string "r2"
 getFilmList({search:'r2'},function (data)
 { console.log("All results that match  (search r2)", data);
 });

// get all Films, page 2 and Films with search string "r2"
 getFilmList({page:2,search:'r2'},function (data)
 { console.log("All results that match  (page 2,search r2)", data);
 });

// get one Films (assumes 4 works)
 getFilmbyId(4,function (data)
 { console.log("All results that match 4 workers", data);
 });
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
