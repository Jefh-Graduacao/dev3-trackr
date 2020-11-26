<template>
  <div class="topbar">
    <div class="trackr-brand">
      <svg
          width="65px"
          height="65px"
          viewBox="0 0 70 70"
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink">
          <image href="../assets/logo.png" width="65px" height="60px" />
      </svg>

      <span class="nome-container">{{nome}}</span>
    </div>

    <div class="pesquisa-container">
      <select name="" id="" v-model="selectedType">
        <option
          v-for="origem in origens"
          v-bind:key="origem"
          :label="origem"
          :title="origem"
          :value="origem"
        >
          {{ tti }}
        </option>
      </select>
      <input
        type="text"
        v-on:keyup.enter="emitValue()"
        v-model="searchData"
      >
      <button class="bt-pesquisa" v-on:click="emitValue()">
         <svg width="22" height="22" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
          <circle cx="9.40909" cy="9.40909" r="8.15909" stroke="white" stroke-width="2.5" stroke-linejoin="round"/>
          <line x1="16.7678" y1="16.0454" x2="22" y2="21.2776" stroke="white" stroke-width="2.5" stroke-linecap="round"/>
        </svg>
      </button>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import { TipoBotao } from "./base/Botao.vue"

export default Vue.extend({
  name: "NavBar2",
  components: {},
  props: {
    nome: String,
    origens: Array,
  },
  data() {
    return {
      searchData: null,
      selectedType: "CPF",
    };
  },
  methods: {
    emitValue() {
      this.$emit("emit-value-bar", [this.selectedType, this.searchData]);
      this.searchData = null;
    },
    consultaDescricao() {
      switch (this.selectedType) {
        case "CPF":
          return "Consulta por CPF";
        case "CORREIOS":
          return "Consulta por código do Correios";
        default:
          return "Consulta por " + this.selectedType;
      }
    },
  }
})
</script>

<style scoped>
.topbar {
  background: white;
  width: 100%;
  height: 75px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  border-bottom: 2px solid #FBDE96;
  /* border-bottom: 2px solid #a61212; */
}

.trackr-brand {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin-left: 40px;
  margin-right: 40px;
}

.nome-container {
  line-height: 100%;
  font-size: 26px;
  padding: 0 10px 0 10px;
}

.pesquisa-container {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  height: 60%;
}

select {
  height: 100%;
  min-width: 120px;
  background: #F3F5F9;
  border: 1px solid #BDBFC0;
  border-radius: 4px 0px 0px 4px;
  outline: none;
  box-sizing: border-box;
  padding: 0 5px 0 10px;

  background-image: url("data:image/svg+xml;utf8,<svg enable-background='new 0 0 551.13 551.13' height='15' width='15' viewBox='0 0 551.13 551.13' xmlns='http://www.w3.org/2000/svg'><path d='m275.565 361.679-223.897-223.896h-51.668l275.565 275.565 275.565-275.565h-51.668z'/></svg>");
  background-repeat: no-repeat;
  background-position-x: 90%;
  background-position-y: 52%;
  -webkit-appearance: none;
  -moz-appearance: none;

  font-size: 14px;
  line-height: 20px;
}

input {
  height: 100%;
  min-width: 600px;
  line-height: 100%;
  font-size: 19px;
  border: 1px solid #BDBFC0;
  border-left: none;
  border-right: none;
  color: black;
  padding: 0 10px 0 15px;
  margin-left: 0;
  outline: none;
  text-transform: uppercase;
}

.bt-pesquisa {
  background: #FAC84B;
  /* background: #961111; */
  border: none;
  height: 100%;
  border: 1px solid #BDBFC0;
  border-radius: 0px 4px 4px 0px;
  padding: 0 15px 0 15px;
  outline: none;
}

</style>
