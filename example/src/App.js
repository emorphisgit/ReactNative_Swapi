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
   getmovieList, getpeopleList, getplanetList, getspeciesList, getstarshipList, getvehicleList,
   getmoviewithId, getpeoplewithId, getplanetwithId, getspecieswithId, getstarshipwithId, getvehiclewithId
 } from 'react-native-swapi-custom-plugin';
 import { Data, fun_name } from './dummy_data';
 
 
 export default function App() {
 
   var myObject = new MyClass();
 
   // for list
   function MyClass() {
    this.getMovies = async () => {
       getmovieList({}, function (data) {
         console.log("Result of getmovieList ", data);
       });
     }
     this.getPersons = async () => {
       getpeopleList({ search: 2 }, function (data) {
         console.log("Result of getPersons", data);
       });
     }
     this.getPlanets = async () => {
       getplanetList({ search: 2 }, function (data) {
         console.log("Result of getPlanets", data);
       });
     }
     this.getSpecies = async () => {
       getspeciesList({ search: 2 }, function (data) {
         console.log("Result of getSpecies", data);
       });
     }
     this.getStarships = async () => {
       getstarshipList({ search: 2 }, function (data) {
         console.log("Result of getStarships", data);
       });
     }
     this.getVehicles = async () => {
       getvehicleList({ search: 2 }, function (data) {
         console.log("Result of getVehicles", data);
       });
     }
 
     //for id
 
     this.getmoviesById = async () => {
       getmoviewithId(2, function (data) {
         console.log("Result of getmoviewithId", data);
       });
     }
     this.getPersonById = async () => {
       getpeoplewithId(2, function (data) {
         console.log("Result of getpeoplewithId ", data);
       });
     }
     this.getPlanetById = async () => {
       getplanetwithId(2, function (data) {
         console.log("Result of getplanetwithId", data);
       });
     }
     this.getSpeciesById = async () => {
       getspecieswithId(2, function (data) {
         console.log("Result of getspecieswithId", data);
       });
     }
     this.getStarshipById = async () => {
       getstarshipwithId(2, function (data) {
         console.log("Result of getstarshipwithId", data);
       });
     }
     this.getVehicleById = async () => {
       getvehiclewithId(2, function (data) {
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
 