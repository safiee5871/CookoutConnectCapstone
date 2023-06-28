<template>
  <div class="date-time">
    <h1>Date/Time</h1>
    <p>Select a date and time for your cookout.</p>
    <!-- Form -->
    <form class="date-form">
      <div class="date-input">
        <label for="date">Date</label>
        <input type="date" id="date" v-model="newDateTime.date" required />
      </div>
      <div class="time-input">
        <label for="time">Time</label>
        <input type="time" id="time" v-model="newDateTime.time" required />
      </div>
      <!-- Save Button -->
      <div class="date-time-submit">
        <label for="save-button"></label>
        <button class="btn5" type="button" v-on:click="updateDateTime">
          Save
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import CookoutService from "../services/CookoutService.js";

export default {
  name: "dateTime",
  data() {
    return {
      newDateTime: {
        date: "",
        time: "",
      },
    };
  },
  methods: {
    displayDateTime() {
      console.log(this.newDateTime);
    },
    updateDateTime() {
      //create updated cookout obj
      const updatedCookout = {
        cookoutId: this.$store.state.cookout.cookoutId,
        name: this.$store.state.cookout.cookoutName,
        // Passes date/time to cookout obj
        date: this.newDateTime.date,
        time: this.newDateTime.time,
        location: this.$store.state.cookout.location
      }

      console.log(updatedCookout);

      CookoutService.updateCookout(updatedCookout, updatedCookout.cookoutId)
        .then((response) => {
          if (response.status === 200) {
            // When the cookout is successfully updated, do....
            console.log();
            this.$store.commit("SET_COOKOUT", response.data);
          }
        })
        .catch((error) => {
          if (error.response) {
            window.alert("Bad Request");
          } else if (error.request) {
            window.alert("Could not find");
          }
        });
    },
  },
};
</script>

<style scoped>

.btn5 {
  font-family: "mencken-std", sans-serif;
  font-weight: 400;
  font-style: normal;
  color: #FF415B;
  margin: 30px;
  border: 2px solid #64AA71;
  border-radius: 50px;
  padding: 8px 25px;
  text-transform: uppercase;
  font-size: 13px;
  letter-spacing: 1px;
  transition: 0.3s;
}

.btn5:hover {
  color: #e37d43;
}

.time-input {
  margin-top:20px;
}

.date-form {
  background: rgba(8, 8, 8, 0.39);
  padding: 30px 5px 7px;
}

</style>