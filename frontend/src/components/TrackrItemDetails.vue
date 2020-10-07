<template>
  <div id="main-track-item">
    <div class="ui-box ui-box--lite track-box">
      <div v-for="ite in movimentacoes" :key="ite" :item="ite">
        <div id="main-track-item-title" class="myml-ui-item-container__header">
          <div class="purchase-status__title-container">
            <h2 class="purchase-status__title purchase-status__title--black">
              {{ ite.titulo }}
            </h2>
            <span class="purchase-status__icon">
              <div class="purchase-status__icon-full">
                <!-- <svg
                  width="50px"
                  height="20px"
                  viewBox="0 0 56 18"
                  xmlns="http://www.w3.org/2000/svg"
                  xmlns:xlink="http://www.w3.org/1999/xlink"
                >
                  <image
                    href="../assets/caixa.png"
                    width="50px"
                    height="20px"
                  />
                </svg> -->
              </div>
            </span>
          </div>
          <p class="purchase-status__subtitle">
            {{ "Atividade registrada em " + ite.dataHora }}
          </p>
        </div>

        <div class="myml-ui-item-row u-myml-clearfix">
          <div class="myml-ui-dropdown-actions"></div>

          <div class="myml-ui-item u-media-object">
            <div
              class="myml-ui-item__icon u-media-object__fit"
              style="position: relative"
            >
              <a href="">
                <img
                  :src="getLogoTransportadora()"
                  alt="Transportadora Correios."
                  width="70"
                  height="70"
                />
              </a>
            </div>
            <div class="myml-ui-item__info u-media-object__fill">
              <a class="myml-ui-item__name" href=""> {{ ite.local }}</a>
              <div class="myml-ui-item__description">
                <p>
                  {{ ite.situacao }}
                </p>
              </div>
            </div>
          </div>
          <div class="myml-ui-user-info u-myml-float-left"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Vue from "vue";

export default Vue.extend({
  name: "TrackrItem",
  components: {},
  props: {
    movimentacoes: Array,
  },
  methods: {
    getImgUrl: function (name) {
      var images = require.context("../assets/transp", false, /\.png$/);
      return images("./" + name + ".png");
    },
    getLogoTransportadora: function () {
      switch ("Correios") {
        case "Correios":
          return this.getImgUrl("Trackr_LOGO");
        case "Cooperativa Rede Sul":
          return this.getImgUrl("CoopRedeSul");
        case "Jadlog":
          return this.getImgUrl("jadlog-logo");
        case "TNT":
          return this.getImgUrl("tnt");

        default:
          return this.getImgUrl("caixa");
      }
    },
  },
});
</script>

<style>
.ui-box,
.ui-panel {
  margin-bottom: 16px;
}
.ui-box--lite {
  border: 1px solid #eee;
}

.ui-badge,
.ui-box--lite {
  background-color: #fff;
}

.ui-box {
  margin-bottom: 15px;
  position: relative;
}
.track-box {
  font-family: Arial, Helvetica, "Nimbus Sans L", sans-serif;
  font-size: 13px;
  line-height: 20px;
  color: #333;
}
</style>
