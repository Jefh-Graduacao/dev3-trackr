<template>
  <div class="track-box-2">
    <div class="track-info">
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

import Botao from "./base/Botao.vue";
import TrackingHeading from "./tracking-box/TrackingHeading.vue";
import TrackingContent from "./tracking-box/TrackingContent.vue"

export default Vue.extend({
  name: "Item",
  components: { TrackingHeading, TrackingContent },
  props: {
    item: {
      type: Object,
      required: true,
    },
    id: Number,
  },
  methods: {
    click() {
      alert("aaa");
    },
    emitClick(id: number) {
      this.$emit("emit-value-id", id);
    },
    getImgUrl: function (name: string) {
      var images = require.context("../assets/transp", false, /\.png$/);
      return images("./" + name + ".png");
    },
    getLogoTransportadora: function () {
      return this.getImgUrl("Trackr_LOGO");
    },
  },
});
</script>

<style>
.track-box-2 {
  border: 1px solid #eee;
  padding: 15px;
  margin-bottom: 25px;
  font-family: "Poppins";
  font-size: 13px;
  line-height: 20px;
  color: #333;
  display: flex;
  flex-direction: row;
}

.track-info {
  width: 100%;
  /* background: lightblue; */
}

.track-buttons {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 10px;
  /* background: lightcoral; */
}

.track-buttons button:not(:last-child) {
  margin-bottom: 15px;
}

.flex-teste {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.track-events {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  /* background: lightgray; */
}

.track-events img {
  width: 100px;
  height: 100px;
  /* background: lightgreen; */
}

.track-events .boxxx {
  width: 100px;
  height: 100px;
  font-size: 75px;
  line-height: 100%;
  /* background: lightgreen; */
}

.track-events-details {
  margin: auto 0 auto 20px;
  /* width: 75%; */
  /* background: lightgoldenrodyellow; */
}

.track-events-details p {
  font-size: 16px;
  line-height: 30px;
}
</style>
