<template>
<div class='map-list' >

    <div class="loading" v-if="loading">
      Loading...
    </div>

    <div v-if="error" class="error">
        Error: {{ error }}
    </div>

    <h2 v-if="mapList.data" class="map-list__header" >Maps</h2>
    <div v-if="mapList.data" class="map-list__list">
        <div class="map-list__list-item" v-for="(map, index) in mapList.data" @click="setQuery(map)" :key="map.name + index" append>
            {{ map["map_name"].replace('surf_', '').charAt(0).toUpperCase() + map["map_name"].replace('surf_', '').slice(1) }}
        </div>
    </div>
</div>
</template>
 
<script>
export default {
name: 'mapList',
    data () {
        return {
            loading: false,
            error: null,
            mapList: {}
        }
  },
  created () {
  },
  methods: {
    setQuery(map) {
        this.$router.push({ query: { mapid: map['map_id'] }});
        console.log(this.router)
    }
  }
};
</script>
 
<style lang='scss' scoped>
@import '../assets/_scss/_sidebar';
</style>