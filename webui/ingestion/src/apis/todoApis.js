import makeApiCall from './makeApiCalls.js'
export default {
    DownloadFile(callback, file) {
        var url = '/api/file/upload'
        makeApiCall.makePostRequest(url,callback, file)
    },
}