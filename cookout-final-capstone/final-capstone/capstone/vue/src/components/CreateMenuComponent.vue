<template>
  <div>
    <h1>Create Menu</h1>
    <!-- Add list where created items will appear (so user can see what items are already on the menu) -->
    <!-- Each item needs a name and belongs to an item type (entree/side/dessert/beverage) -->
    <form class="create-menu">
      <div class="create-menu-item">
        <label for="menu-item">Add Menu Item</label>
        <select
          id="menu-item-list"
          name="menu-item-list"
          v-model="menuOrder.type"
        >
          <!-- in progress -->
          <option value="entree" id="menu-item">Entree</option>
          <option value="side" id="menu-item">Side</option>
          <option value="dessert" id="menu-item">Dessert</option>
          <option value="beverage" id="menu-item">Beverage</option>
          <option value="misc">Miscellaneous</option>
        </select>
        <input type="text" id="entree-item" v-model="menuOrder.name" />
        <div class="attendee-info-item">
          <label for="save-button"></label>
          <button
            class="btn2"
            type="button"
            v-on:click.prevent="addMenuItem"
          >
            Save
          </button>
        </div>
        <!-- in progress  -->
      </div>
    </form>
  </div>
</template>

<script>
import CookoutService from "../services/CookoutService";

export default {
  name: "createMenu",
  data() {
    return {
      menuOrder: {
        name: "",
        type: "",
      },
    };
  },
  methods: {
    addMenuItem() {
      //insert into menu
      //new menuItem obj
      const newMenuItem = {
        cookoutId: this.$store.state.cookout.cookoutId,
        itemName: this.menuOrder.name,
        itemType: this.menuOrder.type
      };
      console.log(newMenuItem)
      CookoutService.addMenuItem(newMenuItem)
        .then((response) => {
          if (response.status === 200) {
            // this.menuOrder = response.data;
          this.$store.commit("SET_MENU", this.menuOrder);

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
    },
  },
};
</script>

<style scoped>

.create-menu {
  background: rgba(8, 8, 8, 0.39);
  padding: 30px
}




.btn2 {
    margin-top: 25px;
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

</style>
