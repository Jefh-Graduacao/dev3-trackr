<template>
  <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap shadow">
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
    <input
      class="form-control form-control-dark w-100"
      type="text"
      placeholder="Pesquisar por CPF"
      aria-label="CPF"
      v-on:keyup.enter="emitValue(searchData)"
      v-model="searchData"
    />
    <ul class="navbar-nav px-3">
      <li class="nav-item text-nowrap">
        <a class="nav-link" href="#">Sair</a>
      </li>
    </ul>
  </nav>
</template>

<script>
import Vue from "vue";
export default Vue.extend({
  name: "NavBar",
  components: {},
  props: {
    nome: String,
  },
  data() {
    return {
      searchData: null,
    };
  },
  methods: {
    emitValue(value) {
      this.$emit("emit-value-bar", value);
      this.searchData = null;
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
</style>
