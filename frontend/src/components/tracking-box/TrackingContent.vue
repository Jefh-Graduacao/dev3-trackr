<template>
  <div class="tracking-events">
    <TrackingDetailsModal v-if="showModal" @close="showModal = false" :movimentacoes="item.movimentacoes" />
    <div class="flex-teste2">
      <div class="boxxx">🚚</div>
      <div class="tracking-events-details">
        <p>{{ item.movimentacoes.length }} eventos registrados</p>
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
      </div>
    </div>
    <div class="tracking-buttons">
      <Botao tipo="primario" :onClick="click">
        Ver detalhes
      </Botao>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import Botao from "../base/Botao.vue"
import TrackingDetailsModal from './TrackingDetailsModal.vue'

export default Vue.extend({
  name: "TrackingContent",
  components: { Botao, TrackingDetailsModal },
  props: {
    item: Object
  },
  methods: {
    click: function() {
      this.showModal = true
    }
  },
  data: function() {
    return {
      showModal: false
    }
  }
});
</script>

<style scoped>
.tracking-buttons {
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  padding: 10px;
}

.tracking-buttons button:not(:last-child) {
  margin-bottom: 15px;
}

.flex-teste2 {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
}

.tracking-events {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}

.tracking-events img {
  width: 100px;
  height: 100px;
}

.tracking-events .boxxx {
  width: 100px;
  height: 100px;
  font-size: 75px;
  line-height: 100%;
}

.tracking-events-details {
  margin: auto 0 auto 20px;
}

.tracking-events-details p {
  font-size: 16px;
  line-height: 30px;
}
</style>
