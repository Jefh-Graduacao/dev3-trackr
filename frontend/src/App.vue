<template>
  <div id="main">
    <NavBar
      nome="Trackr"
      @emit-value-bar="getValueBar"
      :origins="typesCrawler"
    ></NavBar>
    <div class="container-fluid">
      <div class="row">
        <!-- <MenuBar></MenuBar> -->
        <h3 v-show="errorSearch">
          <br />
          <div class="center">{{ mensagemError }}</div>
        </h3>
        <Content
          v-if="searchValue != null && !details"
          :items="rastreios"
          :cpf="searchValue"
          @emit-value-id="emitClick"
          :registrar="botaoRegistrar"
          :rastreio="searchValue"
        ></Content>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
// import MenuBar from "./components/MenuBar.vue";
import NavBar from "./components/NavBar.vue";
import Content from "./components/Content.vue";
// Import stylesheet
import "vue-loading-overlay/dist/vue-loading.css";

export default Vue.extend({
  name: "App",
  components: { NavBar, Content },
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
      this.details = false;
      const loader = this.loader();
      if (obj[1] == "41") {
        this.errorSearch = false;
        this.rastreios = this.rastreiosTemp;
        this.searchValue = obj[1].toString();
      } else {
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
      }
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
      details: false,
      errorSearch: false,
      searchValue: "",
      botaoRegistrar: false,
      rastreios: new Array(),
      mensagemError: "Não há resultados para a consulta de:",
      typesCrawler: null,
      rastreiosTemp: [
        {
          cpf: "03820790004",
          movimentacoes: [
            {
              titulo: "MERCADORIA ENTREGUE",
              dataHora: "2020-09-30T19:45:00",
              local: "NOVO HAMBURGO / RS RED NHO",
              situacao:
                "ENTREGUE - INFORMADA ATRAVES DA CHAVE **********************0020010164858085480316 - MOBILE 5.",
            },
            {
              titulo: "SAIDA PARA ENTREGA",
              dataHora: "2020-09-30T02:09:00",
              local: "NOVO HAMBURGO / RS RED NHO",
              situacao: "Saida para entrega na cidade NOVO HAMBURGO.",
            },
            {
              titulo: "CHEGADA EM UNIDADE",
              dataHora: "2020-09-29T11:08:00",
              local: "NOVO HAMBURGO / RS RED NHO",
              situacao: "Chegada na unidade NOVO HAMBURGO em 29/09/20, 11:08h.",
            },
            {
              titulo: "SAIDA DE UNIDADE",
              dataHora: "2020-09-29T02:46:00",
              local: "GRAVATAI / RS RED POT",
              situacao:
                "Saida da unidade GRAVATAI em 29/09/20, 02:46h. Previsao de chegada na unidade NOVO HAMBURGO em 29/09/20, 03:45h.",
            },
            {
              titulo: "CHEGADA EM UNIDADE",
              dataHora: "2020-09-28T12:37:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Chegada na unidade GRAVATAI em 28/09/20, 12:37h.",
            },
            {
              titulo: "SAIDA DE UNIDADE",
              dataHora: "2020-09-26T02:41:00",
              local: "EXTREMA / MG RED EXT",
              situacao:
                "Saida da unidade EXTREMA em 26/09/20, 02:41h. Previsao de chegada na unidade GRAVATAI em 27/09/20, 00:41h.",
            },
            {
              titulo: "MERCADORIA RECEBIDA PARA TRANSPORTE",
              dataHora: "2020-09-25T12:28:00",
              local: "EXTREMA / MG RED EXT",
              situacao:
                "CT-e autorizado com 1 volume e 1 Kg. Destino: RS/NOVO HAMBURGO. Previsao de entrega: 01/10/20.",
            },
          ],
          codigo: "código",
        },
        {
          cpf: "03820790004",
          movimentacoes: [
            {
              titulo: "SAIDA DE UNIDADE",
              dataHora: "2020-09-30T16:02:00",
              local: "PALHOCA / SC RED FLP",
              situacao:
                "Saida da unidade PALHOCA em 30/09/20, 16:02h. Previsao de chegada na unidade GRAVATAI em 30/09/20, 23:02h.",
            },
            {
              titulo: "CHEGADA EM UNIDADE",
              dataHora: "2020-09-29T12:38:00",
              local: "PALHOCA / SC RED FLP",
              situacao: "Chegada na unidade PALHOCA em 29/09/20, 12:38h.",
            },
            {
              titulo: "SAIDA DE UNIDADE",
              dataHora: "2020-09-28T19:44:00",
              local: "EXTREMA / MG RED EXT",
              situacao:
                "Saida da unidade EXTREMA em 28/09/20, 19:44h. Previsao de chegada na unidade PALHOCA em 29/09/20, 09:44h.",
            },
            {
              titulo: "MERCADORIA RECEBIDA PARA TRANSPORTE",
              dataHora: "2020-09-28T12:33:00",
              local: "EXTREMA / MG RED EXT",
              situacao:
                "CT-e autorizado com 1 volume e 2 Kg. Destino: RS/NOVO HAMBURGO. Previsao de entrega: 02/10/20.",
            },
          ],
          codigo: "código",
        },
        {
          cpf: "03820790004",
          movimentacoes: [
            {
              titulo: "MERCADORIA ENTREGUE",
              dataHora: "2020-07-08T19:39:00",
              local: "NOVO HAMBURGO / RS RED NHO",
              situacao:
                "ENTREGUE - INFORMADA ATRAVES DA CHAVE **********************0010040485841060823176 - MOBILE 5.",
            },
            {
              titulo: "SAIDA PARA ENTREGA",
              dataHora: "2020-07-06T17:21:00",
              local: "NOVO HAMBURGO / RS RED NHO",
              situacao: "Saida para entrega na cidade NOVO HAMBURGO.",
            },
            {
              titulo: "CHEGADA EM UNIDADE",
              dataHora: "2020-07-06T02:54:00",
              local: "NOVO HAMBURGO / RS RED NHO",
              situacao: "Chegada na unidade NOVO HAMBURGO em 06/07/20, 02:54h.",
            },
            {
              titulo: "SAIDA DE UNIDADE",
              dataHora: "2020-07-06T01:34:00",
              local: "GRAVATAI / RS RED POT",
              situacao:
                "Saida da unidade GRAVATAI em 06/07/20, 01:34h. Previsao de chegada na unidade NOVO HAMBURGO em 06/07/20, 02:34h.",
            },
            {
              titulo: "CHEGADA EM UNIDADE",
              dataHora: "2020-07-04T14:55:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Chegada na unidade GRAVATAI em 04/07/20, 14:55h.",
            },
            {
              titulo: "SAIDA DE UNIDADE",
              dataHora: "2020-07-03T17:31:00",
              local: "JUNDIAI / SP RED SPO",
              situacao:
                "Saida da unidade JUNDIAI em 03/07/20, 17:31h. Previsao de chegada na unidade GRAVATAI em 04/07/20, 13:31h.",
            },
            {
              titulo: "MERCADORIA RECEBIDA PARA TRANSPORTE",
              dataHora: "2020-07-03T13:53:00",
              local: "JUNDIAI / SP RED SPO",
              situacao:
                "CT-e autorizado com 1 volume e 3 Kg. Destino: RS/NOVO HAMBURGO. Previsao de entrega: 09/07/20.",
            },
          ],
          codigo: "código",
        },
        {
          cpf: "03820790004",
          movimentacoes: [
            {
              titulo: "MERCADORIA ENTREGUE",
              dataHora: "2020-07-03T17:21:00",
              local: "PORTO ALEGRE / RS DLG PNH",
              situacao:
                "Documento do recebedor CPF: ***.***.900-04 Relacao com o destinatario: Proprio destinatario Comentario: (recebido via SSW Ocorrencia WebAPI).",
            },
            {
              titulo: "SAIDA PARA ENTREGA",
              dataHora: "2020-07-02T08:52:00",
              local: "PORTO ALEGRE / RS DLG PNH",
              situacao: "Saida para entrega na cidade NOVO HAMBURGO.",
            },
            {
              titulo: "CHEGADA EM UNIDADE",
              dataHora: "2020-07-01T21:51:00",
              local: "PORTO ALEGRE / RS DLG PNH",
              situacao: "Chegada na unidade PORTO ALEGRE em 01/07/20, 21:51h.",
            },
            {
              titulo: "SAIDA DE UNIDADE",
              dataHora: "2020-07-01T21:05:00",
              local: "PORTO ALEGRE / RS DLG POA",
              situacao:
                "Saida da unidade PORTO ALEGRE em 01/07/20, 21:05h. Previsao de chegada na unidade PORTO ALEGRE em 01/07/20, 23:00h.",
            },
            {
              titulo: "CHEGADA EM UNIDADE",
              dataHora: "2020-06-30T22:17:00",
              local: "PORTO ALEGRE / RS DLG POA",
              situacao: "Chegada na unidade PORTO ALEGRE em 30/06/20, 22:17h.",
            },
            {
              titulo: "SAIDA DE UNIDADE",
              dataHora: "2020-06-30T02:21:00",
              local: "JUNDIAI / SP DLG JND",
              situacao:
                "Saida da unidade JUNDIAI em 30/06/20, 02:21h. Previsao de chegada na unidade PORTO ALEGRE em 30/06/20, 22:21h.",
            },
            {
              titulo: "MERCADORIA RECEBIDA PARA TRANSPORTE",
              dataHora: "2020-06-27T18:10:00",
              local: "JUNDIAI / SP DLG JND",
              situacao:
                "CT-e autorizado com 1 volume e 1 Kg. Destino: RS/NOVO HAMBURGO. Previsao de entrega: 06/07/20.",
            },
          ],
          codigo: "código",
        },
      ],
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
  background: #f3f5f9;
}
</style>
