<template>
<div class="cookout_details_card">
  <div class="cookoutDetails">
        <h2>{{ cookout.cookoutName }}</h2>
        <h3>{{ new Date(cookout.date) | dateFormat('MMM D, YYYY', dateFormatConfig) }} at {{ cookout.time }}</h3>
        <p> {{cookout.location}} </p>
        <button class="btn2" type="button" v-on:click="update(); hasRSVP = true" v-if="!hasRSVP">RSVP?</button>
        <button class="btn2" type="button" v-on:click="update(); hasRSVP = false" v-else>Can't Attend?</button>
        <router-link :to="{ name: 'menuComponent', params: {cookoutId: cookout.cookoutId }}"><h2>See the Menu</h2></router-link>
        <h2 class="list_title">Guest List</h2>
        <guest-list-component :cookoutID=cookout.cookoutId />
    
  </div>
</div>

</template>

<script>

import cookoutService from '../services/CookoutService'
import GuestListComponent from './GuestListComponent.vue';

export default {
  components: { GuestListComponent },
    name: 'cookoutDetailsComponent',
    data() {
        return {
            hasRSVP: false,
            userCookout: {
                userId:'',
                cookoutId:'',
                cookoutRole:'',
                username:'',
                rsvp:''
            },
            cookout: {
                cookoutId: '',
                cookoutName: '',
                cookoutRole: '',
                date: '',
                time: '',
                location: ''
            },
 
        }
    },
    created() {
        cookoutService.getCookoutById(this.$route.params.cookoutID)
            .then((response) => (this.cookout = response.data));

        cookoutService
            .getUserCookoutById(this.$route.params.cookoutID, this.$store.state.user.id)
            .then((response) => {
                this.userCookout = response.data;
                if(this.userCookout.rsvp == true) {
                this.hasRSVP = true;
                } else {
                    this.hasRSVP = false;
                }
            });
    },

    methods: {
        update() {
        cookoutService
            .updateUserCookoutRSVP(this.userCookout, this.$route.params.cookoutID, this.$store.state.user.id)
            .then((response) => {
                this.userCookout = response.data;
                this.$router.push({
                name: "cookoutDetailsComponent",
                params: {cookoutID: this.$route.params.cookoutID},
              });
            });
        },

    }

}
    
</script>

<style scoped>
.cookout_details_card {
    display: flex;
    margin-top: 30px;
    align-items: center;
    text-align: center;
    
}

.cookoutDetails {
    border: 2px solid;
    border-color: #352c20;
    border-radius: 25px;
    background: rgba(12, 11, 9, 0.8);
    padding-left: 5%;
    padding-right: 5%;
    width: 50%;
}

.list_title{
    text-decoration: #bc7f37 underline;
}
</style>