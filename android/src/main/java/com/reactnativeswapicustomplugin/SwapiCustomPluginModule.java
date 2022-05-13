package com.reactnativeswapicustomplugin;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.gson.Gson;
import com.swapi.swModels.SWFilm;
import com.swapi.swModels.SWModelList;
import com.swapi.swModels.SWPeople;
import com.swapi.swModels.SWPlanet;
import com.swapi.swModels.SWSpecies;
import com.swapi.swModels.SWStarship;
import com.swapi.swModels.SWVehicle;
import com.swapi.swNetworkCall.SWAPIs;
import com.swapi.swNetworkCall.SWClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//@ReactModule(name = SwapiCustomPluginModule.NAME)
public class SwapiCustomPluginModule extends ReactContextBaseJavaModule {
  public static final String NAME = "SwapiCustomPlugin";
  public static final String TAG = SwapiCustomPluginModule.class.getName();
  public SWAPIs swapiInterface;

  public SwapiCustomPluginModule(ReactApplicationContext reactContext) {
    super(reactContext);
    swapiInterface = SWClient.getInstanceServices();
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void CallAllPeopleApi(Promise promise) {
    Call<SWModelList<SWPeople>> call = swapiInterface.getPeoples();
    call.enqueue(new Callback<SWModelList<SWPeople>>() {
      @Override
      public void onResponse(Call<SWModelList<SWPeople>> call, retrofit2.Response<SWModelList<SWPeople>> response) {
        SWModelList<SWPeople> res = response.body();
        Gson gson = new Gson();
        try {
          if (res.getResults() != null) {
            JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
            WritableArray array = new WritableNativeArray();
            for (int i = 0; i < jsonArray.length(); i++) {
              WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
              array.pushMap(collectionDictionary);
            }
            Log.d(TAG, "CallAllPeopleApi....." + array);
            promise.resolve(array);
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWPeople>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllPeopleApi error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallPeopleApi(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWPeople> call = swapiInterface.getPeopleByID(jsonObject.getInt("id"));
    call.enqueue(new Callback<SWPeople>() {
      @Override
      public void onResponse(@NonNull Call<SWPeople> call, @NonNull Response<SWPeople> response) {
        SWPeople res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            JSONObject jsonObject = new JSONObject(gson.toJson(res));
            WritableMap collectionDictionary = convertJsonToMap(jsonObject);
            Log.d(TAG, "CallPeopleApi....." + collectionDictionary);
            promise.resolve(collectionDictionary);
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWPeople> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallPeopleApi error" + t.toString());
        promise.reject(t.toString());
      }
    });
  }

  @ReactMethod
  public void CallAllFilmApi(Promise promise) {
    Call<SWModelList<SWFilm>> call = swapiInterface.getFilms();
    call.enqueue(new Callback<SWModelList<SWFilm>>() {
      @Override
      public void onResponse(Call<SWModelList<SWFilm>> call, retrofit2.Response<SWModelList<SWFilm>> response) {
        SWModelList<SWFilm> res = response.body();
        Gson gson = new Gson();
        try {
          if (res.getResults() != null) {
            JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
            WritableArray array = new WritableNativeArray();
            for (int i = 0; i < jsonArray.length(); i++) {
              WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
              array.pushMap(collectionDictionary);
            }
            Log.d(TAG, "CallAllFilmApi....." + array);
            promise.resolve(array);
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWFilm>> call, @NonNull Throwable t) {
        call.cancel();
        Log.e(TAG, "CallAllFilmApi error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallFilmApi(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Log.e(TAG, "jsonobject>>>>>>> " + jsonObject);
    Call<SWFilm> call = swapiInterface.getFilmByID(jsonObject.getInt("id"));
    call.enqueue(new Callback<SWFilm>() {
      @Override
      public void onResponse(@NonNull Call<SWFilm> call, @NonNull Response<SWFilm> response) {
        SWFilm res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            JSONObject jsonObject = new JSONObject(gson.toJson(res));
            WritableMap collectionDictionary = convertJsonToMap(jsonObject);
            Log.d(TAG, "CallFilmApi....." + collectionDictionary);
            promise.resolve(collectionDictionary);
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWFilm> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallFilmApi error" + t.toString());
        promise.reject(t.toString());
      }
    });
  }


  @ReactMethod
  public void CallAllPlanetApi(Promise promise) {
    Call<SWModelList<SWPlanet>> call = swapiInterface.getPlanets();
    call.enqueue(new Callback<SWModelList<SWPlanet>>() {
      @Override
      public void onResponse(Call<SWModelList<SWPlanet>> call, retrofit2.Response<SWModelList<SWPlanet>> response) {
        SWModelList<SWPlanet> res = response.body();
        Gson gson = new Gson();
        try {
          if (res.getResults() != null) {
            JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
            WritableArray array = new WritableNativeArray();
            for (int i = 0; i < jsonArray.length(); i++) {
              WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
              array.pushMap(collectionDictionary);
            }
            Log.d(TAG, "CallAllPlanetApi....." + array);
            promise.resolve(array);
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWPlanet>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllPlanetApi error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallPlanetApi(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWPlanet> call = swapiInterface.getPlanetByID(jsonObject.getInt("id"));
    call.enqueue(new Callback<SWPlanet>() {
      @Override
      public void onResponse(@NonNull Call<SWPlanet> call, @NonNull Response<SWPlanet> response) {
        SWPlanet res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            JSONObject jsonObject = new JSONObject(gson.toJson(res));
            WritableMap collectionDictionary = convertJsonToMap(jsonObject);
            Log.d(TAG, "CallPlanetApi....." + collectionDictionary);
            promise.resolve(collectionDictionary);
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWPlanet> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallPlanetApi error" + t.toString());
        promise.reject(t.toString());
      }
    });
  }

  @ReactMethod
  public void CallAllSpeciesApi(Promise promise) {
    Call<SWModelList<SWSpecies>> call = swapiInterface.getSpecies();
    call.enqueue(new Callback<SWModelList<SWSpecies>>() {
      @Override
      public void onResponse(Call<SWModelList<SWSpecies>> call, retrofit2.Response<SWModelList<SWSpecies>> response) {
        SWModelList<SWSpecies> res = response.body();
        Gson gson = new Gson();
        try {
          if (res.getResults() != null) {
            JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
            WritableArray array = new WritableNativeArray();
            for (int i = 0; i < jsonArray.length(); i++) {
              WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
              array.pushMap(collectionDictionary);
            }
            Log.d(TAG, "CallAllSpeciesApi....." + array);
            promise.resolve(array);
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWSpecies>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, " CallAllSpeciesApi error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallSpeciesApi(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWSpecies> call = swapiInterface.getSpeciesByID(jsonObject.getInt("id"));
    call.enqueue(new Callback<SWSpecies>() {
      @Override
      public void onResponse(@NonNull Call<SWSpecies> call, @NonNull Response<SWSpecies> response) {
        SWSpecies res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            JSONObject jsonObject = new JSONObject(gson.toJson(res));
            WritableMap collectionDictionary = convertJsonToMap(jsonObject);
            Log.d(TAG, "CallSpeciesApi....." + collectionDictionary);
            promise.resolve(collectionDictionary);
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWSpecies> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallSpeciesApi error" + t.toString());
        promise.reject(t.toString());
      }
    });
  }

  @ReactMethod
  public void CallAllStarshipApi(Promise promise) {
    Call<SWModelList<SWStarship>> call = swapiInterface.getStarships();
    call.enqueue(new Callback<SWModelList<SWStarship>>() {
      @Override
      public void onResponse(Call<SWModelList<SWStarship>> call, retrofit2.Response<SWModelList<SWStarship>> response) {
        SWModelList<SWStarship> res = response.body();
        Gson gson = new Gson();
        try {
          if (res.getResults() != null) {
            JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
            WritableArray array = new WritableNativeArray();
            for (int i = 0; i < jsonArray.length(); i++) {
              WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
              array.pushMap(collectionDictionary);
            }
            Log.d(TAG, "CallAllStarshipApi....." + array);
            promise.resolve(array);
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWStarship>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllStarshipApi error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallStarshipApi(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWStarship> call = swapiInterface.getStarshipByID(jsonObject.getInt("id"));
    call.enqueue(new Callback<SWStarship>() {
      @Override
      public void onResponse(@NonNull Call<SWStarship> call, @NonNull Response<SWStarship> response) {
        SWStarship res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            JSONObject jsonObject = new JSONObject(gson.toJson(res));
            WritableMap collectionDictionary = convertJsonToMap(jsonObject);
            Log.d(TAG, "CallStarshipApi....." + collectionDictionary);
            promise.resolve(collectionDictionary);
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWStarship> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallStarshipApi error " + t.toString());
        promise.reject(t.toString());
      }
    });
  }

  @ReactMethod
  public void CallAllVehicleApi(Promise promise) {
    Call<SWModelList<SWVehicle>> call = swapiInterface.getVehicles();
    call.enqueue(new Callback<SWModelList<SWVehicle>>() {
      @Override
      public void onResponse(Call<SWModelList<SWVehicle>> call, retrofit2.Response<SWModelList<SWVehicle>> response) {
        SWModelList<SWVehicle> res = response.body();
        Gson gson = new Gson();
        try {
          if (res.getResults() != null) {
            JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
            WritableArray array = new WritableNativeArray();
            for (int i = 0; i < jsonArray.length(); i++) {
              WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
              array.pushMap(collectionDictionary);
            }
            Log.d(TAG, "CallAllVehicleApi....." + array);
            promise.resolve(array);
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWVehicle>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllVehicleApi error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallVehicleApi(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWVehicle> call = swapiInterface.getVehicleByID(jsonObject.getInt("id"));
    call.enqueue(new Callback<SWVehicle>() {
      @Override
      public void onResponse(@NonNull Call<SWVehicle> call, @NonNull Response<SWVehicle> response) {
        SWVehicle res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            JSONObject jsonObject = new JSONObject(gson.toJson(res));
            WritableMap collectionDictionary = convertJsonToMap(jsonObject);
            Log.d(TAG, "CallVehicleApi....." + collectionDictionary);
            promise.resolve(collectionDictionary);
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWVehicle> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallVehicleApi error" + t.toString());
        promise.reject(t.toString());
      }
    });
  }


  // with search arguments
  @ReactMethod
  public void CallAllPeopleApiBySearch(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWPeople>> call = swapiInterface.getPeopleSearch(jsonObject.getString("search"));
    call.enqueue(new Callback<SWModelList<SWPeople>>() {
      @Override
      public void onResponse(Call<SWModelList<SWPeople>> call, retrofit2.Response<SWModelList<SWPeople>> response) {
        SWModelList<SWPeople> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllPeopleApiBySearch....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWPeople>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllPeopleApiBySearch error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllFilmApiBySearch(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWFilm>> call = swapiInterface.getFilmSearch(jsonObject.getString("search"));
    call.enqueue(new Callback<SWModelList<SWFilm>>() {
      @Override
      public void onResponse(Call<SWModelList<SWFilm>> call, retrofit2.Response<SWModelList<SWFilm>> response) {
        SWModelList<SWFilm> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllFilmApiBySearch....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWFilm>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllFilmApiBySearch error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllPlanetApiBySearch(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWPlanet>> call = swapiInterface.getPlanetSearch(jsonObject.getString("search"));
    call.enqueue(new Callback<SWModelList<SWPlanet>>() {
      @Override
      public void onResponse(Call<SWModelList<SWPlanet>> call, retrofit2.Response<SWModelList<SWPlanet>> response) {
        SWModelList<SWPlanet> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllPlanetApiBySearch....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWPlanet>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllPlanetApiBySearch error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllSpeciesApiBySearch(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWSpecies>> call = swapiInterface.getSpeciesSearch(jsonObject.getString("search"));
    call.enqueue(new Callback<SWModelList<SWSpecies>>() {
      @Override
      public void onResponse(Call<SWModelList<SWSpecies>> call, retrofit2.Response<SWModelList<SWSpecies>> response) {
        SWModelList<SWSpecies> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllSpeciesApiBySearch....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWSpecies>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllSpeciesApiBySearch error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllStarshipApiBySearch(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWStarship>> call = swapiInterface.getStarshipSearch(jsonObject.getString("search"));
    call.enqueue(new Callback<SWModelList<SWStarship>>() {
      @Override
      public void onResponse(Call<SWModelList<SWStarship>> call, retrofit2.Response<SWModelList<SWStarship>> response) {
        SWModelList<SWStarship> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllStarshipApiBySearch....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWStarship>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllStarshipApiBySearch error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllVehicleApiBySearch(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWVehicle>> call = swapiInterface.getVehicleSearch(jsonObject.getString("search"));
    call.enqueue(new Callback<SWModelList<SWVehicle>>() {
      @Override
      public void onResponse(Call<SWModelList<SWVehicle>> call, retrofit2.Response<SWModelList<SWVehicle>> response) {
        SWModelList<SWVehicle> res = response.body();
        Gson gson = new Gson();
        try {
          Log.e(TAG, "response.code() " + response.code());
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllVehicleApiBySearch....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWVehicle>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllVehicleApiBySearch error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  // api with page

  @ReactMethod
  public void CallAllPeopleApiByPage(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWPeople>> call = swapiInterface.getPeoplesByPages(jsonObject.getInt("page"));
    call.enqueue(new Callback<SWModelList<SWPeople>>() {
      @Override
      public void onResponse(Call<SWModelList<SWPeople>> call, retrofit2.Response<SWModelList<SWPeople>> response) {
        SWModelList<SWPeople> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllPeopleApiByPage....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWPeople>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllPeopleApiByPage error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllFilmApiByPage(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Log.d(TAG, "jsobject............  " + jsonObject);
    Call<SWModelList<SWFilm>> call = swapiInterface.getFilmsByPages(jsonObject.getInt("page"));
    call.enqueue(new Callback<SWModelList<SWFilm>>() {
      @Override
      public void onResponse(Call<SWModelList<SWFilm>> call, retrofit2.Response<SWModelList<SWFilm>> response) {
        SWModelList<SWFilm> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllFilmApiByPage....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWFilm>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllFilmApiByPage error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllPlanetApiByPage(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWPlanet>> call = swapiInterface.getPlanetByPages(jsonObject.getInt("page"));   // wrong method name
    call.enqueue(new Callback<SWModelList<SWPlanet>>() {
      @Override
      public void onResponse(Call<SWModelList<SWPlanet>> call, retrofit2.Response<SWModelList<SWPlanet>> response) {
        SWModelList<SWPlanet> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllPlanetApiByPage....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWPlanet>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllPlanetApiByPage error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllSpeciesApiByPage(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWSpecies>> call = swapiInterface.getSpeciesByPages(jsonObject.getInt("page"));
    call.enqueue(new Callback<SWModelList<SWSpecies>>() {
      @Override
      public void onResponse(Call<SWModelList<SWSpecies>> call, retrofit2.Response<SWModelList<SWSpecies>> response) {
        SWModelList<SWSpecies> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllSpeciesApiByPage....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWSpecies>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllSpeciesApiByPage error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllStarshipApiByPage(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWStarship>> call = swapiInterface.getStarshipByPages(jsonObject.getInt("page"));
    call.enqueue(new Callback<SWModelList<SWStarship>>() {
      @Override
      public void onResponse(Call<SWModelList<SWStarship>> call, retrofit2.Response<SWModelList<SWStarship>> response) {
        SWModelList<SWStarship> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllStarshipApiByPage....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWStarship>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllStarshipApiByPage error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }

  @ReactMethod
  public void CallAllVehicleApiByPage(ReadableMap readableMap, Promise promise) throws JSONException {
    JSONObject jsonObject = readableMapToJson(readableMap);
    Call<SWModelList<SWVehicle>> call = swapiInterface.getVehicleByPages(jsonObject.getInt("page"));
    call.enqueue(new Callback<SWModelList<SWVehicle>>() {
      @Override
      public void onResponse(Call<SWModelList<SWVehicle>> call, retrofit2.Response<SWModelList<SWVehicle>> response) {
        SWModelList<SWVehicle> res = response.body();
        Gson gson = new Gson();
        try {
          if (response.code() == 200) {
            if (res.getResults() != null) {
              JSONArray jsonArray = new JSONArray(gson.toJson(res.getResults()));
              WritableArray array = new WritableNativeArray();
              for (int i = 0; i < jsonArray.length(); i++) {
                WritableMap collectionDictionary = convertJsonToMap(jsonArray.getJSONObject(i));
                array.pushMap(collectionDictionary);
              }
              Log.d(TAG, "CallAllVehicleApiByPage....." + array);
              promise.resolve(array);
            }
          } else {
            promise.resolve("no data found");
          }
        } catch (JSONException e) {
          promise.reject(e);
        }
      }

      @Override
      public void onFailure(@NonNull Call<SWModelList<SWVehicle>> call, @NonNull Throwable t) {
        call.cancel();
        Log.d(TAG, "CallAllVehicleApiByPage error ...... " + t.toString());
        promise.reject(t.toString());
      }

    });
  }


  private static WritableMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
    WritableMap map = new WritableNativeMap();


    Iterator<String> iterator = jsonObject.keys();
    while (iterator.hasNext()) {
      String key = iterator.next();
      Object value = jsonObject.get(key);

      if (value instanceof JSONObject) {
        map.putMap(key, convertJsonToMap((JSONObject) value));
      } else if (value instanceof Boolean) {
        map.putBoolean(key, (Boolean) value);
      } else if (value instanceof Integer) {
        map.putInt(key, (Integer) value);
      } else if (value instanceof Double) {
        map.putDouble(key, (Double) value);
      } else if (value instanceof String) {
        map.putString(key, (String) value);
      } else if (value instanceof JSONArray) {
        map.putArray(key, jsonArrayToWritableArray((JSONArray) value));
      } else {
        map.putString(key, value.toString());
      }
    }
    return map;
  }

  @Nullable
  public static WritableArray jsonArrayToWritableArray(JSONArray jsonArray) {
    WritableArray writableArray = new WritableNativeArray();

    if (jsonArray == null) {
      return null;
    }

    if (jsonArray.length() <= 0) {
      return null;
    }

    for (int i = 0; i < jsonArray.length(); i++) {
      try {
        Object value = jsonArray.get(i);
        if (value == null) {
          writableArray.pushNull();
        } else if (value instanceof Boolean) {
          writableArray.pushBoolean((Boolean) value);
        } else if (value instanceof Integer) {
          writableArray.pushInt((Integer) value);
        } else if (value instanceof Double) {
          writableArray.pushDouble((Double) value);
        } else if (value instanceof String) {
          writableArray.pushString((String) value);
        } else if (value instanceof JSONObject) {
          writableArray.pushMap(convertJsonToMap((JSONObject) value));
        } else if (value instanceof JSONArray) {
          writableArray.pushArray(jsonArrayToWritableArray((JSONArray) value));
        }
      } catch (JSONException e) {
        // Do nothing and fail silently
      }
    }

    return writableArray;
  }

  @Nullable
  public static JSONObject readableMapToJson(ReadableMap readableMap) {
    JSONObject jsonObject = new JSONObject();
    if (readableMap == null) {
      return null;
    }
    ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
    if (!iterator.hasNextKey()) {
      return null;
    }
    while (iterator.hasNextKey()) {
      String key = iterator.nextKey();
      ReadableType readableType = readableMap.getType(key);
      try {
        switch (readableType) {
          case Null:
            jsonObject.put(key, null);
            break;
          case Boolean:
            jsonObject.put(key, readableMap.getBoolean(key));
            break;
          case Number:
            jsonObject.put(key, readableMap.getInt(key));
            break;
          case String:
            jsonObject.put(key, readableMap.getString(key));
            break;
          case Map:
            jsonObject.put(key, readableMapToJson(readableMap.getMap(key)));
            break;
          default:
            // Do nothing and fail silently
        }
      } catch (JSONException ex) {
      }
    }
    return jsonObject;
  }
}
