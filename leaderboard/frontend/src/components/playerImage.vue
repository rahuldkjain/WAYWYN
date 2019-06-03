<template>
<div>
    <img :class="className" v-if="loading" src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRZ0CLenc8ZCs6kgKYg0Pe2IhiSlNRygACCC1JzkKioGWFr-Ar7g' alt="Loading..." style="height:55px;width:45px">
    <img :class="className" v-else :src='imgUrl' />
</div>
</template>
 
<script>
export default {
    name: 'playerImage',
    props: ['className', 'steamId'],
    data() {
        return {
            loading: true,
            imgUrl: '',
            error: null
        }
    },
    created () {
        this.getPlayerImage(this.steamId);
    },
    methods: {
        getPlayerImage(id) {
            let url = 'http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=7BA7B2ACDDBDD5D85F4109CB6FCB30DA&steamids=' + id;
            fetch(url)
            .catch(err => this.error = err)
            .then(res => {
                return res.json();
            })
            .then(results => {
                this.imgUrl = results.response.players['0'].avatarmedium;
                this.loading = false;
            });
        }
    },
};
</script>