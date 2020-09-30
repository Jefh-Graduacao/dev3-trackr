<template>
  <div id="main">
    <NavBar nome="Trackr" @emit-value-bar="getValueBar"></NavBar>
    <div class="container-fluid">
      <div class="row">
        <MenuBar></MenuBar>
        <Content v-if="searchValue != null" :items="rastreios"></Content>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import MenuBar from "./components/MenuBar.vue";
import NavBar from "./components/NavBar.vue";
import Content from "./components/Content.vue";

export default Vue.extend({
  name: "App",
  components: { MenuBar, NavBar, Content },
  methods: {
    getValueBar(value: null) {
      this.searchValue = value;
    },
  },
  data: function () {
    return {
      searchValue: null,
      rastreios: [
        {
          codigo: "OP805455756BR",
          inicio: "20/09/2020",
          transportadora: {
            nome: "Correios",
          },
          status: {
            mensagem: "Em trasferência entre unidades.",
            dataHora: "26/09/2020 ás 15:40",
          },
        },
        {
          codigo: "45454DD",
          inicio: "20/09/2020",
          transportadora: {
            nome: "Expresso São Miguel",
          },
          status: {
            mensagem: "Encomenda em rota de entrega.",
            dataHora: "28/08/2020 ás 02:40",
          },
        },
        {
          codigo: "OP8789784564BR",
          inicio: "15/08/2020",
          transportadora: {
            nome: "Jadlog",
          },
          status: {
            mensagem: "Entregue ao destinatário.",
            dataHora: "24/08/2020 ás 12:40",
          },
        },
        {
          codigo: "457855121EX",
          inicio: "10/09/2020",
          transportadora: {
            nome: "TNT",
          },
          status: {
            mensagem: "Entregue ao destinatário.",
            dataHora: "21/09/2020 ás 10:40",
          },
        },
      ],
    };
  },
});
</script>

<style>
body {
  font-family: Arial, Helvetica, "Nimbus Sans L", sans-serif;
  font-size: 13px;
  line-height: 20px;
  color: #333;
}
</style>
