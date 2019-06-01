import axios from 'axios'
export default {
    makePostRequest(path,callback,file){
        axios.post(path,file)
        .then(callback)
        .catch((error) => {console.log(error)})
    },
    // makeGetRequest(path,callback) {
    //     axios.get(path)
    //         .then(callback)
    //         .catch((error) => { console.log(error) })
    // },
    // makeSearchRequest(path,callback){


        // {
        //     'Content-Type': 'multipart/form-data',
        //     'Accept': 'application/json'
        // }
        
    //     //console.log('PATH:' ,path)
    //     axios.get(path)
    //     .then(callback)
    //     .catch((error)=>{ console.log(error) })
    // },
    // makeProductRequest(path,callback){
         
    //     axios.get(path)
    //     .then(callback)
    //     .catch((error) => console.log(error))
    // },
    // makeMerchantRequest(path,callback){
    //     axios.get(path)
    //     .then(callback)
    //     .catch((error)=>console.log(error))
    // },
    // //new test user signout login

    // makeGetUserRequest (path, callback){
	
	// 	axios.get(path)
	// 		.then(callback)
	// 		.catch((error) => {console.log(error)})
    // },
	// makePostUserRequest(path, callback, jsonObject){
	// 	axios.post(path, jsonObject)
	// 		.then(callback)
	// 		.catch((error) => {console.log(error)})
	// },
	// makePostLoginRequest(path, callback){
	// 	axios.post(path)
	// 		.then(callback)
	// 		.catch((error) =>{console.log(error)})
	// },
	// makeDeleteUserRequest(path,callback) {
	// 	axios.delete(path)
	// 		.then(callback)
	// 		.catch((error) => {console.log(error) })
    // },
    // makeAddItemToCartRequest(path,callback){
    //     axios.post(path)
    //     .then(callback)
    //     .catch((error) => {console.log(error)})
    // },
    // makeGetitemFromCartRequest(path,callback){
    //     axios.get(path)
    //     .then(callback)
    //     .catch((error) => {console.log(error)})
    // },
    // makeDeleteItemRequest(path,callback){
    //     axios.delete(path)
    //     .then(callback)
    //     .catch((error) => {console.log(error)})
    // },
    // makePostOrderRequest(path,callback){
    //     axios.post(path)
    //     .then(callback)
    //     .catch((error) => {console.log(error)})
    // },
    // makeGetOrderHistoryRequest(path,callback){
    //     axios.get(path)
    //     .then(callback)
    //     .catch((error) => {console.log(error)})
    // },
    // makePutRequest(path,callback,editedObject){
    //     axios.put(path, editedObject)
    //     .then(callback)
    //     .catch( (error) => {console.log(error)})
    // }
}