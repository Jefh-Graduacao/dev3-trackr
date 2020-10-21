<template>
  <button :class="`btn btn-${tipo}`" @click="onClick">
    <slot />
  </button>
</template>

<script lang="ts">
import Vue, { PropType } from "vue";

export default Vue.extend({
  name: "Botao",
  components: {},
  props: {
    tipo: String,
    onClick: Function,
  },
});

export enum TipoBotao {
  Primario,
  Secundario,
}
</script>

<style scoped>
.btn {
  --btn-border-width: 1px;
  --btn-border-style: solid;
  --btn-border-color: var(--secondary-color);
  --btn-radius: 4px;
  position: relative;
  color: var(--btn-color, #000);
  background: var(--btn-bg, #fff);

  border: var(--btn-border-width) var(--btn-border-style)
    var(--btn-border-color);
  border-radius: 4px;
}

.btn-primario {
  --btn-color: white;
  --btn-bg: #f03a17;
  --btn-border-color: var(--btn-bg);
}

.btn-secundario {
  --btn-color: white;
  --btn-bg: rgb(52, 128, 68);
  --btn-border-color: var(--btn-bg);
}

.btn-terciario {
  --btn-color: white;
  --btn-bg: black;
  --btn-border-color: #ccc;
}
</style>
