function get_port() {
  return (!process.env.dev) ?
    location.port :
    process.env.dev
}

function get_host() {
  return location.hostname
}

export async function request(body, type, port=get_port(), host = location.hostname){
  if(port === ""){
    port = location.port;
  }
  if(host === ""){
    host = location.hostname;
  }
  return fetch('http://' + host + ":" + port + '/' + type, {
    method:"POST",
    body: JSON.stringify(body)
  }).then(response => {return response.json()}).catch(err => {console.error(err)});
}

export async function get_config(type, host = location.hostname, port=get_port()) {
  return fetch('http://' + host + ":" + port + '/config', {
    method:"GET"
  }).then(response => {return response.json()}).catch(err => {console.error(err)});
}