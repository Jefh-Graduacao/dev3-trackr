<template>
  <div class="tracking-box">
    <div class="tracking-info">
      <TrackingHeading
        :title="item.movimentacoes[0].titulo"
        :subtitle="`Atividade registrada em
          ${new Date(item.movimentacoes[0].dataHora).toLocaleString('pt-BR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
          })}`"
      />

      <TrackingContent :item="item" />

    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

import TrackingHeading from "./TrackingHeading.vue";
import TrackingContent from "./TrackingContent.vue"

export default Vue.extend({
  name: "TrackingItem",
  components: { TrackingHeading, TrackingContent },
  props: {
    item: {
      type: Object,
      required: true,
    },
    id: Number,
  },
  methods: { },
});
</script>

<style>
.tracking-box {
  border: 1px solid rgb(219, 219, 219);
  border-radius: 3px;
  padding: 15px;
  margin-bottom: 25px;
  font-size: 13px;
  line-height: 20px;
  color: #333;
  display: flex;
  flex-direction: row;
}

.tracking-info {
  width: 100%;
}
</style>
