<template>

  <div id="profile">
    <div class="user-info">
      <h1 class="main_view_item">{{ this.user.username }}'s Profile</h1>      
      <h2 class="main_view_item">Current Username:  {{ this.user.username }} </h2>
      <h2 class="main_view_item" >Current email address:  {{ this.user.emailAddress }} </h2>
      <h2 class="main_view_item" >Current User Notes:  {{this.user.userNotes }}  </h2>
      <div class="main_view_item">
        <router-link v-bind:to="{name: 'updateProfile', params: {id: user.id} }">
      <button class="btn2" type="submit">Update Profile </button></router-link> 
      </div>
      </div>
  </div>
</template>

<script>
import profileService from '../services/ProfileUpdateService.js';
export default {
  name: 'profile',
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
}

</script>

<style scoped>
#profile h2 {
  text-transform: none;
  color: #eae8e8;
  font-family: "mencken-std", sans-serif;
}

</style>