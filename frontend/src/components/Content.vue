<template>
  <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
    <div id="container" v-if="items.length > 0">
      <h2>Rastreios</h2>
      <TrackrItem v-for="ite in items" :key="ite" :item="ite"></TrackrItem>
    </div>
  </main>
</template>

<script>
import Vue from "vue";
import TrackrItem from "./TrackrItem.vue";

export default Vue.extend({
  name: "Content",
  components: { TrackrItem },
  props: {
    items: Array,
  },
});
</script>

<style>
</style>
