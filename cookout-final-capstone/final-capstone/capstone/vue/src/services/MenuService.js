import axios from 'axios';

export default {

    // GETS
    getAllMenus() {
        return axios.get('/menu/all');
    },

    getMenuByItemId(menuItemId) {
        return axios.get(`/menu/${menuItemId}`)
    },

    getMenuByCookoutId(cookoutId){
        return axios.get(`/menu/cookoutId/${cookoutId}`)
    },

    // POST
    createMenu(menu) {
        return axios.post(`/menu/newItem`, menu);
    },

    createUserOrder(userOrder) {
        return axios.post(`/userOrder/newOrder`, userOrder);
    },

    // PUT
    updateMenu(cookoutId) {
        return axios.put(`/menu/update/${cookoutId}`);
    },
}