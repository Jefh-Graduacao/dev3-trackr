<template>
  <main role="main" class="col-md-9 pd_final col-lg-10 px-md-4">
    <div id="container" v-if="items.length > 0">
      <br />
      <h2>Rastreios para {{ cpf }}</h2>
      <br />
      <TrackrItem
        v-for="(ite, index) in items"
        :key="ite"
        :item="ite"
        :id="index"
        @emit-value-id="emitClick"
      ></TrackrItem>
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
    cpf: String,
  },
  methods: {
    emitClick(id) {
      this.$emit("emit-value-id", id);
    },
  },
});
</script>

<style>
.pd_final {
  padding-left: 100px !important;
}
</style>
