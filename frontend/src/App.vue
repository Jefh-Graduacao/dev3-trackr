<template>
  <div id="main">
    <NavBar nome="Trackr" @emit-value-bar="getValueBar"></NavBar>
    <div class="container-fluid">
      <div class="row">
        <MenuBar></MenuBar>
        <h3 v-show="errorSearch">
          <br />
          {{ mensagemError }} {{ searchValue }}
        </h3>
        <Content
          v-if="searchValue != null && !details"
          :items="rastreios"
          :cpf="searchValue"
          @emit-value-id="emitClick"
        ></Content>
        <ContentDetails
          v-if="details"
          :item="rastreios[0]"
          :cpf="searchValue"
        ></ContentDetails>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
import MenuBar from "./components/MenuBar.vue";
import NavBar from "./components/NavBar.vue";
import Content from "./components/Content.vue";
import ContentDetails from "./components/ContentDetails.vue";

export default Vue.extend({
  name: "App",
  components: { MenuBar, NavBar, Content, ContentDetails },
  methods: {
    emitClick(id: number) {
      this.details = true;
    },
    getValueBar(value: number) {
      this.details = false;
      if (value == 41) {
        this.errorSearch = false;
        this.rastreios = this.rastreiosTemp;
      } else {
        var vueSelf = this;
        // var url = "http://localhost:8080/entregas/04000432036";
        var url = "http://localhost:8080/entregas/" + value;
        this.$http.get(url).then(
          function (response) {
            if (response.status == 200) {
              vueSelf.errorSearch = false;
              if (response.data && response.data.length > 0) {
                vueSelf.rastreios = response.data;
              } else {
                vueSelf.errorSearch = true;
                vueSelf.rastreios = [];
                vueSelf.mensagemError =
                  "Não há resultados para a consulta de " + vueSelf.errorSearch;
              }
            }
          },
          function (response) {
            vueSelf.errorSearch = true;
            vueSelf.rastreios = [];
            vueSelf.mensagemError =
              "Informe um CPF válido para realizar a Consulta";
          }
        );
      }
      this.searchValue = value;
    },
  },
  data: function () {
    return {
      details: false,
      errorSearch: false,
      searchValue: 0,
      rastreios: new Array(),
      mensagemError: "Não há resultados para a consulta de:",
      rastreiosTemp: [
        {
          cpf: "04000432036",
          movimentacoes: [
            {
              dataHora: "2020-09-16T01:39:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Redespacho autorizado com 1 volume e 0 Kg. Destino: RS/CACHOEIRINHA. Previsao de entrega: 18/09/20.",
            },
            {
              dataHora: "2020-09-16T04:27:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Saida da unidade CONTAGEM em 16/09/20, 04:27h. Previsao de chegada na unidade JUNDIAI em 16/09/20, 14:26h.",
            },
            {
              dataHora: "2020-09-16T14:20:00",
              local: "JUNDIAI / SP RED SPO",
              situacao: "Chegada na unidade JUNDIAI em 16/09/20, 14:20h.",
            },
            {
              dataHora: "2020-09-17T01:41:00",
              local: "JUNDIAI / SP RED SPO",
              situacao:
                "Saida da unidade JUNDIAI em 17/09/20, 01:41h. Previsao de chegada na unidade GRAVATAI em 17/09/20, 21:39h.",
            },
            {
              dataHora: "2020-09-17T22:24:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Chegada na unidade GRAVATAI em 17/09/20, 22:24h.",
            },
            {
              dataHora: "2020-09-18T09:42:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Saida para entrega na cidade CACHOEIRINHA.",
            },
            {
              dataHora: "2020-09-18T13:18:00",
              local: "GRAVATAI / RS RED POT",
              situacao:
                "ENTREGUE EM 18/09/20 13:18H RECEBIDO POR DENIS RENATO, PORTEIRO DO DESTINATARIO - INFORMADA ATRAVES DA CHAVE **********************0020009606991082023928 - MOBILE 5 DOC N.: *****70264.",
            },
          ],
          codigo: "código",
        },
        {
          cpf: "04000432036",
          movimentacoes: [
            {
              dataHora: "2020-09-16T01:39:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Redespacho autorizado com 1 volume e 0 Kg. Destino: RS/CACHOEIRINHA. Previsao de entrega: 18/09/20.",
            },
            {
              dataHora: "2020-09-16T04:27:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Saida da unidade CONTAGEM em 16/09/20, 04:27h. Previsao de chegada na unidade JUNDIAI em 16/09/20, 14:26h.",
            },
            {
              dataHora: "2020-09-16T14:20:00",
              local: "JUNDIAI / SP RED SPO",
              situacao: "Chegada na unidade JUNDIAI em 16/09/20, 14:20h.",
            },
            {
              dataHora: "2020-09-17T01:41:00",
              local: "JUNDIAI / SP RED SPO",
              situacao:
                "Saida da unidade JUNDIAI em 17/09/20, 01:41h. Previsao de chegada na unidade GRAVATAI em 17/09/20, 21:39h.",
            },
            {
              dataHora: "2020-09-17T22:24:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Chegada na unidade GRAVATAI em 17/09/20, 22:24h.",
            },
            {
              dataHora: "2020-09-18T09:42:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Saida para entrega na cidade CACHOEIRINHA.",
            },
            {
              dataHora: "2020-09-18T13:18:00",
              local: "GRAVATAI / RS RED POT",
              situacao:
                "ENTREGUE EM 18/09/20 13:18H RECEBIDO POR DENIS RENATO, PORTEIRO DO DESTINATARIO - INFORMADA ATRAVES DA CHAVE **********************0020009606991082023928 - MOBILE 5 DOC N.: *****70264.",
            },
          ],
          codigo: "código",
        },
        {
          cpf: "04000432036",
          movimentacoes: [
            {
              dataHora: "2020-09-16T01:39:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Redespacho autorizado com 1 volume e 0 Kg. Destino: RS/CACHOEIRINHA. Previsao de entrega: 18/09/20.",
            },
            {
              dataHora: "2020-09-16T04:27:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Saida da unidade CONTAGEM em 16/09/20, 04:27h. Previsao de chegada na unidade JUNDIAI em 16/09/20, 14:26h.",
            },
            {
              dataHora: "2020-09-16T14:20:00",
              local: "JUNDIAI / SP RED SPO",
              situacao: "Chegada na unidade JUNDIAI em 16/09/20, 14:20h.",
            },
            {
              dataHora: "2020-09-17T01:41:00",
              local: "JUNDIAI / SP RED SPO",
              situacao:
                "Saida da unidade JUNDIAI em 17/09/20, 01:41h. Previsao de chegada na unidade GRAVATAI em 17/09/20, 21:39h.",
            },
            {
              dataHora: "2020-09-17T22:24:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Chegada na unidade GRAVATAI em 17/09/20, 22:24h.",
            },
            {
              dataHora: "2020-09-18T09:42:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Saida para entrega na cidade CACHOEIRINHA.",
            },
            {
              dataHora: "2020-09-18T13:18:00",
              local: "GRAVATAI / RS RED POT",
              situacao:
                "ENTREGUE EM 18/09/20 13:18H RECEBIDO POR DENIS RENATO, PORTEIRO DO DESTINATARIO - INFORMADA ATRAVES DA CHAVE **********************0020009606991082023928 - MOBILE 5 DOC N.: *****70264.",
            },
          ],
          codigo: "código",
        },
        {
          cpf: "04000432036",
          movimentacoes: [
            {
              dataHora: "2020-09-16T01:39:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Redespacho autorizado com 1 volume e 0 Kg. Destino: RS/CACHOEIRINHA. Previsao de entrega: 18/09/20.",
            },
            {
              dataHora: "2020-09-16T04:27:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Saida da unidade CONTAGEM em 16/09/20, 04:27h. Previsao de chegada na unidade JUNDIAI em 16/09/20, 14:26h.",
            },
            {
              dataHora: "2020-09-16T14:20:00",
              local: "JUNDIAI / SP RED SPO",
              situacao: "Chegada na unidade JUNDIAI em 16/09/20, 14:20h.",
            },
            {
              dataHora: "2020-09-17T01:41:00",
              local: "JUNDIAI / SP RED SPO",
              situacao:
                "Saida da unidade JUNDIAI em 17/09/20, 01:41h. Previsao de chegada na unidade GRAVATAI em 17/09/20, 21:39h.",
            },
            {
              dataHora: "2020-09-17T22:24:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Chegada na unidade GRAVATAI em 17/09/20, 22:24h.",
            },
            {
              dataHora: "2020-09-18T09:42:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Saida para entrega na cidade CACHOEIRINHA.",
            },
            {
              dataHora: "2020-09-18T13:18:00",
              local: "GRAVATAI / RS RED POT",
              situacao:
                "ENTREGUE EM 18/09/20 13:18H RECEBIDO POR DENIS RENATO, PORTEIRO DO DESTINATARIO - INFORMADA ATRAVES DA CHAVE **********************0020009606991082023928 - MOBILE 5 DOC N.: *****70264.",
            },
          ],
          codigo: "código",
        },
        {
          cpf: "04000432036",
          movimentacoes: [
            {
              dataHora: "2020-09-16T01:39:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Redespacho autorizado com 1 volume e 0 Kg. Destino: RS/CACHOEIRINHA. Previsao de entrega: 18/09/20.",
            },
            {
              dataHora: "2020-09-16T04:27:00",
              local: "CONTAGEM / MG RED CON",
              situacao:
                "Saida da unidade CONTAGEM em 16/09/20, 04:27h. Previsao de chegada na unidade JUNDIAI em 16/09/20, 14:26h.",
            },
            {
              dataHora: "2020-09-16T14:20:00",
              local: "JUNDIAI / SP RED SPO",
              situacao: "Chegada na unidade JUNDIAI em 16/09/20, 14:20h.",
            },
            {
              dataHora: "2020-09-17T01:41:00",
              local: "JUNDIAI / SP RED SPO",
              situacao:
                "Saida da unidade JUNDIAI em 17/09/20, 01:41h. Previsao de chegada na unidade GRAVATAI em 17/09/20, 21:39h.",
            },
            {
              dataHora: "2020-09-17T22:24:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Chegada na unidade GRAVATAI em 17/09/20, 22:24h.",
            },
            {
              dataHora: "2020-09-18T09:42:00",
              local: "GRAVATAI / RS RED POT",
              situacao: "Saida para entrega na cidade CACHOEIRINHA.",
            },
            {
              dataHora: "2020-09-18T13:18:00",
              local: "GRAVATAI / RS RED POT",
              situacao:
                "ENTREGUE EM 18/09/20 13:18H RECEBIDO POR DENIS RENATO, PORTEIRO DO DESTINATARIO - INFORMADA ATRAVES DA CHAVE **********************0020009606991082023928 - MOBILE 5 DOC N.: *****70264.",
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
  font-family: Arial, Helvetica, "Nimbus Sans L", sans-serif;
  font-size: 13px;
  line-height: 20px;
  color: #333;
}
</style>
