<template>
    <div class="cookoutList">
        <div class="cookoutList"
        v-for="cookout in cookouts"
        v-bind:key="cookout.cookout_id" 
        v-on:click="viewCookoutDetails(cookout.cookoutId)" >
            <div class="cookoutListItem">
            <h2>{{ cookout.cookoutName }}</h2>
            <h2><em>{{cookout.cookoutRole}}</em></h2>
            <h3>{{ new Date(cookout.date) | dateFormat('MMMM D, YYYY', dateFormatConfig) }} at {{ cookout.time }}</h3>
            <p> My Notes: {{cookout.userNotes}} </p>
            </div>
    </div>
  </div>
</template>

<script>
import cookoutService from '../services/CookoutService';

export default {
    name: 'myCookoutsComponent',

    data() {
        return {
            cookouts: [],
            userCookout: {},
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
        cookoutService.getCookoutsByUserId(this.$store.state.user.id)
            .then(
                (response) => this.cookouts = response.data
            );
    },

    methods: {
        viewCookoutDetails(cookoutId) {
            this.$router.push(`/cookout/${cookoutId}`)
        },
    }

}

</script>

<style scoped>

.cookoutList {
    text-align: center;
    margin: 10px
}

.cookoutListItem {
    border: 2px solid;
    border-color: #352c20;
    border-radius: 25px;
    background: rgba(12, 11, 9, 0.8);
    padding-left: 5%;
    padding-right: 5%;
    width: 100%;
    padding-bottom: 5%;
}
</style>