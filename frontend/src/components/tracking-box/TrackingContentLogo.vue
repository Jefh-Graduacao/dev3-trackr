<template>
  <div v-if="checarEntrega()" class="boxxx">📦</div>
  <div v-else class="boxxx">🚚</div>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "TrackingContentLogo",
  components: {},
  props: {
    title: String,
  },
  methods: {
    checarEntrega: function () {
      return /entregue/.exec(this.title.toLowerCase());
    },
  },
  data: function () {
    return {};
  },
});
</script>

<style>
</style>
