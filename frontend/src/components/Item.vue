<template>
  <div class="track-box-2">
    <div class="track-info">
      <div class="track-title">
        <h5>
          {{ item.movimentacoes[0].titulo }}
        </h5>
        <p>
          Atividade registrada em
          {{
            new Date(item.movimentacoes[0].dataHora).toLocaleString("pt-BR", {
              year: "numeric",
              month: "long",
              day: "numeric",
              hour: "numeric",
              minute: "numeric",
            })
          }}
        </p>
      </div>
      <div class="track-events">
        <div class="flex-teste">
          <div class="boxxx">🚚</div>
          <div class="track-events-details">
            <p>{{ item.movimentacoes.length }} eventos registrados</p>
            <p>
              Primeiro evento em
              {{
                new Date(item.movimentacoes[0].dataHora).toLocaleString("pt-BR", {
                  year: "numeric",
                  month: "numeric",
                  day: "numeric",
                  hour: "numeric",
                  minute: "numeric",
                })
              }}
            </p>
            <p>
              Último evento em
              {{
                new Date(
                  item.movimentacoes[item.movimentacoes.length - 1].dataHora
                ).toLocaleString("pt-BR", {
                  year: "numeric",
                  month: "numeric",
                  day: "numeric",
                  hour: "numeric",
                  minute: "numeric",
                })
              }}
            </p>
          </div>
        </div>
        <div class="track-buttons">
          <Botao texto="Ver detalhes" tipo="primario" />
          <Botao texto="Sincronizar" tipo="primario"/>
          {{  }}
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";

import Botao from './Botao.vue'

export default Vue.extend({
  name: "Item",
  components: { Botao },
  props: {
    item: {
      type: Object,
      required: true,
    },
    id: Number,
  },
  methods: {
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

.track-title {
  display: flex;
  flex-direction: column;
}

.track-title h5, p {
  margin: auto;
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
