import { AppRegistry } from 'react-native';
import App from './src/App';
import Swapi_App from './src/Swapi_App';
import { name as appName } from './app.json';

AppRegistry.registerComponent(appName, () => Swapi_App);
