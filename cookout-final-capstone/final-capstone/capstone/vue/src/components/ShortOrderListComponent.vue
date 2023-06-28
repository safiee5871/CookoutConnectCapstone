<template>
  <div>
      <h1>Short-Order List</h1>
      
      <table class="ShortOrderTbl">
        <thead>
            <td class="tblItem">Username</td>
            <td class="tblItem">Food Type</td>  
            <td class="tblItem">Dish Name</td>
            <td class="tblItem">Allergens</td>
        </thead>
      <tr class="menu" v-for="menu in menu" v-bind:key="menu.cookoutId">
          <td class="tblItem" v-show="!isCompletedOrder">{{username}}</td>
          <td class="tblItem" v-show="!isCompletedOrder">{{menu.item_type}}</td>
          <td class="tblItem" v-show="!isCompletedOrder">{{menu.item_name}}</td>
          <td class="tblItem" v-show="!isCompletedOrder">{{menu.allergens}}</td>
          <td class="menuTable"><input type="checkbox" v-on:click="isCompletedOrder = true" v-show="!isCompletedOrder"/>Order Finished</td>
      </tr>
      </table>
  </div>
</template>

<script>
export default {
    name: "shortOrderListComponent",
    data(){
        return{
            order: {},
            username: '',
            isCompletedOrder: false
        }
    },
    created(){
        //load all orders for this cookout
        // get user_order and menu info by joining tables
        //associate cookoutId to this menu
    },
    methods: {
        
    }

}
</script>

<style>

</style>