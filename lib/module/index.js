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

export async function getmovieList(requestdata, cb) {
  if (requestdata) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(`${BASEURL}/films/?${searchParams.toString()}`, cb);
  }
}
export async function getpeopleList(requestdata, cb) {
  if (requestdata) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(BASEURL + '/people/?' + searchParams.toString(), cb);
  }
}
export async function getplanetList(requestdata, cb) {
  if (requestdata) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(BASEURL + '/planets/?' + searchParams.toString(), cb);
  }
}
export async function getspeciesList(requestdata, cb) {
  if (requestdata) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(BASEURL + '/species/?' + searchParams.toString(), cb);
  }
}
export async function getstarshipList(requestdata, cb) {
  if (requestdata) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(BASEURL + '/starships/?' + searchParams.toString(), cb);
  }
}
export async function getvehicleList(requestdata, cb) {
  if (requestdata) {
    let searchParams = new URLSearchParams();

    for (let key of Object.keys(requestdata)) {
      let value = requestdata[key];
      searchParams.append(key, value);
    }

    return getdatafromServerInList(`${BASEURL}/vehicles/?${searchParams.toString()}`, cb);
  }
} //for id

export async function getmoviewithId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/films/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getpeoplewithId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/people/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getplanetwithId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/planets/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getspecieswithId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/species/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getstarshipwithId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/starships/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
export async function getvehiclewithId(requestdata, cb) {
  if (typeof requestdata == 'number') {
    return getdatafromServerForId(`${BASEURL}/vehicles/${requestdata}`, cb);
  } else {
    Promise.reject('id should be number');
  }
}
//# sourceMappingURL=index.js.map