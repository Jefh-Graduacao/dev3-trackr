<template>
  <main role="main" class="col-md-9 pd_final col-lg-10 px-md-4">
    <div id="container" v-if="item">
      <br />
      <h2>Detalhes do Rastreio</h2>
      <br />
      <TrackrItemDetails
        :movimentacoes="item.movimentacoes"
      ></TrackrItemDetails>
    </div>
  </main>
</template>

<script>
import Vue from "vue";
import TrackrItemDetails from "./TrackrItemDetails.vue";

export default Vue.extend({
  name: "ContentDetails",
  components: { TrackrItemDetails },
  props: {
    item: Object,
  },
});
</script>

<style>
</style>
