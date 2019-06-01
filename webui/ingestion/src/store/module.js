import todoApis from '../apis/todoApis.js'

export default {
    state: {
        fileUploadMessage: '',
    },
    getters: {
        DownloadFile : (state) => state.fileUploadMessage

    },
    mutations: {
        DOWNLOADING_FILE: (state, result) => {
            
            state.fileUploadMessage = result.data
        }
    },
    actions: {
        Upload: (context,file) =>{
            todoApis.DownloadFile((result)=>{
                if(result.data!= "success"){
                    alert(result.data)
                    console.log("ok = false "+result.data)
                }
                else{
                    context.commit('DOWNLOADING_FILE',result.data)
                    console.log("ok=true "+result.data.data)
                }
            },file)
        }
	}
 }
