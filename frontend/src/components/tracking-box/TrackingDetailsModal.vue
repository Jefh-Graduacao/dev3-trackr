<template>
  <transition name="tracking-modal">
    <div class="tracking-modal-mask">
      <div class="tracking-modal-wrapper">
        <div class="tracking-modal-container">
          <div class="tracking-modal-header">
            <slot name="header">
              <h3>Detalhes da entrega</h3>
              <strong> {{ movimentacoes.length }} movimentações </strong>
            </slot>
          </div>

          <div class="tracking-modal-body">
            <slot name="body">
              <div
                class="tracking-mov"
                v-for="(item, index) in movimentacoes"
                :key="index"
                :item="item"
              >
                <h5>
                  {{ item.titulo }}
                </h5>

                {{ item.dataHora }}<br />
                {{ item.situacao }}<br />
                {{ item.local }}<br />
              </div>
            </slot>
          </div>

          <div class="tracking-modal-footer">
            <div>
              {{ movimentacoes.length }} movimentações registradas com início em
              <strong>
                {{
                  new Date(movimentacoes[0].dataHora).toLocaleString("pt-BR", {
                    year: "numeric",
                    month: "numeric",
                    day: "numeric",
                    hour: "numeric",
                    minute: "numeric",
                  })
                }}
              </strong>
            </div>
            <Botao
              class="tracking-modal-default-button"
              tipo="terciario"
              :onClick="close"
            >
              Fechar
            </Botao>
          </div>
          <!-- Ver comenário no CSS desta classe -->
          <div class="space" />
        </div>
      </div>
    </div>
  </transition>
</template>

<script lang="js">
import Botao from "../base/Botao.vue";

export default {
  name: "Modal",
  components: { Botao },
  props: {
    movimentacoes: Array,
  },
  methods: {
    close() {
      this.$emit("close")
    },
  },
};
</script>


<style lang="css" scoped>
.tracking-modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.tracking-modal-wrapper {
  display: table-cell;
  vertical-align: middle;
  overflow-y: scroll;
}

.tracking-modal-container {
  width: 60%;
  max-height: 80%;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: #fff;
  border-radius: 2px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  overflow-y: scroll;
  height: 600px;
  display: flex;
  flex-direction: column;
}

.tracking-modal-header h3 {
  margin-top: 0;
  color: #f03a17;
}

.tracking-modal-body {
  margin: 20px 0;
}

.tracking-modal-default-button {
  float: right;
}

.tracking-mov {
  padding: 5px 0;
}

.tracking-mov:not(:last-child) {
  margin-bottom: 10px;
}

/*
  Isso é necessário por causa de um bug no Firefox onde ele
  não respeita paddings quando é setado um overflow
  Link do bug: https://bugzilla.mozilla.org/show_bug.cgi?id=748518
  Stack Overflow: https://stackoverflow.com/questions/13471910
*/
@supports (-moz-appearance: none) {
  .space {
    display: block;
    width: 100%;
    padding: 10px;
  }
}

.tracking-modal-footer {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: baseline;
}

.tracking-modal-enter {
  opacity: 0;
}

.tracking-modal-leave-active {
  opacity: 0;
}

.tracking-modal-enter .tracking-modal-container,
.tracking-modal-leave-active .tracking-modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
