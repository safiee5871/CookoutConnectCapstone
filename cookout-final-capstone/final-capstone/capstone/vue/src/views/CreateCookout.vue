<template>
  <div class="main_view">
    <div class="main_view_item">
      <!-- HEADER -->
      <div id="header">
        <h4>Create a Cookout</h4>
      </div>
    </div>
    <!-- Name -->
    <form class="cookout-name">
      <label for="name">Select a name for your cookout</label>
      <input type="text" v-model="cookoutName" />
      <!-- This button will create a new cookout in DB, intially stores CookoutId and UserId (user_cookout and cookout)-->
      <!-- Cookout items will display once the name and cookout has been created -->
      <button class="btn2" type="button" v-on:click="createCookout">
        Save
      </button>
    </form>
    <div class="cookout-items" v-show="displayCreateCookout">
      <!-- TIME / DATE (popup router-link) -->
      <div class="main_view_item">
        <a class="btn3" v-on:click="displayDateForm = true" v-if="!displayDateForm"> Add Date-Time </a>
        <date-time-component v-bind:cookoutId="cookout.cookoutId" v-show="displayDateForm"/>
      </div>
      <!-- LOCATION (popup router-link) -->
      <div class="main_view_item">
        <a class="btn3" v-on:click="displayLocationForm = true" v-if="!displayLocationForm">
          Add Location
        </a>
        <location-component v-show="displayLocationForm" />
      </div>
      <!-- ADD ATTENDEES (popup router-link) -->
      <div class="main_view_item">
        <a class="btn3" v-on:click="displayAttendeesForm = true" v-if="!displayAttendeesForm">
          Add Attendees
        </a>
        <add-attendees-component v-show="displayAttendeesForm" />
      </div>
      <div class="main_view_item">
        <!-- CREATE MENU (popup router-link) -->
        <a class="btn2" v-on:click="displayMenuForm = true" v-if="!displayMenuForm"> Create Menu </a>
        <create-menu-component v-show="displayMenuForm" />
      </div>
    </div>
  </div>
</template>

<script>
import AddAttendeesComponent from "../components/AddAttendeesComponent.vue";
import DateTimeComponent from "../components/DateTimeComponent.vue";
import CreateMenuComponent from "../components/CreateMenuComponent.vue";
import LocationComponent from "../components/LocationComponent.vue";
import CookoutService from "../services/CookoutService.js";

export default {
  components: {
    DateTimeComponent,
    AddAttendeesComponent,
    CreateMenuComponent,
    LocationComponent,
  },
  name: "createCookout",
  data() {
    return {
      cookout: {
        cookoutId: '',
        cookoutName: '',
        date: '',
        time: '',
        location: ''
      },
      userCookout: {},
      displayNameForm: true,
      displayCreateCookout: false,
      displayDateForm: false,
      displayAttendeesForm: false,
      displayMenuForm: false,
      displayLocationForm: false,
      cookoutName: "",
    };
  },
  methods: {
    createCookout() {
      // Show other cookout details
      this.displayCreateCookout = true;

      //New cookout object
      const newCookout = {
        cookoutName: this.cookoutName,
      };

      //add cookout
      CookoutService.addCookout(newCookout)
        .then((response) => {
          if (response.status === 200) {
            //    Set current cookout ID to this id
            this.cookout = response.data;
            this.$store.commit("SET_COOKOUT", this.cookout);

            console.log(this.cookout);

             //New userCookout object
            this.userCookout = {
              //  userId error here
              userId: this.$store.state.user.id,
              // this.$store.user needs to display the user id
              cookoutId: this.cookout.cookoutId,
              cookoutRole: "Host",
              rsvp: true,
            };
            console.log(this.userCookout);
            
            // add into userCookouts
            CookoutService.addUserCookout(this.userCookout)
              .then((response) => {
                if (response === 200) {
                  console.log(response.data);
                }
              })
              .catch((error) => {
                if (error.response) {
                  window.alert("Bad Request");
                } else if (error.request) {
                  window.alert("Could not reach service");
                }
              });
          }
        })
        .catch((error) => {
          if (error.response) {
            window.alert("Bad Request");
          } else if (error.request) {
            window.alert("Could not reach service");
          }
        });
    },
  },
};
</script>

<style scoped>

.main_view {
  text-align: center;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;  
  color: #eae8e8;
  align-items: center;
}

.cookout-name {
  text-align: center;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;  
  color: #eae8e8;
  font-size: 20px;
  background: rgba(8, 8, 8, 0.39);
  padding-top: 30px;
  padding: 30px;
  /* width: 40%; */
}

input[type="text"]
{
    font-size:15px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
    padding: 5px 5px 5px 5px;
}

.btn3 {
  font-family: "mencken-std", sans-serif;
  font-weight: 400;
  font-style: bold;
  margin: 0 0 0 15px;
  border: 2px solid #96c5ea;
  color: #fff;
  border-radius: 60px;
  padding: 15px 20px;
  text-transform: uppercase;
  font-size: 15px;
  letter-spacing: 1px;
  transition: 0.3s;
}

#main h4 {
  font-family: 'Bebas Neue', sans-serif;
  font-weight: 400;
  font-style: bold;
  margin-bottom: 30px;
  font-size: 75px;
  line-height: 56px;
  color: #eae8e8;
  font-display:auto;
  font-stretch:normal;
  text-shadow: -1px 0  #0f0f0e, 0 1px  #235c0c, 1px 0  #204213, 0 -1px  #1e3d12;
  text-align: center;
}

</style>