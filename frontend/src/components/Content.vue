<template>
  <main role="main" class="center col-md-9 col-lg-10 px-md-4">
    <div id="container" v-if="items.length > 0">
      <br />
      <h2>Rastreios para {{ cpf }}</h2>
      <br />
      <Item
        v-for="(ite, index) in items"
        :key="ite"
        :item="ite"
        :id="index"
        @emit-value-id="emitClick"
      ></Item>
    </div>
  </main>
</template>

<script>
import Vue from "vue";
import TrackrItem from "./TrackrItem.vue";
import Item from "./Item"

export default Vue.extend({
  name: "Content",
  components: { Item },
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
.center {
  margin: auto;
}
</style>
