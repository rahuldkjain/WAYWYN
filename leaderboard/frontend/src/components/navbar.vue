<template>
<nav class='navbar' >
    <div class="wrapper">
       
        <i @click="toggleSidebar" class="material-icons navbar__toggle">menu</i>
        <span style="position:relative;right:420px;color:white" > {{date}}</span>
        <router-link  class="navbar__logo" to="/">
            <img src="https://badgeos.org/wp-content/uploads/edd/2013/11/leaderboard.png" alt="BB Logo" style="height:70px;width:70px;">
        </router-link>
        
        <!-- 
            <span v-if="signedIn" class="navbar__user" ><p class="navbar__greet"> Hey, {{ username }} </p> <router-link to="/leaderboard/user"><PlayerImage className="navbar__user-image"  :steamId="user.steamId"/></router-link> </span>
            <a v-else href="#"  class="navbar__user"><img src='../assets/steamsignin.png'/></a>
        -->
        <span class="navbar__map"> {{ this.mapName }} </span>
        
    </div>
</nav>
</template>
 
<script>
import PlayerImage from './playerImage';

export default {
    name: 'navbar',
    props: ['user'],
    data() {
        return {
            signedIn: false,
            username: null,
            error: null,
            mapName: "",
            date: new Date(),
        }
    },
    created () {
        // if(this.user) this.fetchUser();
        this.getMapName();
    },
    watch: {
        user: "fetchUser",
        '$route.query.mapid': "getMapName",
        date: function(){
            this.date = new Date
        }
    },
    methods: {
        toggleSidebar(){
            document.querySelector('.sidebar').classList.toggle('sidebar--active');
        },
        // fetchUser() {
        //     let url = 'http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=7BA7B2ACDDBDD5D85F4109CB6FCB30DA&steamids=' + this.user.steamId;
        //     fetch(url)
        //     .catch(err => this.error = err)
        //     .then(res => {
        //         return res.json();
        //     })
        //     .then(results => {
        //         this.username = results.response.players['0'].personaname;
        //         this.signedIn = true;
        //     });
        // },
        getMapName() {
            this.mapName = "";
            if(this.$route.query.mapid) {
                let url = 'https://api.bbroleplay.co.uk/v1/games/surf/mapinfo/' + this.$route.query.mapid || 2
                fetch(url,
                { 
                    method: 'post', 
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .catch(err => this.error = err)
                .then(res => {
                    return res.json()
                })
                .then(result => {
                    this.mapName = result.data['map_name'].replace('surf_', '').charAt(0).toUpperCase() + result.data['map_name'].replace('surf_', '').slice(1)
                })
            } else {
                this.mapName = "QuizHum";
            }
        },
        updateTime(){
            this.date=new Date()
        }
    },
    components: {
        PlayerImage
    },
    computed: {
        now(){
          return new Date
        }
    },
    mounted(){
        setInterval(()=>{
            this.updateTime()
        },1000)
    }
};
</script>
 
<style lang='scss' scoped>
    @import '../assets/_scss/_navbar';
</style>