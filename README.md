# react-native-swapi-custom-plugin

swapi plugin
A simple wrapper to the Star Wars API. Include the library and then make calls to the various API end points. Current methods are


getpeoplewithId(id) - Returns one person.
getpeopleList({page: page number, search: search string}) - Returns everyone, paged. Defaults to page 1.
getmoviewithId(id) - Returns one film.
getmovieList({page: page number, search: search string}) - Returns all films, paged. Defaults to page 1.
getplanetwithId(id) - Returns a planet.
getplanetList({page: page number, search: search string}) - Returns all planets, paged. Defaults to page 1.
getspecieswithId(id) - Returns one species.
getspeciesList({page: page number, search: search string}) - Returns all species, paged. Defaults to page 1.
getstarshipwithId(id) - Returns a starship.
getstarshipList({page: page number], search: search string}) - Returns all starships, paged. Defaults to page 1.
getvehiclewithId(id) - Returns a vehicle.
getvehicleList({page: page number, search: search string}) - Returns all vehicles, paged. Defaults to page 1.

## Installation

```sh
npm install react-native-swapi-custom-plugin
```

## Usage

```js
import { getmovieList } from "react-native-swapi-custom-plugin";

// ...
// get all Films
getmovieList(function (data)
 { 
	 console.log("Result of getmovieList ", data);
 });

// get one Films (assumes 4 works)
 getmoviewithId(4,function (data)
 { 
	 console.log("Result of getmovie ", data);
 });

// get all Films, page 2
 getmovieList({page:2},function (data)
 {
	  console.log("Result of getmovieList that match "2" ", data);
 });

// get all Films, page 2 and Films with search string "r2"
 getmovieList({page:2,search:'r2'},function (data)
 { 
	 console.log("Result of getmovieList (page 2,search r2)", data);
 });

// get all results for Fmils with search string "r2"
getpeopleList({search: 'r2'}, function(data) 
{
    console.log("All results that match "r2", data);
});


// get all vehicles
getvehicleList(function(data) {
	console.log("Result of getVehicles", data);
});

// get all vehicles, page 2
getpeopleList({page: 2}, function(data) {
    console.log("All results that match "2", data);
});

// get all vehicles, page 2 and vehicles with search string "r2"
getvehicleList({page: 2,search:"r2"}, function(data) {
	console.log("Result of getVehicles (page 2,search r2)", data);
});

// get one vehicle (assumes 4 works)
getvehiclewithId(4,function(data) {
	console.log("Result of getVehicle/4", data);
});

// get all results for people with search string "r2"
getpeopleList({search: "r2"}, function(data) {
    console.log("All results that match "r2", data);
});
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
