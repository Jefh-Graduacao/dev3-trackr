<template>
  <nav class="navbar sticky-top flex-md-nowrap shadow bg-nav">
    <a class="navbar-brand col-md-2 col-lg-1 mr-0 px-3" href="#">{{ nome }}</a>
    <span class="purchase-status__icon col-md-1 col-lg-1 icon-position">
      <div class="">
        <svg
          width="65px"
          height="65px"
          viewBox="0 0 70 70"
          xmlns="http://www.w3.org/2000/svg"
          xmlns:xlink="http://www.w3.org/1999/xlink"
        >
          <image href="../assets/Trackr_LOGO.png" width="65px" height="60px" />
        </svg>
      </div>
    </span>
    <button
      class="navbar-toggler position-absolute d-md-none collapsed"
      type="button"
      data-toggle="collapse"
      data-target="#sidebarMenu"
      aria-controls="sidebarMenu"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>
    <!-- <div class="input-group">
      <div class="input-group-prepend">
        <select class="form-control selectpicker">
          <option>Por CPF</option>
          <option>Por Correios</option>
        </select>
      </div>
      <input
        class="form-control form-control-dark"
        type="text"
        placeholder="Pesquisar por CPF"
        aria-label="CPF"
        v-on:keyup.enter="emitValue(searchData)"
        v-model="searchData"
      />
    </div> -->
    <!-- Underlined search bars -->
    <div class="input-group">
      <div class="input-group-prepend">
        <select class="custom-select my-select" v-model="tipoID">
          <option
            v-for="tti in tipoConsulta"
            v-bind:key="tti"
            :label="tti.descricao"
            :title="tti.descricao"
            :value="tti.id"
          >
            {{ tti.descricao }}
          </option>
        </select>
      </div>
      <input
        id="formSearch"
        type="text"
        :placeholder="consultaTipo().placeholder"
        aria-label="CPF"
        class="form-control form-control-underlined border-warning"
        v-on:keyup.enter="emitValue()"
        v-model="searchData"
      />
    </div>
    <!-- End -->
    <ul class="navbar-nav px-3">
      <li class="nav-item text-nowrap">
        <a class="nav-link" href="#">Sair</a>
      </li>
    </ul>
  </nav>
</template>

<script>
import Vue from "vue";
import { TipoBotao } from "./base/Botao.vue";
export default Vue.extend({
  name: "NavBar",
  components: {},
  props: {
    nome: String,
  },
  data() {
    return {
      searchData: null,
      tipoID: 0,
      tipoConsulta: [
        {
          id: 0,
          descricao: "CPF",
          placeholder: "Pesquisar por CPF",
        },
        {
          id: 1,
          descricao: "Correios",
          placeholder: "Pesquisar por Código do Correios",
        },
      ],
    };
  },
  methods: {
    emitValue() {
      this.$emit("emit-value-bar", [this.tipoID, this.searchData]);
      this.searchData = null;
    },
    consultaTipo() {
      return this.tipoConsulta[this.tipoID];
    },
  },
});
</script>

<style>
.purchase-status__icon {
  position: relative;
  height: 18px;
  width: 56px;
  display: -webkit-inline-box;
  display: -ms-inline-flexbox;
  display: inline-flex;
}

.icon-position {
  padding-bottom: 50px !important;
}
a {
  color: wheat;
}

.bg-nav {
  background-color: #a61212 !important;
}

.form-control:focus {
  box-shadow: none;
  color: yellow;
}

.form-control-underlined {
  border-width: 0;
  border-bottom-width: 1px;
  border-radius: 0;
  padding-left: 25;
  background-color: #a61212 !important;
}

label:not(.input-group-text) {
  margin-top: 10px;
}

.my-select {
  background-color: #a61212;
  color: wheat;
  border: 0 none;
  border-radius: 20px;
  padding: 6px 20px;
}

.form-control::-webkit-input-placeholder {
  color: #bec6cc;
}
.form-control:-moz-placeholder {
  color: #bec6cc;
}
.form-control::-moz-placeholder {
  color: #bec6cc;
}
.form-control::placeholder {
  color: #bec6cc;
}
.form-control:-ms-input-placeholder {
  color: #bec6cc;
}
</style>
