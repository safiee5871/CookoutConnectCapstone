<template>
  <div class="cookout_menu">
    <h1>Cookout Menu</h1>
    <h2>Select items to add to your order</h2>

    <!-- menu items table -->
    <div class="menu-table">
      <table class="menuTable" data-classes="table table-hover table-condensed">
        <thead>
          <th class="menuTable">ADD</th>
          <th class="menuTable">DISH NAME</th>
          <th class="menuTable">FOOD TYPE</th>
        </thead>
        <tr class="menu" v-for="menu in menuItems" :key="menu.itemName">
          <td class="menuTable">
            <input
              id="checkbox"
              type="checkbox"
              :value="menu.menuItemId"
              v-model="checkedItems"
            />
          </td>
          <td class="menuTable">{{ menu.itemName }}</td>
          <td class="menuTable">{{ menu.itemType }}</td>
        </tr>
      </table>
    </div>

    <!-- create order -->
    <div class="createOrder">
      <button class="btn2" type="button" v-on:click="createUserOrder">
        Create Order
      </button>
    </div>
  </div>
</template>

<script>
// JavaScript
import menuService from "../services/MenuService.js";

export default {
  name: "MenuComponent",
  data() {
    return {
      menuItems: [],
      orderName: "",
        //   array of Ids
      checkedItems: [],
    };
  },

  created() {
      console.log(this.$route.params.cookoutId);
    menuService
      .getMenuByCookoutId(this.$route.params.cookoutId)
      .then((response) => {
        this.menuItems = response.data;
        console.log(this.menuItems);
      });
  },

  methods: {
    createUserOrder() {
      console.log(this.checkedItems);
      this.displayCreateOrder = true;

      //loop through each item in array
      this.checkedItems.forEach((checkedMenuItem) => {
        
        //newOrder obj
        const newOrder = {
          userId: this.$store.state.user.id,
          menuItemId: checkedMenuItem,
        };

        console.log(newOrder);
        console.log(checkedMenuItem);
        
        //create new user order
        menuService
          .createUserOrder(newOrder)
          .then((response) => {
            if (response.status === 201) {
             console.log(`Item added: ${newOrder}`)
            }
          })
          .catch((error) => {
            if (error.response) {
              window.alert("Bad Request");
            } else if (error.request) {
              window.alert("Could not reach service");
            }
          });
      });
    },
  },
};
</script>

<style scoped>
/* CSS */

.cookout_menu {
  align-items: center;
  text-align: center;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

h1 {
  margin-top: 100px;
  margin-bottom: 15px;
}

.menuTable {
  border: 1px solid;
  border-color: #352c20;
  width: 65%;
  border-spacing: 10px;
  border-collapse: collapse;
  table-layout: fixed;
  margin-left: auto;
  margin-right: auto;
  padding: 5px;
  margin-top: 10px;
}

tr:hover {
  background-color: #64aa71a2;
}

tr {
  /* text-align: left; */
  color: #eae8e8;
  background: rgba(2, 8, 0, 0.452);
}

th {
  color: rgb(255, 251, 251);
  background: rgba(219, 148, 66, 0.397);
}

.btn2 {
  margin-top: 75px;
}
</style>