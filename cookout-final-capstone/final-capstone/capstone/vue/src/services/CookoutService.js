import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:9000"
//   });

export default {
    // GET 
    getCookouts() {
        return axios.get('/cookout');
    },

    getCookoutById(cookoutID) {
        return axios.get(`/cookout/${cookoutID}`)
    },

    getCookoutsByUserId(id){
        return axios.get(`/cookout/mycookouts/${id}`)
    },

    getGuestListById(cookoutID) {
        return axios.get(`/userCookout/cookoutDetail/${cookoutID}`)
    },
    
    getUserCookoutById(cookoutID, id){
        return axios.get(`/userCookout/cookoutDetail/${cookoutID}/${id}`)
    },
    
    // POST
    addCookout(cookout) {
        return axios.post(`/cookout/newCookout`, cookout);
    },

    addUserCookout(userCookout) {
        return axios.post(`/userCookout/newUserCookout`, userCookout);
    },

    createNewUserOrder(userOrder){
        return axios.post(`userOrder/newOrder`, userOrder);
    },
    
    addMenuItem(menuItem){
        return axios.post(`/menu/newItem`, menuItem);
    },

    // UPDATE
    updateCookout(cookout, cookoutId) {
        return axios.put(`/cookout/update/${cookoutId}`, cookout);
    },

    updateUserCookoutRSVP(userCookout, cookoutId, id) {
        return axios.put(`userCookout/update/${cookoutId}/${id}`, userCookout);
    }
}