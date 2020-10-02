<template>
  <div id="main-track-item">
    <div class="track-box">
      <ItemTitle :item="item" :id="id" @emit-value-id="emitClick"></ItemTitle>
      <ItemContent :item="item"></ItemContent>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import ItemTitle from "./TrackrItemTitle";
import ItemContent from "./TrackrItemContent";

export default Vue.extend({
  name: "TrackrItem",
  components: { ItemTitle, ItemContent },
  props: {
    item: {
      type: Object,
      required: true,
    },
    id: Number,
  },
  methods: {
    ultimo() {
      if (item.movimentacoes.length > 0) {
        return item.movimentacoes[item.movimentacoes.length - 1];
      }
    },
    emitClick(id) {
      this.$emit("emit-value-id", id);
    },
  },
});
</script>

<style>
.ui-box,
.ui-panel {
  margin-bottom: 16px;
}
.ui-box--lite {
  border: 1px solid #eee;
}

.ui-badge,
.ui-box--lite {
  background-color: #fff;
}

.ui-box {
  margin-bottom: 15px;
  position: relative;
}
/* .track-box {
  border: 1px solid #eee;

  margin-bottom: 25px;
  font-family: Arial, Helvetica, "Nimbus Sans L", sans-serif;
  font-size: 13px;
  line-height: 20px;
  color: #333;
} */
</style>
