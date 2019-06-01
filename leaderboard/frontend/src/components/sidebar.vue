<template>
<aside class='sidebar' >
    <header class="sidebar__header">
        <i @click="toggleSidebar" class="material-icons sidebar__close">close</i>
       <img src="https://badgeos.org/wp-content/uploads/edd/2013/11/leaderboard.png" alt="BB Logo" style="height:70px;width:70px;"><br>
        <p class='sidebar__header-text' >Leaderboard</p>
    </header>
    <nav class="sidebar__nav">
        <!-- <router-link class="sidebar__nav-item" to="/leaderboard/map">
            <i class="material-icons">star</i> Contest Leaderboard
        </router-link> -->
        
        <!--
        <router-link  class="sidebar__nav-item" to="/leaderboard/user">
            <i class="material-icons">person</i> Personal Leaderboard
        </router-link>
        -->
         <router-link  class="sidebar__nav-item" to="/report">
            <i class="material-icons">group</i> Reports 
        </router-link>


        <router-link  class="sidebar__nav-item" to="/winner">
            <i class="material-icons">group</i> Winners 
        </router-link>
    </nav>
    <hr class="sidebar__divider" />
    <MapList /> 
</aside>
</template>
 
<script>
import MapList from './mapList';
export default {
name: 'sidebar',
methods: {
    toggleSidebar(){
        document.querySelector('.sidebar').classList.toggle('sidebar--active');
    }
},
components: {
    MapList
}
};
</script>
 
<style lang='scss' scoped>
@import '../assets/_scss/_sidebar';
</style>