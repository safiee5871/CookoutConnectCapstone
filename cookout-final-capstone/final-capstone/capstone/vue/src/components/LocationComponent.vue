<template>
  <div>
    <h1>Location</h1>
    <p>Select a location for your cookout.</p>
    <div id="map"></div>
    <div id="input-area">
      <p>For best results the address should have this format:</p>
      <p><em>2934 Russell St, Detroit, MI, 48207</em></p>
      <form class="location">
        <label for="location">Address:</label>
        <input type="text" v-model="currentInput" />
        <!-- Save Button -->
        <div class="date-time-submit">
          <label for="submit-button"></label>
          <button class="btn4" type="button" v-on:click="addToList">
            Add to Route
          </button>
          <p>Current Locations:</p>
          <button class="btn2" v-on:click.prevent="generateRoute">
            Generate Route</button
          ><br /><br />
          <div
            id="currentList"
            v-for="(location, index) of locations"
            v-bind:key="index"
          >
            <input class="current-inputs" v-model="locations[index]" />
            <button class="btn2" v-on:click.prevent="removeFromList(index)">
              Remove
            </button>
          </div>
          <div>
            <br />
            <label for="saveAddress">Cookout Address:</label>
            <input type="text" v-model="newLocation" />
            <button class="btn2" v-on:click.prevent="addLocation">Save</button>
          </div>
        </div>
        <!--Google Maps will render directions here-->
        <div id="panel"></div>
      </form>
    </div>
  </div>
</template>

<script>
import CookoutService from "../services/CookoutService";

export default {
  name: "location",
  data() {
    return {
      newLocation: "",
      map: null,
      routeService: null,
      routeRendererService: null,
      currentInput: "",
      roundTrip: true,
      mapCenter: { lat: 42.3327, lng: -83.0458 },
      locations: [
        "5200 Woodward Ave, Detroit, MI 48202",
        "2934 Russell St, Detroit, MI, 48207",
      ],
    };
  },
  methods: {
    addLocation() {
      //Pass location
      const updatedCookout = {
        cookoutId: this.$store.state.cookout.cookoutId,
        name: this.$store.state.cookout.cookoutName,
        date: this.$store.state.cookout.date,
        time: this.$store.state.cookout.time,
        location: this.newLocation
      };
      
      console.log(updatedCookout);
      
      CookoutService.updateCookout(updatedCookout, updatedCookout.cookoutId)
        .then((response) => {
          if(response.status === 200) {
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
    // This function is called during load, but can also be called to reset the map
    initMap() {
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: this.mapCenter,
        zoom: 14,
        maxZoom: 20,
        minZoom: 3,
        streetViewControl: true,
        mapTypeControl: true,
        fullscreenControl: true,
        zoomControl: true,
      });
      let noPOIStyle = [
        {
          featureType: "poi",
          elementType: "labels",
          stylers: [{ visibility: "off" }],
        },
      ];
      this.map.setOptions({ styles: noPOIStyle });
    },

    // This function is called to add a new location
    addToList() {
      if (this.currentInput.trim().length === 0) {
        window.alert("Location cannot be empty");
        return;
      }
      this.locations.push(this.currentInput);
      this.currentInput = "";
    },

    // This function is called to remove a location
    removeFromList(index) {
      if (this.locations.length == 2) {
        window.alert("A start and end location must be present");
        return;
      }

      this.locations.splice(index, 1);
    },

    // This function calls the Google Maps API, renders the route
    // and retrieves the directions
    generateRoute() {
      for (let i = 0; i < this.locations.length; i++) {
        if (this.locations[i].trim().length === 0) {
          window.alert("Location cannot be empty");
          return;
        }
      }

      const panel = document.getElementById("panel");
      panel.innerHTML = "";
      this.initMap();

      this.routeService = new window.google.maps.DirectionsService();
      this.routeRendererService = new window.google.maps.DirectionsRenderer();

      this.routeRendererService.setMap(this.map);
      this.routeRendererService.setPanel(panel);

      let myWaypoints = [];

      /*
        The API expects a single waypoint to be an object like this:
        
        {
          location: "123 somewhere St...",
          stopover: true
        }
      */

      // loop through all locations
      for (let i = 1; i < this.locations.length - 1; i++) {
        myWaypoints.push({
          //add into myWaypoints
          location: this.locations[i],
          stopover: true,
        });
      }

      this.routeService
        .route({
          origin: this.locations[0],
          destination: this.locations[this.locations.length - 1],
          waypoints: myWaypoints,
          travelMode: window.google.maps.TravelMode.DRIVING,
          avoidTolls: true,
          optimizeWaypoints: true,
        })
        .then((result) => {
          this.routeRendererService.setDirections(result);
        })
        .catch((error) => {
          console.log(error + "Could not generate route");
        });
    },
  },

  mounted() {
    this.initMap();
  },
};
</script>

<style scoped>

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

#grid-container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "map inputs"
    "directions directions";
}

#map {
  grid-area: map;
  width: 650px;
  height: 400px;
  padding: 25px;
  margin: 25px;
}

#input-area {
  grid-area: inputs;
}

#panel {
  grid-area: directions;

  background: #eae8e8;
}
/* =======

>>>>>>> main */

.current-inputs {
  width: 350px;
}

.btn4 {
  font-family: "mencken-std", sans-serif;
  font-weight: 400;
  font-style: bold;
  margin-top: 30px;
  border: 2px solid #96c5ea;
  color: #fff;
  background: transparent;
  border-radius: 60px;
  padding: 15px 20px;
  text-transform: uppercase;
  font-size: 15px;
  letter-spacing: 1px;
  transition: 0.3s;
}

#input-area {
  background: rgba(8, 8, 8, 0.39);
  padding-bottom: 40px;
  padding-top: 15px
}


</style>