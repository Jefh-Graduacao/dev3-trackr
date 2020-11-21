<template>
  <div id="main">
    <NavBar2
      nome="Trackr"
      @emit-value-bar="getValueBar"
      :origens="typesCrawler"
    ></NavBar2>
    <div class="container-conteudo">
      <h3 v-show="errorSearch">
        <br />
        <div class="center">{{ mensagemError }}</div>
      </h3>
      <Content
        v-if="searchValue != null"
        :items="rastreios"
        :cpf="searchValue"
        @emit-value-id="emitClick"
        :registrar="botaoRegistrar"
        :rastreio="searchValue"
      ></Content>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import NavBar2 from "./components/NavBar2.vue";
import Content from "./components/Content.vue";
// Import stylesheet
import "vue-loading-overlay/dist/vue-loading.css";

export default Vue.extend({
  name: "App",
  components: { NavBar2, Content },
  mounted: function () {
    const vueSelf = this;
    const loader = vueSelf.loader();
    //Busca os tipos
    const url = `${process.env.VUE_APP_URL_BACKEND}/entregas/origens`;
    this.$http.get(url).then(
      function (response) {
        if (response.status == 200) {
          if (response.data) {
            var tempOrigens = response.data;
            tempOrigens.unshift("CPF");
            vueSelf.typesCrawler = tempOrigens;
          } else {
            console.log("Busca de origens vazia...");
          }
        }
        loader.hide();
      },
      function () {
        alert("Falha ao realizar busca de origens.");
        loader.hide();
      }
    );
  },
  methods: {
    //Configs para loader:
    //https://www.npmjs.com/package/vue-loading-overlay
    loader() {
      const loader = this.$loading.show({
        // Optional parameters
        //canCancel: true,
        onCancel: this.onCancel,
        color: "#a61212",
        loader: "dots",
      });
      return loader;
      // simulate AJAX
      // setTimeout(() => {
      //   loader.hide();
      // }, 5000);
    },
    onCancel() {
      console.log("User cancelled the loader.");
    },
    getValueBar(obj: String[]) {
      const loader = this.loader();
      const vueSelf = this;
      const url = this.getURL(obj[0], obj[1]);
      this.$http.get(url).then(
        function (response) {
          if (response.status == 200) {
            vueSelf.errorSearch = false;
            if (response.data) {
              if (obj[0] == "CPF" && response.data.length > 0) {
                vueSelf.rastreios = response.data;
              } else {
                vueSelf.rastreios = [response.data];
              }
            } else {
              vueSelf.errorSearch = true;
              vueSelf.rastreios = [];
              vueSelf.mensagemError =
                "Não há resultados para a consulta de " + vueSelf.searchValue;
            }
          }
          loader.hide();
        },
        function () {
          vueSelf.errorSearch = true;
          vueSelf.rastreios = [];
          vueSelf.mensagemError =
            "Informe um dado válido para realizar a Consulta";
          alert("Dado informado não é válido para esta consulta.");
          loader.hide();
        }
      );
      this.searchValue = obj[1].toString();
    },
    getURL(type: String, search: String) {
      switch (type) {
        case "CPF":
          //Por CPF
          this.botaoRegistrar = false;
          return `${process.env.VUE_APP_URL_BACKEND}/entregas/${search}`;
        default:
          //DEFAULTs
          this.botaoRegistrar = true;
          return `${process.env.VUE_APP_URL_BACKEND}/entregas/${type}/${search}`;
      }
    },
  },
  data: function () {
    return {
      errorSearch: false,
      searchValue: "",
      botaoRegistrar: false,
      rastreios: new Array(),
      mensagemError: "Não há resultados para a consulta de:",
      typesCrawler: null,
    };
  },
});
</script>

<style>
body {
  font-family: "Poppins";
  font-size: 13px;
  line-height: 20px;
  color: #333;
  background: #F3F5F9;
}

#main {
  width: 100%;
  min-height: 100%;
  position: relative;
}

.container-conteudo {
  width: 100%;
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}
</style>
