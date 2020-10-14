<template>
  <div class="tracking-title">
    <h5>
      {{ title }}
    </h5>
    <p>
      {{ subtitle }}
    </p>
  </div>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  name: "TrackingHeading",
  props: {
    title: String,
    subtitle: String
  }
});
</script>

<style scoped>
.tracking-title {
  display: flex;
  flex-direction: column;
}

.tracking-title h5, p {
  margin: auto;
}
</style>
