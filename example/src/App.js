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
  getFilmList, getPeopleList, getPlanetList, getSpeciesList, getStarshipList, getVehicleList,
  getFilmbyId, getPeoplebyId, getPlanetbyId, getSpeciesbyId, getStarshipbyId, getVehiclebyId
} from 'rn-swapi-plugin';
import { Data, fun_name } from './dummy_data';


export default function App() {

  var myObject = new MyClass();

  // for list
  function MyClass() {
    this.getMovies = async () => {
      getFilmList(function (data) {
        console.log("Result of getmovieList ", data);
      });
    }
    this.getPersons = async () => {
      getPeopleList({ search: 2 }, function (data) {
        console.log("Result of getPersons", data);
      });
    }
    this.getPlanets = async () => {
      getPlanetList( function (data) {
        console.log("Result of getPlanets", data);
      });
    }
    this.getSpecies = async () => {
      getSpeciesList({ search: 2 }, function (data) {
        console.log("Result of getSpecies", data);
      });
    }
    this.getStarships = async () => {
      getStarshipList({ search: 2 }, function (data) {
        console.log("Result of getStarships", data);
      });
    }
    this.getVehicles = async () => {
      getVehicleList({ search: 2 }, function (data) {
        console.log("Result of getVehicles", data);
      });
    }

    //for id

    this.getmoviesById = async () => {
      getFilmbyId(2, function (data) {
        console.log("Result of getmoviewithId", data);
      });
    }
    this.getPersonById = async () => {
      getPeoplebyId(2, function (data) {
        console.log("Result of getpeoplewithId ", data);
      });
    }
    this.getPlanetById = async () => {
      getPlanetbyId(2, function (data) {
        console.log("Result of getplanetwithId", data);
      });
    }
    this.getSpeciesById = async () => {
      getSpeciesbyId(2, function (data) {
        console.log("Result of getspecieswithId", data);
      });
    }
    this.getStarshipById = async () => {
      getStarshipbyId(2, function (data) {
        console.log("Result of getstarshipwithId", data);
      });
    }
    this.getVehicleById = async () => {
      getVehiclebyId(2, function (data) {
        console.log("Result of getvehiclewithId", data);
      });
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
