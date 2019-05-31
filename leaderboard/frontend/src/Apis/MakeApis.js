import Axios from 'axios'

export default {
  makePostRequest (path, callback, payload) {
    Axios.post(path, payload)
      .then(callback)
      .catch((error) => { console.log(error) })
  },
  makeGetContestRequest (path, callback) {
    console.log('PATH:', path)
    Axios.get(path)
      .then(callback)
      .catch((error) => { console.log(error) })
  },
  makeDeleteRequest (path, callback, payload) {
    console.log('PATH:', path)
    Axios.delete(path, {
      params: { 'cartId': payload.cartId }
    })
      .then(callback)
      .catch((error) => { console.log(error) })
  },
  makeGetOverallRequest(path,callback){
      Axios.get(path)
        .then(callback)
        .catch((error) => { console.log(error)})
  }
}