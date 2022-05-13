import React, { useEffect, useState } from 'react';
import {
  StatusBar,
  Text,
  View,
  StyleSheet,
  TouchableOpacity,
  FlatList
} from 'react-native';
import {
  getAllPeople, getPeople, getAllFilm,getFilm,getAllPlanet,getPlanet,getAllSpecies,getSpecies,getAllStarship,getStarship,getAllVehicle,getVehicle
} from 'rn-swapi-plugin';
import { Data, fun_name } from './dummy_data';


export default function Swapi_App() {

  var myObject = new MyClass();

  // for list
  function MyClass() {
    this.getMovies = async () => {
      await getAllFilm({page:2}).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getPersons = async () => {
      await getAllPeople({search:'Luke'}).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getPlanets = async () => {
      await getAllPlanet({search:'Geonosis'}).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getSpecies = async () => {
      await getAllSpecies({search:'Neimodian'}).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getStarships = async () => {
      await getAllStarship({search:'Slave'}).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getVehicles = async () => {
      await getAllVehicle({page:23}).then((array) => {
        console.log("Result of.....", array);
      })
    }

    //for id

    this.getmoviesById = async () => {
      await getFilm(2).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getPersonById = async () => {
      await getPeople(2).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getPlanetById = async () => {
      await getPlanet(2).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getSpeciesById = async () => {
      await getSpecies(2).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getStarshipById = async () => {
      await getStarship(2).then((array) => {
        console.log("Result of.....", array);
      })
    }
    this.getVehicleById = async () => {
      await getVehicle(2).then((array) => {
        console.log("Result of.....", array);
      })
    }
  }

  const renderItem = ({ item, index }) => {
    return (
      <TouchableOpacity onPress={() => myObject[fun_name[index]]()} style={styles.button}>
        <Text style={styles.text}>{item.name}</Text>
      </TouchableOpacity>
    )
  }


  return (
    <View style={styles.container}>
      <StatusBar hidden />
      <View style={{ flex: 1, margin: 20 }}>
        <FlatList
          data={Data}
          renderItem={renderItem}
          ItemSeparatorComponent={(props) => {
            return (<View style={{ height: 1, backgroundColor: 'grey' }} />);
          }}
        />
      </View>

    </View >
  );
}

const styles = StyleSheet.create({

  container: {
    flex: 1,

  },
  text: {
    margin: 4,
    fontSize: 17,
    color: 'black',
  },
  button: {
    borderRadius: 10,
    padding: 10,
    marginTop: 10,

  },
  separator: {
    color: 'black'
  }

});
