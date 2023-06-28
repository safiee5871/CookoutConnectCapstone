<template>
<div class="main_view">

    <div class="main_view_item">
        <h1>My Cookouts</h1>
    </div>
    <div class="main_view_item">
      <my-cookouts-component />
    </div>

    <div class="main_view_item">
      <!-- **This does not have to be a button, we can always change**-->
      <router-link v-bind:to="{ name: 'createCookout' }">
        <a class="btn2">Create Cookout</a>
      </router-link>
    </div>
  </div>

</template>

<script>
import MyCookoutsComponent from '../components/MyCookoutsComponent.vue';
import profileService from '../services/ProfileUpdateService';

export default {
  components: { MyCookoutsComponent },
  
  name: "home",
    data() {
    return {
      user: {
        username: '',
        emailAddress: '',
        userNotes: '',
        id: ''
      }
    }
  },
  created() {
    profileService.myProfile()
    .then((response) => {
      this.user = response.data;
    });
  },
};

</script>

