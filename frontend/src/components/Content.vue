<template>
  <main role="main" class="center col-md-9 col-lg-10 px-md-4">
    <div id="container" v-if="items.length > 0">
      <br />
      <h2>Rastreios para {{ cpf }}</h2>
      <br />
      <TrackingItem
        v-for="(item, index) in items"
        :key="item.id"
        :item="item"
        :id="index"
        @emit-value-id="emitClick"
        :registrar="registrar"
        :rastreio="rastreio"
      ></TrackingItem>
    </div>
  </main>
</template>

<script>
import Vue from "vue";
import TrackingItem from "./tracking-box/TrackingItem";

export default Vue.extend({
  name: "Content",
  components: { TrackingItem },
  props: {
    items: Array,
    cpf: String,
    registrar: {
      required: true,
      type: Boolean,
    },
    rastreio: String,
  },
  methods: {
    emitClick(id) {
      this.$emit("emit-value-id", id);
    },
  },
});
</script>

<style>
.center {
  margin: auto;
}
</style>
