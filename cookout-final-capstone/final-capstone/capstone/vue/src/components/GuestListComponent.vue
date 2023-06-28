<template>
<div>
  <div class="guestList"
        v-for="guest in guestList" 
        v-bind:key="guest.user_id">
            <div class="guestListItem">
            <h3> {{ guest.username }}&nbsp;&nbsp;&nbsp;<span class="details">{{guest.cookoutRole}}&nbsp;&nbsp;&nbsp;RSVP? {{  getRSVP(guest.rsvp) }}</span></h3>
        </div>
        </div>
    </div>
</template>

<script>
import cookoutService from '../services/CookoutService'
import profileService from '../services/ProfileUpdateService.js';
export default {
    name:'guestListComponent',
    data(){
        return{
            // notHost: true,
            currentUser: {
                username: '',
                emailAddress: '',
                userNotes: '',
                id: ''
      },
            guestList: {
                userId:'',
                cookoutId:'',
                cookoutRole:'',
                username: '',
                rsvp:''
            }
        }
    },
     created() {
        cookoutService.getGuestListById(this.$route.params.cookoutID)
        .then((response) => (this.guestList = response.data));

        profileService.myProfile()
        .then((response) => {
        this.currentUser = response.data;
        });
  },
    
    methods: {
        getRSVP(rsvp) {
            if(rsvp) {
                return "Yes"
            } else {
                return "No"
            }
        },

        notHost(user_id) {
            if(user_id === this.currentUser.user.id) {
                return true;
            } else {
                return false;
            }
        }

        
    }

}

</script>


<style scoped>
.details {
    color: #eae8e8;
    text-transform: none;
    font-size: 60%;
    font-style: normal;
}

</style>