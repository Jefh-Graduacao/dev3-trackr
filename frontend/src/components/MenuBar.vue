<template>
  <nav
    id="sidebarMenu"
    class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
  >
    <div class="sidebar-sticky pt-3">
      <ul class="nav flex-column">
        <li class="nav-item">
          <a class="nav-link active" href="#">
            <home-icon size="1.0x"></home-icon>Dashboard
            <span class="sr-only">(current)</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <file-icon size="1.0x"></file-icon>Consultar
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <shopping-cart-icon size="1.0x"></shopping-cart-icon>Cadastrar
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <users-icon size="1.0x"></users-icon>Usuário
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <bar-chart-2-icon size="1.0x"></bar-chart-2-icon>Relatórios
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">
            <layers-icon size="1.0x"></layers-icon>Integração
          </a>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import Vue from "vue";
import {
  PlusCircleIcon,
  FileIcon,
  ShoppingCartIcon,
  UsersIcon,
  BarChart2Icon,
  LayersIcon,
  HomeIcon,
  FileTextIcon,
} from "vue-feather-icons";

export default Vue.extend({
  name: "MenuBar",
  components: {
    FileIcon,
    ShoppingCartIcon,
    UsersIcon,
    BarChart2Icon,
    LayersIcon,
    HomeIcon,
  },
});
</script>

<style>
</style>
