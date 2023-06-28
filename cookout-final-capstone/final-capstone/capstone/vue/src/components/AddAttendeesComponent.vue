<template>
  <div>
    <h1>Add Attendees</h1>
    <!-- Form -->
    <form class="add-attendees" id="add-attendees">
      <div class="role-list-container">
        <!-- Add an on-click so that role is updated when user clicks on a role for the attendee -->
        <input type="radio" id="host" name="role" value="host" v-model="newAttendee.role"/>
        <label for="host">     Host</label><br />
        <input type="radio" id="guest" name="role" value="guest" v-model="newAttendee.role"/>
        <label for="guest">     Guest</label><br />
        <input type="radio" id="chef" name="role" value="chef" v-model="newAttendee.role"/>
        <label for="chef">     Chef</label>
      </div>
      <!-- Name/Email Inputs -->
      <div class="attendee-info-container">
        <div class="attendee-info-item">
          <label for="name-input">Name:</label>
          <input
            type="text"
            id="name-input"
            v-model="newAttendee.name"
            required
          />
        </div>
        <div class="attendee-info-item">
          <label for="email-input">Email:</label>
          <input
            type="text"
            id="email-input"
            v-model="newAttendee.email"
            required
          />
        </div>
        <!-- Create Button -->
        <div class="attendee-info-item">
          <label for="attendee-btn"></label>
          <button class="btn2" type="button" v-on:click.prevent="addAttendees">
            Save
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import ProfileUpdateService from "../services/ProfileUpdateService";
import AuthService from "../services/AuthService";
import CookoutService from "../services/CookoutService";

export default {
  name: "addAttendees",
  data() {
    return {
      newAttendee: {
        role: "",
        name: "",
        email: "",
      },
    };
  },
  methods: {
    addAttendees() {
      // new user cookout obj
      const newUserCookout = {
        userId: "",
        cookoutId: this.$store.state.cookout.cookoutId,
        cookoutRole: this.newAttendee.role,
        rsvp: false,
      };

      // Pass info to newAttendee obj
      // outer request to check if user exists

      ProfileUpdateService.getUserByEmail(this.newAttendee.email)
        .then((response) => {
          console.log(response.status);
          if (response.status === 200 && response.data != "") {
            // set user id
            newUserCookout.userId = response.data.id;
            console.log(response.data);
            console.log(newUserCookout);

            //create new user_cookout
            console.log(newUserCookout);
            CookoutService.addUserCookout(newUserCookout)
              .then((response) => {
                if (response.status === 200) {
                  console.log(response.data);
                }
              })
              .catch((error) => {
                if (error.response) {
                  window.alert("User already added.");
                } else if (error.request) {
                  window.alert("Could not reach service");
                }
              });
          } else if (response.data === "") {
            // create new user obj
            const newUser = {
              username: this.newAttendee.name,
              password: "password",
              confirmPassword: "password",
              role: "user",
              emailAddress: this.newAttendee.email,
            };

            console.log(newUser);
            //inner request to create user
            AuthService.register(newUser)
              .then((response) => {
                if (response.status == 201) {
                  // set the user id equal to the new user

                  console.log("User Created");

                  ProfileUpdateService.getUserByEmail(this.newAttendee.email)
                    .then((response) => {
                      console.log(response.status);
                      if (response.status === 200 && response.data != "") {
                        newUserCookout.userId = response.data.id;

                        //create new user_cookout
                        console.log(newUserCookout);
                        CookoutService.addUserCookout(newUserCookout)
                          .then((response) => {
                            if (response.status === 200) {
                              console.log(response.data);
                            }
                          })
                          .catch((error) => {
                            if (error.response) {
                              window.alert("User already added.");
                            } else if (error.request) {
                              window.alert("Could not reach service");
                            }
                          });
                      }
                    })
                    .catch((error) => {
                      if (error.response) {
                        window.alert("Error Request");
                      } else if (error.request) {
                        window.alert("Error");
                      }
                    });
                }
              })
              .catch((error) => {
                const response = error.response;
                this.registrationErrors = true;
                if (response.status === 400) {
                  this.registrationErrorMsg = "Bad Request: Validation Errors";
                }
              });
          }
        })
        .catch((error) => {
          if (error.response) {
            window.alert("Error Request");
          } else if (error.request) {
            window.alert("Error");
          }
        });
    },
  },
};
</script>

<style scoped>


input [type="radio"]:after {
      width: 15px;
        height: 15px;
        border-radius: 15px;
        top: -2px;
        left: -1px;
        position: relative;
        background-color: #d1d3d1;
        content: '';
        display: inline-block;
        visibility: visible;
        border: 2px solid white;
    }

input[type='radio']:checked:after {
        width: 15px;
        height: 15px;
        border-radius: 15px;
        top: -2px;
        left: -1px;
        position: relative;
        background-color: #bc7f37;
        content: '';
        display: inline-block;
        visibility: visible;
        border: 2px solid white;
    }

.btn2 {
  margin-top: 25px;
}
.btn2 {
  font-family: "mencken-std", sans-serif;
  font-weight: 400;
  font-style: normal;
  color: #FF415B;
  margin: 15px 0 0 15px;
  border: 2px solid #64AA71;
  border-radius: 50px;
  padding: 8px 25px;
  text-transform: uppercase;
  font-size: 13px;
  letter-spacing: 1px;
  transition: 0.3s;
}

.btn2:hover {
  color: #e37d43;
}

input [type="radio"]:after {
  background-color: #64aa71
}

#add-attendees {
    background: rgba(8, 8, 8, 0.39);
    padding: 30px;
}

#chef {
  margin-bottom: 20px;;
}

</style>