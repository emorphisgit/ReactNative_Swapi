export const BASEURL = 'https://swapi.dev/api';

async function getdatafromServerInList(url, cb) {
  return await fetch(url).then(res => res.json()).then(function (data) {
    if (data.detail) return cb(data.detail);
    console.log('result......', data.results, data, url);

    if (typeof cb === "function") {
      cb(data.results);
    }

    return data.results;
  }).catch(function (err) {
    console.log('error', err);
    cb(err);
  });
}

async function getdatafromServerForId(url, cb) {
  return await fetch(url).then(res => res.json()).then(function (data) {
    if (data.detail) return cb(data.detail);

    if (typeof cb === "function") {
      cb(data);
    }

    return data;
  }).catch(function (err) {
    console.log('error', err);
    cb(err);
  });
}

export async function getFilmList(requestdata, cb) {
  let cb1 = undefined;

  if (typeof requestdata != 'function' && arguments.length > 1) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(`${BASEURL}/films/?${searchParams.toString()}`, cb);
  } else {
    cb1 = arguments[0];
    return getdatafromServerInList(`${BASEURL}/films/`, cb1);
  }
}
export async function getPeopleList(requestdata, cb) {
  let cb1 = undefined;

  if (typeof requestdata != 'function' && arguments.length > 1) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(BASEURL + '/people/?' + searchParams.toString(), cb);
  } else {
    cb1 = arguments[0];
    return getdatafromServerInList(`${BASEURL}/people/`, cb1);
  }
}
export async function getPlanetList(requestdata, cb) {
  let cb1 = undefined;

  if (typeof requestdata != 'function' && arguments.length > 1) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(BASEURL + '/planets/?' + searchParams.toString(), cb);
  } else {
    cb1 = arguments[0];
    return getdatafromServerInList(`${BASEURL}/planets/`, cb1);
  }
}
export async function getSpeciesList(requestdata, cb) {
  let cb1 = undefined;

  if (typeof requestdata != 'function' && arguments.length > 1) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(BASEURL + '/species/?' + searchParams.toString(), cb);
  } else {
    cb1 = arguments[0];
    return getdatafromServerInList(`${BASEURL}/species/`, cb1);
  }
}
export async function getStarshipList(requestdata, cb) {
  let cb1 = undefined;

  if (typeof requestdata != 'function' && arguments.length > 1) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(BASEURL + '/starships/?' + searchParams.toString(), cb);
  } else {
    cb1 = arguments[0];
    return getdatafromServerInList(`${BASEURL}/starships/`, cb1);
  }
}
export async function getVehicleList(requestdata, cb) {
  let cb1 = undefined;

  if (typeof requestdata != 'function' && arguments.length > 1) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(`${BASEURL}/vehicles/?${searchParams.toString()}`, cb);
  } else {
    cb1 = arguments[0];
    return getdatafromServerInList(`${BASEURL}/vehicles/`, cb1);
  }
} //for id

export async function getFilmbyId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/films/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getPeoplebyId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/people/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getPlanetbyId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/planets/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getSpeciesbyId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/species/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getStarshipbyId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/starships/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getVehiclebyId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/vehicles/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
//# sourceMappingURL=index.js.map