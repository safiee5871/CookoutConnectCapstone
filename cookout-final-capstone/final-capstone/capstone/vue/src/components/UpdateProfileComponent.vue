<template>

  <div id="profile">

        <h1 class="main_view_item">Profile for {{ this.user.username }}</h1>
      
      <div class="form">
        <h2 class="main_view_item">Current Username:  {{ this.user.username }}</h2>
        <div class="main_view_item">
          <input class="form-input-group" type="text" id="username" v-model="updatedUser.username" placeholder="New Username"/>
        </div>
      
        <h2 class="main_view_item" v-on:click="needToEditEmail = true">Current email address:  {{ this.user.emailAddress }}</h2>
        <div class="main_view_item">
          <input class="form-input-group" type="text" id="emailAddress" v-model="updatedUser.emailAddress" placeholder="New Email Address"/>
        </div>

        <h2 class="main_view_item" >Current User Notes:  {{ this.user.userNotes }} </h2>
        <div class="main_view_item">
          <input class="form-input-group" type="text" id="userNotes" v-model="updatedUser.userNotes" placeholder="Updated Notes" />
        </div>
      </div>
      <div class="main_view_item">
      <button class="btn2" type="submit" v-on:click.prevent="update" >Save Updates</button>
      </div>
  </div>
</template>

<script>

import profileService from '../services/ProfileUpdateService';

export default {
  name: 'updateProfile',
  data() {
    return {
      updatedUser: {
        username: '',
        emailAddress: '',
        userNotes: '',
        id: ''
      },
      user: {
      }
    };
  },
  
  created() {
    this.getCurrentUser();
  },

  methods: {
      getCurrentUser(){
        profileService
        .myProfile()
        .then((response) => {
          this.user = response.data
        });
     
      },

      update() {
        if(this.updatedUser.username === "") {
          this.updatedUser.username = this.user.username;
        }
        if(this.updatedUser.emailAddress === "") {
          this.updatedUser.emailAddress = this.user.emailAddress;
        }
        if(this.updatedUser.userNotes === "") {
          this.updatedUser.userNotes = this.user.userNotes;
        }

        profileService
            .update(this.updatedUser, this.user.id)
            .then((response) => {
            if (response.status == 200) {
              this.$router.push({
                path: '/',
                query: { update: 'success' },
              });
            }
          })
      }
  }
}

</script>

<style scoped>

.main_view_item {
  margin: -10px;
  width: 100%;
  display:flex;
  justify-content: center;
}


.form-input-group {
  font-size: 150%;
  width: 500px;
  display: flex;
  justify-content: center;
  
}

#profile h2 {
    text-transform: none;
}
</style>