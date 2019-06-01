<template>
  <div class="fileupload">
      <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <div class="wrapper">
             <div class="file-upload"> 
            <input v-on:change="handleFileUpload()" id="file" ref="file" type="file" />
            <i class="fa fa-arrow-up"></i>
         </div> 
         <button v-on:click="submitFile()">Upload</button>
  </div>
  </div>
</template>
<script>
import axios from 'axios'
import {mapActions, mapGetters} from 'vuex'

 window.onload = function() {
	
}

export default {
  name: 'FileUpload',
  data(){
      return {
        file: ''
      }
  },
  computed: {
      ...mapActions(['Upload'])
     

    },
  methods: {
   
    submitFile(){

  //       var videoUrls = window.ytplayer.config.args.url_encoded_fmt_stream_map.split(',').map(function(){
	// 	return item.split("&").reduce(function(pre,cur){
	// 		cur = cur.split("=");
	// 		return Object.assign(pre, {[cur[0]]: decodeURIComponent(cur[1])});
	// 	},{});
	// });
	console.log("hora hai ",videoUrls);
        /*
                Initialize the form data

            */
           
            let formData = new FormData();

            /*
                Add the form data we need to submit
            */
            formData.append('file', this.file);

        /*
          Make the request to the POST /single-file URL
        */
            console.log(this.file);
            
          this.$store.dispatch('Upload', formData)
        
      },

      /*
        Handles a change on the file upload
      */
      handleFileUpload(){
        this.file = this.$refs.file.files[0];
        
      }
    
    // ,UploadFile(){
    //   this.$store.dispatch('fileUpload',)
    // }
  }
}
</script>
<style>

      html{
        height:30%;
      }
      body{
        
        background-color:#2590EB;
        height:100%;
      }
      .wrapper{       
        width:100%;
        height:100%;
        display:flex;
        align-items:center;
        justify-content:center;   
        position: absolute;
        /* margin-top: 0px  */
      }

      .file-upload{     
          height:200px;
          width:200px;
          border-radius: 100px;
          position:relative;
          
          display:flex;
          justify-content:center;
          align-items: center;  
      
          border:4px solid #FFFFFF;
          overflow:hidden;
          background-image: linear-gradient(to bottom, #2590EB 50%, #FFFFFF 50%);
          background-size: 100% 200%;
          transition: all 1s;
          color: #FFFFFF;
          font-size:100px;
      }     
          input[type='file']{ 
            height:200px;
            width:200px;
            position:absolute;
            top:0;
            left:0;
            opacity:0;
            cursor:pointer;
      
          } 
          button{
                  position: absolute;
                  margin-top: 120px;
          }  
           :hover{
             
            background-position: 0 -100%;
      
            color:#2590EB;
      
          } 
</style>
