<template>
  <div>
    <h1>TOP PHIM HAY 2021</h1>
    <FilmList :films="films" />
  </div>
</template>

<script>
import FilmList from "./components/FilmList.vue";
import axios from "axios";
export default {
  components: {
    FilmList,
  },
  data() {
    return {
      films: [],
      loading: false,
      error: null,
    };
  },
  methods: {
    async fetchFilms() {
      try {
        this.error = null;
        this.loading = true;
        const url = `http://localhost:8083/api/films`;
        const response = await axios.get(url);
        this.films = response.data;
      } catch (err) {
        console.log(err);
      }
      this.loading = false;
    },
  },
  mounted() {
    this.fetchFilms();
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
h1 {
  color: green;
}
</style>
